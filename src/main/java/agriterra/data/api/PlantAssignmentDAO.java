package main.java.agruterrra.api;

import java.sql.Date;

public interface PlantAssignmentDAO {
    public void assegnaTerrenoAColtura(int ID_Ciclo, int anno, Date data_inizio, Date data_fine, 
        int rendimento, String unità_misura, String descrizione, int ID_Terreno, int ID_Pianta, int ID_Vendita);
}
