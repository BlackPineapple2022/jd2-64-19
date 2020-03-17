package by.academy.it.travelcompany.install.role.impl;

import by.academy.it.travelcompany.entity.Role;
import by.academy.it.travelcompany.install.role.RoleInstaller;
import by.academy.it.travelcompany.install.role.data.RoleEnum;
import by.academy.it.travelcompany.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleInstallerImpl implements RoleInstaller {

    @Autowired
    private RoleService roleService;

    @Override
    public void install() {
        log.info("Starting install role");
        for (RoleEnum roleEnum: RoleEnum.values()) {
            roleService.create(new Role(roleEnum.name()));
        }
    }
}
