package agriterra.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.VehicleAssignmentImpl;
import agriterra.data.api.VehicleAssignment;
import agriterra.data.api.dao.VehicleAssignmentDAO;
import agriterra.data.utils.DAOException;

public class VehicleAssignmentDAOImpl implements VehicleAssignmentDAO {

    private final Connection conn;

    public VehicleAssignmentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void assignVehicleToEmployee(VehicleAssignment assignment) {
         String sql = "INSERT INTO Assegnazione_Macchinario(CF, ID_Macchinario, data, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, assignment.getCf());
            st.setString(2, assignment.getIdMacchina());
            st.setDate(3,assignment.getData());
            st.setString(4, assignment.getNote());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Errore nell'assegnazione del veicolo", e);
        }
    }

    @Override
    public List<VehicleAssignment> assegnazioni() {
        List<VehicleAssignment> result = new ArrayList<>();
        String sql = """
                SELECT A.CF, A.ID_Macchinario, A.data, A.note
                FROM Assegnazione_Macchinario A
                """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new VehicleAssignmentImpl(
                    rs.getString("CF"), 
                    rs.getString("ID_Macchinario"),
                    rs.getDate("data"), 
                    rs.getString("Note")));
            }
        } catch (SQLException e){
            throw new DAOException("Errore nel recupero lista trattamenti",e);
        }
        return result;
    }
}


