package com.artineer.artineer.validator;

import com.artineer.artineer.domain.embeddable.Birth;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.util.StringUtils;

@Component
public class BirthValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Birth.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Birth birth = (Birth) target;

        if (!StringUtils.hasText(birth.getYear())) {
            errors.rejectValue("birth.year", "required");
        }

        if (!StringUtils.hasText(birth.getMonth())) {
            errors.rejectValue("birth.month", "required");
        }

        if (!StringUtils.hasText(birth.getDay())) {
            errors.rejectValue("birth.day", "required");
        }

    }
}