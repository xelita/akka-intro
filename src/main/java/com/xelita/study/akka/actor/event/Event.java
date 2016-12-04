package com.xelita.study.akka.actor.event;

public interface Event<T> {

    default String name() {
        return getClass().getName();
    }

    T data();
}
