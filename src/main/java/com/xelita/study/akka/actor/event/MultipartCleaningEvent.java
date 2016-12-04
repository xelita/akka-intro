package com.xelita.study.akka.actor.event;

import com.xelita.study.message.Publication;
import lombok.Data;

@Data
public class MultipartCleaningEvent implements Event<Publication> {

    private Publication data;

    public MultipartCleaningEvent(Publication data) {
        this.data = data;
    }

    @Override
    public Publication data() {
        return data;
    }
}
