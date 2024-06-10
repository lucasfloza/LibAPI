package com.library.controller.v1.dto;

import java.time.LocalDate;

public record ResponseLeanDto(String customerName, String bookTitle, LocalDate loanDate, LocalDate targetReturnDate) {

}
