package data.api;

import java.util.List;

public interface LandCultivationDAO {
    List<String> coltureInTerrenoAnno(int ID_Terreno, int anno);
}
