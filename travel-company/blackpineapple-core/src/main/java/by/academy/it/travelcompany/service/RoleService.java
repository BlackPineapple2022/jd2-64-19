package by.academy.it.travelcompany.service;


import by.academy.it.travelcompany.entity.Role;

public interface RoleService {

    void create(Role role);

    Role getByName(String name);

}
