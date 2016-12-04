package com.xelita.study.akka.actor;

import akka.actor.UntypedActor;
import com.xelita.study.akka.actor.event.MailingEvent;
import com.xelita.study.message.Publication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailingActor extends UntypedActor {


    public void onReceive(Object o) throws Throwable {
        if (o instanceof MailingEvent) {
            onReceive((MailingEvent) o);
        } else {
            unhandled(o);
        }
    }

    private void onReceive(MailingEvent o) {
        log.debug("message received: {}", o.toString());
        Publication publication = o.data();
        if (publication.getMetadata().isMultipart()) {

        } else {

        }
    }
}
