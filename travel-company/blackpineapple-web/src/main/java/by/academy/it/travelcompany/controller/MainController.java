package by.academy.it.travelcompany.controller;

import by.academy.it.travelcompany.entity.Role;
import by.academy.it.travelcompany.entity.User;
import by.academy.it.travelcompany.service.RoleService;
import by.academy.it.travelcompany.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String about() {
        log.debug("home controller");
        return "about";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutRedirect() {
        log.debug("home controller");
        return "about";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeRedirect() {
        log.debug("home controller");
        return "about";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        log.debug("login controller");
        httpSession.removeAttribute("errorCode");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {
        log.debug("autorization controller");

        httpSession.removeAttribute("errorCode");

        if (gRecaptchaResponse == null || gRecaptchaResponse.length() < 1) {
            httpSession.setAttribute("errorCode", "3");
            return "login";
        }

        User user = userService.findByUserNameAndPassword(userName, password);
        if (user != null) {
            httpSession.setAttribute("user", user);
            return "about";
        } else {
            httpSession.setAttribute("errorCode", "2");
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        log.debug("logout controller");
        httpSession.removeAttribute("user");
        return "about";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp() {
        log.debug("signup controller");
        httpSession.removeAttribute("errorCode");
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPost(@RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             @RequestParam("passwordRepeat") String repeat,
                             @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {

        String nameRegex = "^[a-zA-Z0-9_-]{3,20}$";

        log.debug("signup controller register new user");

        httpSession.removeAttribute("errorCode");

        if (gRecaptchaResponse == null || gRecaptchaResponse.length() < 1) {

            httpSession.setAttribute("errorCode", "5");
            return "signup";
        }

        if (userName == null || userName.equals("") || password == null || password.equals("")) {
            httpSession.setAttribute("errorCode", "1");
            return "signup";
        }

        if (userService.isExist(userName)) {
            httpSession.setAttribute("errorCode", 3);
            return "signup";
        }

        if (!password.equals(repeat)) {
            httpSession.setAttribute("errorCode", "4");
            return "signup";
        }

        if (!userName.matches(nameRegex) || password.length() < 5) {
            httpSession.setAttribute("errorCode", "6");
            return "signup";
        }

        Role role = roleService.getByName("USER");
        userService.create(new User(userName, password, role));
        log.info("Successfully register on system " + userName);
        return "login";
    }

}
