package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.Employee;

public interface RegistrationEmployeeDAO {

    void registerEmployee(Employee employee);

    List<Employee> getEmployeeList();


    
}
