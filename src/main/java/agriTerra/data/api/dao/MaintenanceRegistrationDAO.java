package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.Maintenance;

public interface MaintenanceRegistrationDAO {

    void registerMaintenance(Maintenance maintenance);

    List<Maintenance> manutenzioni();
    
}
