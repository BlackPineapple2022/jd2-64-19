package by.academy.it.travelcompany.installer;

import by.academy.it.travelcompany.BlackPineappleCoreInstaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Slf4j
public class InstallerController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        log.debug("install controller");
        return "install";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String startRYScanner() {
        BlackPineappleCoreInstaller.main(null);
        return "thanks";
    }

}
