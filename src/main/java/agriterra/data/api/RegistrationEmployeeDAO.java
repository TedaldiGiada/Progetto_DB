package agriterra.data.api;

import java.util.List;

public interface RegistrationEmployeeDAO {
    void registraDipendente(String CF, String nome, String cognome, 
        long telefono, String via, int num_civ, String città, String note);
    List<String> listaDipendenti();
}
