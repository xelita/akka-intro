package com.xelita.study.akka.actor.event;

import com.xelita.study.message.Publication;
import lombok.Data;

@Data
public class MultipartCheckingEvent implements Event<Publication> {

    private Publication data;

    public MultipartCheckingEvent(Publication data) {
        this.data = data;
    }

    @Override
    public Publication data() {
        return data;
    }
}
