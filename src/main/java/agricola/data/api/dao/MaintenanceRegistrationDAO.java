package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.Maintenance;

public interface MaintenanceRegistrationDAO {

    void registerMaintenance(Maintenance maintenance);

    List<Maintenance> manutenzioni();
    
}
