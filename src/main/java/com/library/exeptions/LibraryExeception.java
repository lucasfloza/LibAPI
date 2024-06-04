package com.library.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class LibraryExeception extends RuntimeException{

    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("Library Internal Server Error");
        return pb;
    }
}
