package by.academy.it.travelcompany.orm.dao.impl;

import by.academy.it.travelcompany.orm.dao.EmployeeDetailDAO;
import by.academy.it.travelcompany.orm.entity.EmployeeDetail;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDetailDAOImpl extends AbstractDAO<EmployeeDetail> implements EmployeeDetailDAO {

    public EmployeeDetailDAOImpl() {
        super(EmployeeDetail.class);
    }
}

