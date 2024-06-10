package com.library.controller.v1;

import com.library.controller.v1.dto.LendAndReturnBookDto;
import com.library.controller.v1.dto.ResponseLeanDto;
import com.library.controller.v1.dto.ResponseReturnBookDto;
import com.library.service.LoanManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loan-management")
public class LoanManagementController {

    public LoanManagementService service;

    public LoanManagementController(LoanManagementService service) {
        this.service = service;
    }

    @PostMapping("/lend")
    public ResponseEntity<ResponseLeanDto> lendBooks(@RequestBody LendAndReturnBookDto lendBookDto) {

        ResponseLeanDto responseLoanDto = service.lend(lendBookDto);
        return new ResponseEntity<>(responseLoanDto, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<ResponseReturnBookDto> returnBooks(@RequestBody LendAndReturnBookDto returnBookDto) {
        ResponseReturnBookDto responseReturnBookDto = service.returnBook(returnBookDto);
        return new ResponseEntity<>(responseReturnBookDto, HttpStatus.OK);
    }
}
