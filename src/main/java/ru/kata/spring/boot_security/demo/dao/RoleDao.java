package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> getAllRoles();

    public Role getRoleByName(String name);

    public List<Role> getListOfRoles(String[] roleNames);

    public void add(Role role);

    public void edit(Role role);

    public Role getById(int id);
}