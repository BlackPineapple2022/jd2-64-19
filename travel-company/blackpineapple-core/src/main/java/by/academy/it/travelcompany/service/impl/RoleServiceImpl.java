package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Role;
import by.academy.it.travelcompany.repository.RoleRepository;
import by.academy.it.travelcompany.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void create(Role role) {
        log.info("Creating role "+ role);
        roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        log.info("Getting role by name");
        return roleRepository.findByName(name);
    }
}
