package agriterra.data.DAO; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import agriterra.data.api.PlantAssignmentDAO;
import agriterra.data.utils.DAOException;


public class PlantAssignmentDAOImpl implements PlantAssignmentDAO{
    private final Connection conn;

    public PlantAssignmentDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void assegnaTerrenoAColtura(int ID_Ciclo, int anno, Date data_inizio, Date data_fine, int rendimento, String unità_misura, String descrizione, int ID_Terreno, int ID_Pianta, int ID_Vendita) {
        String sql = "INSERT INTO Coltivazione(ID_Ciclo, anno, data_inizio, data_fine, rendimento, unità_misura, descrizione, ID_Terreno, ID_Pianta, ID_Vendita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, ID_Ciclo);
            st.setInt(2, anno);
            st.setDate(3, data_inizio);
            st.setDate(4, data_fine);
            st.setDouble(5, rendimento);
            st.setString(6, unità_misura);
            st.setString(7, descrizione);
            st.setDouble(8, ID_Terreno);
            st.setDouble(9, ID_Pianta);
            st.setDouble(10, ID_Vendita);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Errore assegnazione coltura al terreno", e);
        }
    }
}