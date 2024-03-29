package com.epam.esm.controller;

import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.FullUserDto;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> findAllUsers(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        List<UserDto> usersDto = userService.findAll(paginationDto);
        usersDto.forEach(this::addDependenciesLinks);
        return usersDto;
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable long id) {
        UserDto userDto = userService.findById(id);
        addDependenciesLinks(userDto);
        return userDto;
    }

    @PreAuthorize("permitAll()")
    @PostMapping(path = "/registration")
    public UserDto register(@RequestBody FullUserDto fullUserDto) {
        UserDto registeredUser = userService.register(fullUserDto);
        addDependenciesLinks(registeredUser);
        return registeredUser;
    }

    private void addDependenciesLinks(UserDto userDto) {
        userDto.add(linkTo(methodOn(UserController.class).findUserById(userDto.getId())).withSelfRel());
    }
}
