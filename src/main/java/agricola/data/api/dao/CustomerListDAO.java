package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.Customer;

public interface CustomerListDAO {

    List<Customer> getAnnualCustomerList (int anno);
    
}
