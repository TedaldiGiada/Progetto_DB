package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.Treatment;

public interface AnnualTreatmentDAO {

    List<Treatment> getAnnualTreatments(int anno);

}
