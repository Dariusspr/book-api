package com.app.exceptions;

import com.app.constants.ExceptionMessages;

public class CategoryNotFoundException  extends RuntimeException{
    public CategoryNotFoundException() {
        super(ExceptionMessages.CATEGORY_NOT_FOUND_MESSAGE);
    }
}
