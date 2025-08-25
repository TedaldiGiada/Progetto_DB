package agriterra.data.api;

import java.util.List;

public interface MaintenanceRegistrationDAO {
    void registraManutenzione(int ID_Manutenzione, String azienda, String descrizione, String tipo, int ID_Spesa, String ID_Macchinario);
    List<String> manutenzioni();
}
