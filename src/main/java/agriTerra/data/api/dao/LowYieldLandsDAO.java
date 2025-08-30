package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.CropCycle;

public interface LowYieldLandsDAO {
    
    List<CropCycle> getLowYieldLands(String nome);
}
