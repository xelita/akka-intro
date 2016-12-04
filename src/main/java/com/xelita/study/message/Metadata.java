package com.xelita.study.message;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Metadata {

    private String contract;
    private String affiliate;
    private long timestamp;
    private int count;
    private boolean multipart;
    private int multipartCurrentNumber;
    private int multipartTotalNumber;
    private PublicationStatus status = PublicationStatus.NEW;
}
