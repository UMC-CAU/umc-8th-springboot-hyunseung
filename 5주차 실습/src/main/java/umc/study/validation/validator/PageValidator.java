package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.apipayload.code.status.ErrorStatus;
import umc.study.apipayload.exception.InvalidPageException;
import umc.study.validation.annotation.ValidPage;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {
    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer data, ConstraintValidatorContext context) {
        boolean isValid = (data != null);
        if (isValid)
            isValid = data > 0;
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.INCORRECT_PAGE.toString()).addConstraintViolation();
        }
        return isValid;
    }
}