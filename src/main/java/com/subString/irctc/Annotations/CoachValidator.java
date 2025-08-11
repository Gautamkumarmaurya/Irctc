package com.subString.irctc.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoachValidator implements ConstraintValidator<ValidCoach , Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // Annotation create karne ka main logic yahi rahega
        System.out.println("CoachValidator isValid");

         if (value < 1 || 30 > value) {
            return true;
        }
         return false;
    }
}
