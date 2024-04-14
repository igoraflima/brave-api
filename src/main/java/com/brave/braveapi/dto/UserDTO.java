package com.brave.braveapi.dto;

import com.brave.braveapi.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String login;

    private String password;

    private String name;

    private Date birthday;

    private GenderEnum gender;

    private Boolean christian;

    private String photo;
}
