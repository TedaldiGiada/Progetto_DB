package agricola.data.api;

import java.sql.Date;

public interface CropCycle {

    int id();

    double getRendimento();
    
    Date getDataInizio();

    Date getDataFine();

    String getUnitaMisura();

    String getDescrizione();

    int getIdTerreno();

    int getIdPianta();

    int getIdVendita();

    int getAnno();

}
