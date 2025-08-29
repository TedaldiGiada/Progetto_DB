package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agricola.data.CustomerImpl;
import agricola.data.api.Customer;
import agricola.data.api.dao.CustomerListDAO;
import agricola.data.utils.DAOException;

public class CustomerListDAOImpl implements CustomerListDAO {
    private final Connection conn;

    public CustomerListDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Customer> getAnnualCustomerList (int anno){
        List<Customer> result = new ArrayList<>();
        String sql = """
            SELECT DISTINCT C.CF, C.nome, C.cognome 
            FROM CLIENTE C
            JOIN Vendita V ON V.CF = C.CF
            WHERE YEAR(V.data) = ?
            """;
            try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, anno);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new CustomerImpl(
                    rs.getString("CF"), 
                    rs.getString("nome"),
                    rs.getString("cognome")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore lista compratori annui", e);
        }
        return result;
    }
}
