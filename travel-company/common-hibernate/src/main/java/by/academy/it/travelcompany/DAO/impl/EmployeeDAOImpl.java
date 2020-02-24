package by.academy.it.travelcompany.DAO.impl;

import by.academy.it.travelcompany.DAO.EmployeeDAO;
import by.academy.it.travelcompany.entity.homework.orm.Department;
import by.academy.it.travelcompany.entity.homework.orm.Employee;
import by.academy.it.travelcompany.entity.homework.orm.EmployeeDetail;
import by.academy.it.travelcompany.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.Period;
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
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(criteriaBuilder.gt(employee.get("salary"), salary));
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getSalaryGraterThanOrderDesc(Long salary) {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.orderBy(criteriaBuilder.desc(employee.get("salary"))).where(criteriaBuilder.gt(employee.get("salary"), salary));
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getSalaryLessOrEqual(Long salary) {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).where(criteriaBuilder.lessThanOrEqualTo(employee.get("salary"), salary));
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByAgeBetween(Integer from, Integer to) {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        LocalDate birthDayFrom = LocalDate.now().minusYears(to);
        LocalDate birthDayTo = LocalDate.now().minusYears(from);
        Join<Employee, EmployeeDetail> join = employee.join("employeeDetail", JoinType.INNER);
        criteria.select(employee).where(criteriaBuilder.between(join.get("birthdayLocalDate"), birthDayFrom, birthDayTo));
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByAgeAndName(String firstName, String lastName, Integer age) {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Join<Employee, EmployeeDetail> join = employee.join("employeeDetail", JoinType.INNER);
        criteria.select(employee).where(criteriaBuilder.and
                        (criteriaBuilder.equal(employee.get("firstName"), firstName)),
                (criteriaBuilder.equal(employee.get("lastName"), lastName)),
                (criteriaBuilder.between(join.get("birthdayLocalDate"), LocalDate.now().minusYears(age + 1), LocalDate.now().minusYears(age)))
        );
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public List<Employee> getByAgeOrName(String firstName, String lastName, Integer age) {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Join<Employee, EmployeeDetail> join = employee.join("employeeDetail", JoinType.INNER);
        criteria.select(employee).where(criteriaBuilder.or
                (criteriaBuilder.equal(employee.get("firstName"), firstName),
                        (criteriaBuilder.equal(employee.get("lastName"), lastName)),
                        (criteriaBuilder.between(join.get("birthdayLocalDate"), LocalDate.now().minusYears(age + 1), LocalDate.now().minusYears(age))))
        );
        Session session = sessionFactory.openSession();
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public long getEmployeeCount() {
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        criteria.select(criteriaBuilder.count(criteria.from(Employee.class)));
        Session session = sessionFactory.openSession();
        long count = session.createQuery(criteria).getSingleResult();
        session.close();
        return count;
    }

    @Override
    public Double getAverageSalary() {
        CriteriaQuery<Double> criteria = criteriaBuilder.createQuery(Double.class);
        criteria.select(criteriaBuilder.avg(criteria.from(Employee.class).get("salary")));
        Session session = sessionFactory.openSession();
        Double result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

    @Override
    public Double getMaxSalary() {
        CriteriaQuery<Double> criteria = criteriaBuilder.createQuery(Double.class);
        criteria.select(criteriaBuilder.max(criteria.from(Employee.class).get("salary")));
        Session session = sessionFactory.openSession();
        Double result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

    @Override
    public long getMinAge() {
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Join<Employee, EmployeeDetail> join = employee.join("employeeDetail", JoinType.INNER);
        criteria.select(employee);
        criteria.orderBy(criteriaBuilder.desc(join.get("birthdayLocalDate")));
        Session session = sessionFactory.openSession();
        List<Employee> employeeList = session.createQuery(criteria).getResultList();
        Employee employeee = employeeList.get(0);
        session.close();
        return Period.between(employeee.getEmployeeDetail().getBirthdayLocalDate(), LocalDate.now()).getYears();

    }

    @Override
    public Double getAverageSalaryByDep(Long depId) {
        CriteriaQuery<Double> criteria = criteriaBuilder.createQuery(Double.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Join<Employee, Department> join = employee.join("department", JoinType.INNER);
        criteria.select(criteriaBuilder.avg(employee.get("salary"))).where(criteriaBuilder.equal(join.get("departmentId"), depId));
        Session session = sessionFactory.openSession();
        Double result = session.createQuery(criteria).getSingleResult();
        session.close();
        return result;
    }

}
