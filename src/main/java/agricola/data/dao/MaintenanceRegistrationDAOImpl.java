package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agricola.data.MaintenanceImpl;
import agricola.data.api.Maintenance;
import agricola.data.api.dao.MaintenanceRegistrationDAO;
import agricola.data.utils.DAOException;

public class MaintenanceRegistrationDAOImpl implements MaintenanceRegistrationDAO {
    private final Connection conn;

    public MaintenanceRegistrationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void registerMaintenance(Maintenance maintenance) {
        String sql = "INSERT INTO Manutenzione(ID_Manutenzione, azienda, descrizione, tipo, ID_Spesa, ID_Macchinario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, maintenance.getId());
            st.setString(2, maintenance.getAzienda());
            st.setString(3, maintenance .getDescrizione());
            st.setString(4, maintenance.getTipo());
            st.setInt(5, maintenance.getIdSpesa());
            st.setString(6, maintenance.getIdMacchinario());
            st.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Errore registrazione manutenzione", e);
        }
    }

    @Override
    public List<Maintenance> manutenzioni() {
        List<Maintenance> result = new ArrayList<>();
        String sql = """
                SELECT M.ID_Manutenzione, M.azienda, M.descrizione, M.tipo, M.ID_Spesa, M.ID_Macchinario
                FROM Manutenzione_Riparazione M
                """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new MaintenanceImpl(
                    rs.getInt("ID_Manutenzione"), 
                    rs.getString("azienda"), 
                    rs.getString("descrizione"), 
                    rs.getString("tipo"), 
                    rs.getInt("ID_Spesa"),
                    rs.getString("ID_Macchinario"))
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero della lista completa trattamenti", e);
        }
        return result;
    }
}
