package com.app.exceptions;

import com.app.constants.ExceptionMessages;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super(ExceptionMessages.BOOK_NOT_FOUND_MESSAGE);
    }
}
