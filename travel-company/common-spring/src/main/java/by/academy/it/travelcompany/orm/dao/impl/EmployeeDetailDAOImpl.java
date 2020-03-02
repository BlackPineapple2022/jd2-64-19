package by.academy.it.travelcompany.orm.dao.impl;

import by.academy.it.travelcompany.orm.dao.EmployeeDetailDAO;
import by.academy.it.travelcompany.orm.entity.EmployeeDetail;

public class EmployeeDetailDAOImpl extends AbstractDAO<EmployeeDetail> implements EmployeeDetailDAO {

    public EmployeeDetailDAOImpl(Class<EmployeeDetail> clazz) {
        super(clazz);
    }
}
