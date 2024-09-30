package com.app.dtos.responses;

import java.time.LocalDateTime;

public record ExceptionResponse(String message, LocalDateTime time, int status) {
}
