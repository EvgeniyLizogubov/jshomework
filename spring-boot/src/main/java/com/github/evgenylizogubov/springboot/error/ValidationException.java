package com.github.evgenylizogubov.springboot.error;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<String> errorFieldMessages;
    
    public ValidationException(List<String> errorFieldMessages) {
        super(String.join(", ", errorFieldMessages));
        this.errorFieldMessages = errorFieldMessages;
    }
}
