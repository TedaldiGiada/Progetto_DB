package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.VehicleAssignment;

public interface VehicleAssignmentDAO {

    void assignVehicleToEmployee(VehicleAssignment assignment);

    List<VehicleAssignment> assegnazioni();
    
}
