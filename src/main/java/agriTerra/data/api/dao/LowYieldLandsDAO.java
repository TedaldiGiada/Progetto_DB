package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.CropCycle;

public interface LowYieldLandsDAO {
    
    List<CropCycle> getLowYieldLands(String nome);
}
