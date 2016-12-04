package com.xelita.study.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class DataStore {

    public static Map<String, Publication> publications = Collections.synchronizedMap(new HashMap<String, Publication>());

    private DataStore() {
    }

    public static void addPublication(Publication publication) {
        publications.putIfAbsent(publication.getId(), publication);
    }
}
