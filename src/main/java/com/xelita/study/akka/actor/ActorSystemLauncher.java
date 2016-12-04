package com.xelita.study.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.xelita.study.akka.actor.event.MailingEvent;
import com.xelita.study.akka.actor.event.MultipartCheckingEvent;
import com.xelita.study.akka.actor.event.MultipartCleaningEvent;
import com.xelita.study.akka.actor.event.MultipartProcessingEvent;
import com.xelita.study.message.Metadata;
import com.xelita.study.message.Publication;
import com.xelita.study.message.PublicationStatus;

import java.util.Arrays;
import java.util.List;

public class ActorSystemLauncher {

    public static void main(String[] args) throws InterruptedException {
        // Create the actor system
        final ActorSystem actorSystem = ActorSystem.create("cds");

        // Create the actor for saving publications
        final ActorRef mailingActor = actorSystem.actorOf(Props.create(MailingActor.class), "mailing");
        final ActorRef checkingActor = actorSystem.actorOf(Props.create(MultipartCheckingActor.class), "checking");
        final ActorRef processingActor = actorSystem.actorOf(Props.create(MultipartProcessingActor.class), "processing");
        final ActorRef cleaningActor = actorSystem.actorOf(Props.create(MultipartCleaningActor.class), "cleaning");

        // Registering actors on events
        actorSystem.eventStream().subscribe(mailingActor, MailingEvent.class);
        actorSystem.eventStream().subscribe(checkingActor, MultipartCheckingEvent.class);
        actorSystem.eventStream().subscribe(processingActor, MultipartProcessingEvent.class);
        actorSystem.eventStream().subscribe(cleaningActor, MultipartCleaningEvent.class);

        // Emit some events
        Thread.sleep(5000);
        emitCheckingEvent(actorSystem);
    }

    private static void emitCheckingEvent(ActorSystem actorSystem) {
        List<String> data = Arrays.asList("Benjamin", "Sempere", "Software Architect");

        @SuppressWarnings("unchecked")
        Publication publication = Publication.builder()
                .id("/CommercialIndicators_1.0091.123456.0001-0002")
                .metadata(Metadata.builder()
                        .contract("/CommercialIndicators_1")
                        .affiliate("0091")
                        .timestamp(123456)
                        .count(3)
                        .multipart(true)
                        .multipartCurrentNumber(1)
                        .multipartTotalNumber(1)
                        .status(PublicationStatus.NEW)
                        .build())
                .data(data)
                .build();

        actorSystem.eventStream().publish(new MultipartCheckingEvent(publication));
    }
}
