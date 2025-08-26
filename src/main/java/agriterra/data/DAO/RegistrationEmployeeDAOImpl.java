package agriterra.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.RegistrationEmployeeDAO;
import agriterra.data.utils.DAOException;



public class RegistrationEmployeeDAOImpl implements RegistrationEmployeeDAO{
  
    private final Connection conn;
    
    public RegistrationEmployeeDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void registraDipendente(String CF, String nome, String cognome, long telefono, String via, int num_civ, String città, String note) {
        String sql = "INSERT INTO Dipendente(CF, nome, cognome, telefono, via, num_civ, città, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, CF);
            st.setString(2, nome);
            st.setString(3, cognome);
            st.setLong(4, telefono);
            st.setString(5, via);
            st.setInt(6, num_civ);
            st.setString(7, città);
            st.setString(8, note);
            st.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("Errore registrazione dipendente", e);
        }
    }
    
    @Override
    public List<String> listaDipendenti() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT CF, nome, cognome, telefono, via, num_civ, città, note FROM Dipendente";
        try (PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("CF") + ";" +
                    rs.getString("nome") + ";" +
                    rs.getString("cognome") + ";" +
                    rs.getLong("telefono")+ ";" +
                    rs.getString("via") + ";" +
                    rs.getInt("num_civ") + ";" +
                    rs.getString("città") + ";" +
                    rs.getString("note"));
            }
        } catch (Exception e) {
            throw new DAOException("Errore caricamento dipendenti", e);
        }
        return result;
    }
}
