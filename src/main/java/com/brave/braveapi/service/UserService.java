package com.brave.braveapi.service;

import com.brave.braveapi.dto.UserDTO;
import com.brave.braveapi.entidades.User;
import com.brave.braveapi.exceptions.BadRequestException;
import com.brave.braveapi.exceptions.ConflictException;
import com.brave.braveapi.exceptions.NotFoundException;
import com.brave.braveapi.exceptions.ServerErroException;
import com.brave.braveapi.persistence.IUserRepository;
import com.brave.braveapi.ports.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO find(String login, String password) {
        try {
            validateLoginAndPassword(login, password);

            Optional<User> response = repository.findOneByLoginAndPassword(login, password);

            if (response.isEmpty()) {
                throw new NotFoundException(String.format("Usuário não encontrado pelo login e senha passados - %s", login));
            }

            return toDto(response.get());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerErroException("Ocorreu algum erro ao tentar buscar um usuário");
        }
    }

    @Override
    public UserDTO insert(UserDTO request) {
        try {
            validationsInsert(request);

            if (repository.existsByLogin(request.getLogin())) {
                throw new ConflictException("Já existe um usuário com esse login");
            }

            User response = repository.save(toEntity(request));
            return toDto(response);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerErroException("Ocorreu algum erro ao tentar inserir um usuário");
        }
    }

    private void validationsInsert(UserDTO request) {
        if (request == null) {
            throw new BadRequestException("O body da resquest não pode ser nulo.");
        }

        validateLoginAndPassword(request.getLogin(), request.getPassword());
    }

    private void validateLoginAndPassword(String login, String password) {
        if (StringUtils.isBlank(login)) {
            throw new BadRequestException("O login não pode ser nulo ou vazio.");
        }

        if (StringUtils.isBlank(password)) {
            throw new BadRequestException("A senha não pode ser nula ou vazia.");
        }
    }

    private User toEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    private UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
