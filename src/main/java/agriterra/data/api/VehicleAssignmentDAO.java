package main.java.agruterrra.api;

import java.time.LocalDate;

public interface VehicleAssignmentDAO {
    void assegnaDipendenteAMacchina(String CF, String ID_Macchinario, LocalDate data, String note);
}
