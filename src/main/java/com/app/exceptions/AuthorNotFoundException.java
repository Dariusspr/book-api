package com.app.exceptions;

import com.app.constants.ExceptionMessages;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException() {
        super(ExceptionMessages.AUTHOR_NOT_FOUND_MESSAGE);
    }
}
