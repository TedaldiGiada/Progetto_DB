package agriterra.data.api;

import java.time.LocalDate;
import java.util.List;

public interface PlantAssignmentDAO {
    public void assegnaTerrenoAColtura(int ID_Ciclo, int anno, LocalDate data_inizio, LocalDate data_fine, 
        int rendimento, String unità_misura, String descrizione, int ID_Terreno, int ID_Pianta, int ID_Vendita);
    List<String> cicli();
    }
