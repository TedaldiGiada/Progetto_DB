package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.Treatment;

public interface AnnualTreatmentDAO {

    List<Treatment> getAnnualTreatments(int anno);

}
