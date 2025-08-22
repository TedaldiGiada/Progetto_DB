package main.java.agruterrra.api;

import java.util.List;

public interface RegistrationEmployeeDAO {
    void registraDipendente(String CF, String nome, String cognome, 
        int telefono, String via, String num_civ, String città, String note);
    List<String> listaDipendenti();
}
