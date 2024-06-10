package com.library.controller.v1.dto;

import java.time.LocalDate;

public record ResponseReturnBookDto(String fullName, String title, LocalDate returnDate) {
}
