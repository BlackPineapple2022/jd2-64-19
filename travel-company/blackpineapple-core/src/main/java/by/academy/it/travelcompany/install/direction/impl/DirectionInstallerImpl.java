package by.academy.it.travelcompany.install.direction.impl;

import by.academy.it.travelcompany.entity.Direction;
import by.academy.it.travelcompany.install.direction.DirectionInstaller;
import by.academy.it.travelcompany.install.direction.data.DirectionEnum;
import by.academy.it.travelcompany.service.DirectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionInstallerImpl implements DirectionInstaller {

    @Autowired
    private DirectionService directionService;

    @Override
    public void install() {
        log.info("Starting installing direction");
        for (DirectionEnum d:DirectionEnum.values()) {
            directionService.create(new Direction(d.name()));
        }
    }
}
