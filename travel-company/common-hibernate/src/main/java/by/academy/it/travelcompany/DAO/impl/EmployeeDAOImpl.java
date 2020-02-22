package by.academy.it.travelcompany.DAO.impl;

import by.academy.it.travelcompany.DAO.EmployeeDAO;
import by.academy.it.travelcompany.entity.homework.orm.Employee;
import by.academy.it.travelcompany.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final EmployeeDAOImpl INSTANCE = new EmployeeDAOImpl();

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();

    public static EmployeeDAOImpl getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public List<Employee> getAll() {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        criteria.from(Employee.class);
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByName(String firstName, String lastName) {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);

        criteria.select(employee).where(criteriaBuilder.and
                        (criteriaBuilder.equal(employee.get("firstName"), firstName)),
                (criteriaBuilder.equal(employee.get("lastName"), lastName))
        );

        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getAllWithNameNotNull() {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);

        criteria.select(employee).where(criteriaBuilder.and
                (criteriaBuilder.isNotNull(employee.get("firstName"))),
                (criteriaBuilder.isNotNull(employee.get("lastName")))
                );

        Session session = sessionFactory.openSession();
        List<Employee> employees = sessionFactory.openSession().createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getSalaryGraterThan(Long salary) {
        return null;
    }

    @Override
    public List<Employee> getSalaryGraterThanOrderDesc(Long salary) {
        return null;
    }

    @Override
    public List<Employee> getSalaryLessOrEqual(Long salary) {
        return null;
    }

    @Override
    public List<Employee> getByAgeBetween(Integer from, Integer to) {
        return null;
    }

    @Override
    public List<Employee> getByAgeAndName(String name, Integer age) {
        return null;
    }

    @Override
    public List<Employee> getByAgeOrName(String name, Integer age) {
        return null;
    }

    @Override
    public long getEmployeeCount() {
        return 0;
    }

    @Override
    public Double getAverageSalary() {
        return null;
    }

    @Override
    public Double getMaxSalary() {
        return null;
    }

    @Override
    public long getMinAge() {
        return 0;
    }

    @Override
    public Double getAverageSalaryByDep(Long depId) {
        return null;
    }
}
