package com.trid3r.marvelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDomain {
    private int code;
    private String status;
    private ResponseData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseData {
        private int offset;
        private int limit;
        private int total;
        private int count;
        private List<Result> results;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private int id;
        private String name;
        private String description;
        private Thumbnail thumbnail;
        private Comics comics;
        private Events events;
        private Series series;
        private Stories stories;

        public Result (int id, String name, String description){
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }

    @Data
    public static class Thumbnail {
        private String path;
        private String extension;
    }

    @Data
    public static class Comics {
        private int available;
    }

    @Data
    public static class Events {
        private int available;
    }

    @Data
    public static class Series {
        private int available;
    }

    @Data
    public static class Stories {
        private int available;
    }

//    public Result (String name, String description){
//        this.results = name;
//        this.description = description;
//    }

//    public ResponseData (String offset, String limit, String total, String count){
//        this.offset = offset;
//        this.limit = limit;
//        this.total = total;
//        this.count = count;
//    }
}
