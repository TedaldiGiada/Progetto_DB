package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.Plant;

public interface AnnualLandCultivationDAO {

    List<Plant> getAnnualLandCultivation(int idTerreno, int anno);


}
