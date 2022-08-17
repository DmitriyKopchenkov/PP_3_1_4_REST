package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void add (Role role);

    //изменён на обёртку в связи с последнем замечанием в 2.3.1
    Role getById (Long id);

    //изменён на обёртку в связи с последнем замечанием в 2.3.1
    List<Role> getRoles(Long [] ides);
}
