package main.java.agriterra.data.DAO;

import api.RegistrationEmployeeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DAOException;

public class RegistrationEmployeeDAOImpl implements RegistrationEmployeeDAO{
  
    private final Connection conn;
    
    public RegistrationEmployeeDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    public void registraDipendente(String CF, String nome, String cognome, int telefono, String via, String num_civ, String città, String note) {
        String sql = "INSERT INTO Dipendente(nome, cognome, ruolo) VALUES (?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, CF);
            st.setString(2, nome);
            st.setString(3, cognome);
            st.setInt(4, telefono);
            st.setString(5, via);
            st.setString(6, num_civ);
            st.setString(7, città);
            st.setString(8, note);
            st.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("Errore registrazione dipendente", e);
        }
    }
    
    public List<String> listaDipendenti() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT CF, nome, cognome, telefono, via, num_civ, città, note FROM Dipendente";
        try (PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getInt("CF") + " - " +
                    rs.getString("nome") + " " +
                    rs.getString("cognome") + " (" +
                    rs.getString("telefono")+ " (" +
                    rs.getString("via") + " (" +
                    rs.getString("num_civ") + " (" +
                    rs.getString("città") + " (" +
                    rs.getString("note") + ")");
            }
        } catch (Exception e) {
            throw new DAOException("Errore caricamento dipendenti", e);
        }
        return result;
    }
}
