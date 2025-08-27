package agricola.data.api;

import java.sql.Date;

public interface Treatment {

    int getId();

    Date getData();

    int getQuantita();

    String getTipo();

    String getDescrizione();

    int getIdSpesa();
    
}
