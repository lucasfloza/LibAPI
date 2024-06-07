package com.library.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidFormatException extends LibraryExeception{

    private final String detail;

    public InvalidFormatException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Request data with incorrect formatting.");
        pb.setDetail(detail);
        return pb;
    }
}
