package com.brave.braveapi.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDateDTO {

    private Long id;

    private Date date;
}
