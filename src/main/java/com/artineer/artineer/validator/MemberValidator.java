package com.artineer.artineer.validator;

import com.artineer.artineer.domain.Member;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;

        if (!StringUtils.hasText(member.getName())) {
            errors.rejectValue("id", "required");
        }
    }
}