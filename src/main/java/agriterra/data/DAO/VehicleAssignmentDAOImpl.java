package main.java.agriterra.data.DAO;

import api.VehicleAssignmentDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import utils.DAOException;

public class VehicleAssignmentDAOImpl implements VehicleAssignmentDAO {
    private final Connection conn;

    public VehicleAssignmentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void assegnaDipendenteAMacchina(String CF, String ID_Macchinario, LocalDate data, String note) {
        String sql = "INSERT INTO AssegnazioneMacchinario(CF, ID_Macchinario, data, note) VALUES (?, ?, ?, ?)";
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
}