package agriterra.data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.MaintenanceRegistrationDAO;
import agriterra.data.utils.DAOException;



public class MaintenanceRegistrationDAOImpl implements MaintenanceRegistrationDAO {
    private final Connection conn;

    public MaintenanceRegistrationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void registraManutenzione(int ID_Manutenzione, String azienda, String descrizione, String tipo, int ID_Spesa, String ID_Macchinario) {
        String sql = "INSERT INTO Manutenzione(ID_Manutenzione, azienda, descrizione, tipo, ID_Spesa, ID_Macchinario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, ID_Manutenzione);
            st.setString(2, azienda);
            st.setString(3, descrizione);
            st.setString(4, tipo);
            st.setInt(5, ID_Spesa);
            st.setString(6, ID_Macchinario);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Errore registrazione manutenzione", e);
        }
    }

    @Override
    public List<String> manutenzioni() {
        List<String> result = new ArrayList<>();
        String sql = """
                SELECT M.ID_Manutenzione, M.azienda, M.descrizione, M.tipo, M.ID_Spesa, M.ID_Macchinario
                FROM Manutenzione_Riparazione M
                """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(
                    rs.getInt("ID_Manutenzione") + ";" +
                    rs.getDate("azienda") + ";" +
                    rs.getString("descrizione") + ";" +
                    rs.getString("tipo") + ";" +
                    rs.getInt("ID_Spesa") + ";" +
                    rs.getString("ID_Macchinario")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero della lista completa trattamenti", e);
        }
        return result;
    }
}
