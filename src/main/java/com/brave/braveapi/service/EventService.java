package com.brave.braveapi.service;

import com.brave.braveapi.dto.EventDTO;
import com.brave.braveapi.dto.EventDateDTO;
import com.brave.braveapi.dto.UserDTO;
import com.brave.braveapi.entidades.Event;
import com.brave.braveapi.entidades.User;
import com.brave.braveapi.enums.EventEnum;
import com.brave.braveapi.exceptions.BadRequestException;
import com.brave.braveapi.exceptions.NotFoundException;
import com.brave.braveapi.exceptions.ServerErroException;
import com.brave.braveapi.persistence.IEventRepository;
import com.brave.braveapi.ports.IEventService;
import com.brave.braveapi.ports.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.brave.braveapi.util.InternalDateUtils.*;
import static com.brave.braveapi.util.InternalValidationUtils.isBlank;
import static com.brave.braveapi.util.InternalValidationUtils.isNull;

@Service
public class EventService implements IEventService {
    @Autowired
    private IEventRepository repository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EventDTO insert(EventDTO dto) {
        try {
            validation(dto);
            UserDTO user = userService.findById(dto.getUserId());

            Event request = toEntity(dto);
            request.setStatus(EventEnum.PENDING);
            request.setUser(modelMapper.map(user, User.class));

            return toDto(repository.save(request));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
          throw new ServerErroException("Ocorreu algum erro ao tentar inserir um evento", e);
        }
    }

    @Override
    public List<EventDateDTO> findAllByRangeDate(Long startDate, Long endDate) {
        try {
            if (isNull(startDate) || isNull(endDate)) {
                throw new BadRequestException("As datas de início e fim do range não podem ser nulas");
            }

            List<Event> result = repository.findAllByEventDateBetween(minHoursDay(startDate), maxHoursDay(endDate))
                    .orElseThrow(() -> new NotFoundException("Nenhum evento foi encontrando no range de filtro"));

            return result
                .stream()
                .map(e -> EventDateDTO
                        .builder()
                        .id(e.getId())
                        .date(e.getEventDate())
                        .build()
                ).collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerErroException("Ocorreu algum erro ao tentar inserir um evento", e);
        }
    }

    @Override
    public EventDTO findById(Long id) {
        try {
            if (isNull(id)) {
                throw new BadRequestException("O id não pode ser null");
            }

            return toDto(repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum evento foi encontrando pelo id")));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerErroException("Ocorreu algum erro ao tentar inserir um evento", e);
        }
    }

    private void validation(EventDTO dto) {
        if (isNull(dto)) {
            throw new BadRequestException("O body da resquest não pode ser nulo.");
        }

        if (isBlank(dto.getName())) {
            throw new BadRequestException("O nome do evento não pode ser nulo.");
        }

        if (isBlank(dto.getDescription())) {
            throw new BadRequestException("A descrição do evento não pode ser nula.");
        }

        if (isNull(dto.getEventDate())) {
            throw new BadRequestException("A data e horário do evento não pode ser nula.");
        }

        if (isBeforeToday(dto.getEventDate())) {
            throw new BadRequestException("A data e horário do evento não pode ser no passado.");
        }
    }

    private Event toEntity(EventDTO dto) { return modelMapper.map(dto, Event.class); }

    private EventDTO toDto(Event event) {
        EventDTO dto = modelMapper.map(event, EventDTO.class);
        dto.setUserId(event.getUser().getId());
        return dto;
    }
}
