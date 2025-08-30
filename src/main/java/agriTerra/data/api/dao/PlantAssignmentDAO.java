package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.CropCycle;

public interface PlantAssignmentDAO {
    
    void insertPlantAssignment(CropCycle cropCycle);

    List<CropCycle> getCycle();
}
