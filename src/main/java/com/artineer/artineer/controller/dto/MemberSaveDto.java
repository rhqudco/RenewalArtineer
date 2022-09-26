package com.artineer.artineer.controller.dto;

import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import com.artineer.artineer.validator.marker.SignInMarker;
import com.artineer.artineer.validator.marker.SignUpMarker;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberSaveDto {
    @NotBlank(groups = {SignUpMarker.class, SignInMarker.class}) // 회원가입, 로그인시 검증
    private String id;

    @NotBlank(groups = {SignUpMarker.class, SignInMarker.class})
    private String password;

    @NotBlank(groups = {SignUpMarker.class})
    private String name;

    private Birth birth;

    @NotBlank(groups = {SignUpMarker.class})
    private String emailId;

    @NotBlank(groups = {SignUpMarker.class})
    private String emailDomain;

    private Phone phone;

    @NotBlank(groups = {SignUpMarker.class})
    private String gender;

    @NotBlank(groups = {SignUpMarker.class})
    private String generation;

    private String level;
}