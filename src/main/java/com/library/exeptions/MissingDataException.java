package com.library.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class MissingDataException extends LibraryExeception{

    private final String detail;

    public MissingDataException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Request with missing mandatory data.");
        pb.setDetail(detail);
        return pb;
    }
}
