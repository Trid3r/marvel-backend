package com.trid3r.marvelapi.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDomain {
    private String body;
    private int code;

    public RequestDomain(String body, int code) {
        this.body = body;
        this.code = code;
    }
}
