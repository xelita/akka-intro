package com.xelita.study.message;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Publication<T> {

    private String id;
    private Metadata metadata;
    private T data;
}
