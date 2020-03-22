package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.Direction;

public interface DirectionService {

    void create(Direction direction);

    Direction getByName(String name);
}
