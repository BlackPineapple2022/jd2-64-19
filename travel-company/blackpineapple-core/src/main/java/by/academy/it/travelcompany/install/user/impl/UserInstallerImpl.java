package by.academy.it.travelcompany.install.user.impl;

import by.academy.it.travelcompany.entity.Role;
import by.academy.it.travelcompany.entity.User;
import by.academy.it.travelcompany.install.user.UserInstaller;
import by.academy.it.travelcompany.service.RoleService;
import by.academy.it.travelcompany.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInstallerImpl implements UserInstaller {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void install() {
        log.info("Start installing default user");
        Role admin = roleService.getByName("ADMIN");
        Role user = roleService.getByName("USER");
        User u = new User("skibidi","1123581321",admin);
        User u1 = new User("admin","1123581321",admin);
        User u2 = new User("black","1123581321",user);
        userService.create(u);
        userService.create(u1);
        userService.create(u2);
    }
}
