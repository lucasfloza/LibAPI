package com.library.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidMoveException extends LibraryExeception {
    private final String detail;

    public InvalidMoveException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Invalid Movement.");
        pb.setDetail(detail);
        return pb;
    }
}
