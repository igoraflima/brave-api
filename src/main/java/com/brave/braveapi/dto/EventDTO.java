package com.brave.braveapi.dto;

import com.brave.braveapi.enums.EventEnum;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private Long id;

    private Long userId;

    private String name;

    private String description;

    private Date eventDate;

    private String address;

    private EventEnum status;
}
