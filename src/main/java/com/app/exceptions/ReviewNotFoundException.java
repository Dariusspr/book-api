package com.app.exceptions;

import com.app.constants.ExceptionMessages;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException() {
        super(ExceptionMessages.REVIEW_NOT_FOUND_MESSAGE);
    }
}
