package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Direction;
import by.academy.it.travelcompany.repository.DirectionRepository;
import by.academy.it.travelcompany.service.DirectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionServiceImpl implements DirectionService {

    @Autowired
    private DirectionRepository directionRepository;

    @Override
    public void create(Direction direction) {
        log.info("Creating new direction" + direction);
        directionRepository.save(direction);
    }

    @Override
    public Direction getByName(String name) {
        return directionRepository.getByName(name);
    }
}
