package com.xelita.study.akka.actor;

import akka.actor.UntypedActor;
import com.xelita.study.akka.actor.event.MailingEvent;
import com.xelita.study.akka.actor.event.MultipartCleaningEvent;
import com.xelita.study.message.Publication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultipartCleaningActor extends UntypedActor {


    public void onReceive(Object o) throws Throwable {
        if (o instanceof MultipartCleaningEvent) {
            onReceive((MultipartCleaningEvent) o);
        } else {
            unhandled(o);
        }
    }

    private void onReceive(MultipartCleaningEvent o) {
        log.debug("message received: {}", o.toString());
        Publication publication = o.data();
        if (publication.getMetadata().isMultipart()) {

        } else {

        }

        // publish a message to ask another actor for processing the data
        getContext().system().eventStream().publish(new MailingEvent(publication));
    }
}
