package by.academy.it.travelcompany.travelcompany.dao;

import by.academy.it.travelcompany.travelcompany.travelitem.direction.Direction;

import java.sql.SQLException;

public interface DirectionDAO extends DAO<Direction> {

    Long getIdByName(String name) throws SQLException;

}
