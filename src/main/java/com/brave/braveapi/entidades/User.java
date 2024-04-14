package com.brave.braveapi.entidades;

import com.brave.braveapi.entidades.base.BaseEntity;
import com.brave.braveapi.enums.GenderEnum;
import com.brave.braveapi.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Builder.Default
    @Column(name = "christian")
    private Boolean christian = false;

    @Builder.Default
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.COMMON_USER;

    @Column(name = "photo")
    private String photo;
}
