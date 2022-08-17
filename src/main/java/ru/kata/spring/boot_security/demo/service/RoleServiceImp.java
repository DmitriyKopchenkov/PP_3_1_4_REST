package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public void add(Role role) {

        roleRepository.save(role);
    }
    //изменён на обёртку в связи с последнем замечанием в 2.3.1
    @Override
    public Role getById(Long id) {

        return roleRepository.getById(id);
    }


    @Override
    public List<Role> getRoles(Long [] ides) {
        return roleRepository.findAll();

    }
}
