package com.library.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class NotFoundException extends LibraryExeception{

    private final String detail;

    public NotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Entered ID was not found in our system.");
        pb.setDetail(detail);
        return pb;
    }
}
