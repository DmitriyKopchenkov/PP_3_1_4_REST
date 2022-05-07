package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRep;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService{
    private final RoleRep rep;
    @Autowired
    public RoleServiceImp(RoleRep rep) {
        this.rep = rep;
    }

    @Override
    public void add(Role role) {
        rep.save(role);
    }

    @Override
    public Role getById(int id) {
        return rep.getById(id);
    }

    @Override
    public Set<Role> getRoles(int [] ides) {
        Set <Role> roles = new HashSet<>();
        for (int i = 0; i < ides.length; i++) {
            roles.add(rep.getById(ides[i]));
        }
        return roles;
    }
}
