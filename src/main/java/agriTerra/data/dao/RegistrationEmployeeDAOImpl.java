package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import agricola.data.EmployeeImpl;
import agricola.data.api.Employee;
import agricola.data.api.dao.RegistrationEmployeeDAO;
import agricola.data.utils.DAOException;

public class RegistrationEmployeeDAOImpl implements RegistrationEmployeeDAO {
    private final Connection conn;

    public RegistrationEmployeeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void registerEmployee(Employee employee) {
        String sql = "INSERT INTO Dipendente(CF, nome, cognome, telefono, via, num_civ, città, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, employee.getCf());
            st.setString(2, employee.getNome());
            st.setString(3, employee.getCognome());
            st.setLong(4, employee.getTelefono());
            st.setString(5, employee.getVia());
            st.setInt(6, employee.getNumCiv());
            st.setString(7, employee.getCitta());
            st.setString(8, employee.getNote());

            st.executeUpdate();
        } catch (Exception e){
        throw new DAOException("Errore registrazione dipendente", e);
        }
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT CF, nome, cognome, telefono, via, num_civ, città, note FROM Dipendente";
        try (PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                result.add(new EmployeeImpl(
                    rs.getString("CF"),
                    rs.getString("nome"), 
                    rs.getString("cognome"),
                    rs.getLong("telefono"), 
                    rs.getString("via"), 
                    rs.getInt("num_civ"),
                    rs.getString("città"), 
                    rs.getString("note")));
            }
        } catch (Exception e) {
            throw new DAOException("Errore caricamento dipendenti", e);
        }
        return result;
    }
}

