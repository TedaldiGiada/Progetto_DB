package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.Employee;

public interface RegistrationEmployeeDAO {

    void registerEmployee(Employee employee);

    List<Employee> getEmployeeList();


    
}
