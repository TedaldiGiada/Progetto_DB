package agriterra.data.dao; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.PlantAssignmentDAO;
import agriterra.data.utils.DAOException;


public class PlantAssignmentDAOImpl implements PlantAssignmentDAO{
    private final Connection conn;

    public PlantAssignmentDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void assegnaTerrenoAColtura(int ID_Ciclo, int anno, LocalDate data_inizio, LocalDate data_fine, int rendimento, String unità_misura, String descrizione, int ID_Terreno, int ID_Pianta, Integer ID_Vendita) {
        String checkOccupazione = """
        SELECT COUNT(*) 
        FROM Ciclo_Colturale 
        WHERE ID_Terreno = ?
            AND data_inizio <= ?
            AND data_fine >= ?
        """;

        try (PreparedStatement checkSt = conn.prepareStatement(checkOccupazione)) {
            checkSt.setInt(1, ID_Terreno);
            checkSt.setDate(2, Date.valueOf(data_fine));   // fine nuovo ciclo
            checkSt.setDate(3, Date.valueOf(data_inizio)); // inizio nuovo ciclo
            try (ResultSet rs = checkSt.executeQuery()) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    throw new DAOException("Errore: il terreno " + ID_Terreno +
                    " è già occupato in questo periodo.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nella verifica disponibilità terreno", e);
        }
        String sql = "INSERT INTO Ciclo_Colturale(ID_Ciclo, anno, data_inizio, data_fine, rendimento, unità_misura, descrizione, ID_Terreno, ID_Pianta, ID_Vendita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, ID_Ciclo);
            st.setInt(2, anno);
            st.setDate(3, Date.valueOf(data_inizio));
            st.setDate(4, Date.valueOf(data_fine));
            st.setInt(5, rendimento);
            st.setString(6, unità_misura);
            st.setString(7, descrizione);
            st.setInt(8, ID_Terreno);
            st.setInt(9, ID_Pianta);
            if (ID_Vendita == null) {
                st.setNull(10, java.sql.Types.INTEGER);
            } else {
                st.setInt(10, ID_Vendita);
            }
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Errore assegnazione coltura al terreno", e);
        }
    }

    @Override
    public List<String> cicli() {
        List<String> result = new ArrayList<>();
        String sql = """
            SELECT C.ID_Ciclo, C.anno, C.data_inizio, 
                   C.data_fine, C.rendimento, C.unità_misura, 
                   C.descrizione, C.ID_Terreno, C.ID_Pianta
            FROM  Ciclo_Colturale C
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("ID_Ciclo") 
                + ";" + rs.getInt("anno")
                + ";" + rs.getDate("data_inizio") 
                + ";" + rs.getDate("data_fine")
                + ";" + rs.getInt("rendimento") 
                + ";" + rs.getString("unità_misura") 
                + ";" + rs.getString("descrizione") 
                + ";" + rs.getInt("ID_Terreno") 
                + ";" + rs.getInt("ID_Pianta"));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore caricamento colture terreno", e);
        }
        return result;
    }
}