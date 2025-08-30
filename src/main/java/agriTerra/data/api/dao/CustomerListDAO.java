package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.Customer;

public interface CustomerListDAO {

    List<Customer> getAnnualCustomerList (int anno);
    
}
