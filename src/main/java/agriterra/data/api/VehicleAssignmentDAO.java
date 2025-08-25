package agriterra.data.api;

import java.time.LocalDate;
import java.util.List;

public interface VehicleAssignmentDAO {
    void assegnaDipendenteAMacchina(String CF, String ID_Macchinario, LocalDate data, String note);
    List<String> assegnazioni();
}
