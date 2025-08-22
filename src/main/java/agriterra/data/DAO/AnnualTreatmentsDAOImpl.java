package agriterra.data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.AnnualTreatmentsDAO;
import agriterra.data.utils.DAOException;

public class AnnualTreatmentsDAOImpl implements AnnualTreatmentsDAO{
    private final Connection conn;

    public AnnualTreatmentsDAOImpl(Connection conn) {
        this.conn = conn;
}

    public List<String> listaTrattamentiAnnui(int anno){
        List<String> result = new ArrayList<>();
        String sql = """
            SELECT T.*
            FROM TRATTAMENTO T
            JOIN ASSEGNAZIONE_TRATTAMENTO AT ON T.ID_TRATTAMENTO = AT.ID_TRATTAMENTO
            WHERE YEAR(AT.data) = ?
                
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, anno);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add("ID: " + rs.getString ("ID_Trattamento")
                        + "Data: " + rs.getDate("data")
                        + "Quantità: " + rs.getString("qta")
                        + "Tipo: " + rs.getString("tipo")
                        + "Descrizione: " + rs.getString("descrizione")
                        + "ID_Spesa: " + rs.getInt("ID_Spesa"));
            } 
        } catch (SQLException e) {
            throw new DAOException("Errore lista trattamenti annui", e);
        }
        return result;
    }
}
