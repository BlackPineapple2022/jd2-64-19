package by.academy.it.travelcompany.orm.service.impl;

import by.academy.it.travelcompany.orm.entity.EmployeeDetail;
import by.academy.it.travelcompany.orm.service.EmployeeDetailService;
import by.academy.it.travelcompany.orm.service.impl.AbstractCRUDService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailServiceImpl extends AbstractCRUDService<EmployeeDetail> implements EmployeeDetailService {

    protected EmployeeDetailServiceImpl() {
        super(EmployeeDetail.class);
    }
}
