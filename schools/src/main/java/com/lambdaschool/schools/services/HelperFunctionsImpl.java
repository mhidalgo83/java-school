package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")

public class HelperFunctionsImpl implements HelperFunctions {
    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause) {
        while((cause != null) && !(cause instanceof ConstraintViolationException)) {
            cause = cause.getCause();
        }

        List<ValidationError> listValidationErrors = new ArrayList<>();

        if(cause != null) {
            ConstraintViolationException ex = (ConstraintViolationException) cause;
            for(ConstraintViolation cv : ex.getConstraintViolations()) {
                ValidationError newValidationError = new ValidationError();
                newValidationError.setCode(cv.getInvalidValue().toString());
                newValidationError.setMessage(cv.getMessage());
                listValidationErrors.add(newValidationError);
            }
        }
        return listValidationErrors;
    }
}
