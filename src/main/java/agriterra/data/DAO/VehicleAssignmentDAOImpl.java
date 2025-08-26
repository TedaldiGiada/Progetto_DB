package agriterra.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.VehicleAssignmentDAO;
import agriterra.data.utils.DAOException;



public class VehicleAssignmentDAOImpl implements VehicleAssignmentDAO {
    private final Connection conn;

    public VehicleAssignmentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void assegnaDipendenteAMacchina(String CF, String ID_Macchinario, LocalDate data, String note) {
        String sql = "INSERT INTO Assegnazione_Macchinario(CF, ID_Macchinario, data, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, CF);
            st.setString(2, ID_Macchinario);
            st.setDate(3, Date.valueOf(data));
            st.setString(4, note);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Errore assegnazione dipendente a macchina", e);
        }
    }

    @Override
    public List<String> assegnazioni() {
        List<String> result = new ArrayList<>();
        String sql = """
                SELECT A.CF, A.ID_Macchinario, A.data, A.note
                FROM Assegnazione_Macchinario A
                """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(
                    rs.getString("CF") + ";" +
                    rs.getString("ID_Macchinario") + ";" +
                    rs.getDate("data") + ";" +
                    rs.getString("note")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero della lista completa trattamenti", e);
        }    
        return result;
    }
}