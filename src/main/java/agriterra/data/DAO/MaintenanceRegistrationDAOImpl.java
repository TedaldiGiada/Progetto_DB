package data.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import data.api.MaintenanceRegistrationDAO;
import utils.DAOException;

public class MaintenanceRegistrationDAOImpl implements MaintenanceRegistrationDAO {
    private final Connection conn;

    public MaintenanceRegistrationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void registraManutenzione(int ID_Manutenzione, String azienda, String descrizione, String tipo, int ID_Spesa, String ID_Macchinario) {
        String sql = "INSERT INTO Manutenzione(ID_Manutenzione, azienda, descrizione, tipo, ID_Spesa, ID_Macchinario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, ID_Manutenzione);
            st.setString(2, azienda);
            st.setString(3, descrizione);
            st.setString(2, tipo);
            st.setInt(3, ID_Spesa);
            st.setString(2, ID_Macchinario);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Errore registrazione manutenzione", e);
    }
}
}