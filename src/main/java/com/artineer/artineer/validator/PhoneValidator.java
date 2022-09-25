package com.artineer.artineer.validator;

import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PhoneValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Phone.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Phone phone = (Phone) target;

        if (!StringUtils.hasText(phone.getFirstNumber())) {
            errors.rejectValue("phone.firstNumber", "required");
        }

        if (!StringUtils.hasText(phone.getMiddleNumber())) {
            errors.rejectValue("phone.middleNumber", "required");
        }

        if (!StringUtils.hasText(phone.getLastNumber())) {
            errors.rejectValue("phone.lastNumber", "required");
        }
    }
}