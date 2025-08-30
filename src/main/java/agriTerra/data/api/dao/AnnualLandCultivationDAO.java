package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.Plant;

public interface AnnualLandCultivationDAO {

    List<Plant> getAnnualLandCultivation(int idTerreno, int anno);


}
