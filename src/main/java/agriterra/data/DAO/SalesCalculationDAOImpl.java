package agriterra.data.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.SalesCalculationDAO;
import agriterra.data.utils.DAOException;



public class SalesCalculationDAOImpl implements SalesCalculationDAO {
    private final Connection conn;

    public SalesCalculationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<String> calcoloVenditeAnnue(int anno) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(ID_Vendita) as totale FROM Vendita WHERE anno = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, anno);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result.add("Vendite effettuate nell'anno "+ anno + rs.getDouble("totale"));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore calcolo vendite annue", e);
        }
        return result;
    } 
}