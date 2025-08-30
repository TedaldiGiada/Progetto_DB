package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import agricola.data.api.dao.AnnualSalesCalculationDAO;
import agricola.data.utils.DAOException;

public class AnnualSalesCalculationDAOImpl implements AnnualSalesCalculationDAO {

    private final Connection conn;

    public AnnualSalesCalculationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public String calcoloVenditeAnnue(int anno) {
        String result = "";
        String sql = "SELECT SUM(prezzo) as totale FROM Vendita WHERE YEAR(data) = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, anno);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                double totale = rs.getDouble("totale"); // prende il totale
                result = "Ricavo Totale nell'anno " + anno + " = " + totale;
            }
        } catch (SQLException e) {
            throw new DAOException("Errore calcolo vendite annue", e);
        }
        return result;
    } 
    
}
