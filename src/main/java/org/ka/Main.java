package org.ka;


import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toMap;

public class Main {

    static class Param {
        final int id;

        Param(int id) {
            this.id = id;
        }
    }

    static class Subtask {
        final Param param;

        Subtask(Param param) {
            this.param = param;
        }
    }

    public static void main(String[] args) {
        List<? extends Param> params = IntStream.range(1, 100).mapToObj(Param::new).collect(Collectors.toList());
//        NavigableMap<String, Subtask> map = params.stream()
//                .collect(Collectors.toMap(p -> UUID.randomUUID().toString(), Subtask::new, (a, b) -> a, TreeMap::new));
    }
}
