package com.tpi.backend.routes_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoogleDistanceResponce {

    private List<Row> rows;

    @Data
    public static class Row {
        private List<Element> elements;
    }

    @Data
    public static class Element {
        private Value distance;
        private Value duration;
    }

    @Data
    public static class Value {
        private String text;
        private Long value; // metros o segundos
    }

}
