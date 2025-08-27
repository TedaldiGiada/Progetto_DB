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
    public double calcoloVenditeAnnue(int anno) {
        double result = 0;
        String sql = "SELECT SUM(prezzo) as totale FROM Vendita WHERE anno = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, anno);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result= rs.getDouble("totale");
            }
        } catch (SQLException e) {
            throw new DAOException("Errore calcolo vendite annue", e);
        }
        return result;
    } 
    
}
