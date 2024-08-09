package com.github.evgenylizogubov.springboot.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FieldValidationError {
    private List<String> errorFieldMessages;
    
    public FieldValidationError(List<String> errorFieldMessages) {
        this.errorFieldMessages = errorFieldMessages;
    }
}
