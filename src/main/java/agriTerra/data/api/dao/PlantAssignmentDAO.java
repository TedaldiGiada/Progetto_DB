package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.CropCycle;

public interface PlantAssignmentDAO {
    
    void insertPlantAssignment(CropCycle cropCycle);

    List<CropCycle> getCycle();
}
