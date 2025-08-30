package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.VehicleAssignment;

public interface VehicleAssignmentDAO {

    void assignVehicleToEmployee(VehicleAssignment assignment);

    List<VehicleAssignment> assegnazioni();
    
}
