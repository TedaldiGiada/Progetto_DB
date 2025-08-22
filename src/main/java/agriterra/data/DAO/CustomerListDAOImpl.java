package ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.CustomerListDAO;
import utils.DAOException;

public class CustomerListDAOImpl implements CustomerListDAO{
     private final Connection conn;

    public CustomerListDAOImpl(Connection conn) {
        this.conn = conn;
    }
    public List<String> listaCompratoriAnnui(int anno) {
        List<String> result = new ArrayList<>();
        String sql = """
            SELECT ID_Cliente, nome, cognome 
            FROM Cliente C
            JOIN Vendita V ON V.ID_Cliente = C.ID_Cliente
            WHERE anno = ?
                    
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, anno);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add("ID:" + rs.getString("ID_Cliente")
                                + "Nome:" + rs.getString("nome")
                                + "Cognome:" + rs.getString("cognome"));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore lista compratori annui", e);
        }
        return result;
    }
}