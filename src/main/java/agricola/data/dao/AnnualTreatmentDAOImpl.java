package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import agricola.data.TreatmentImpl;
import agricola.data.api.Treatment;
import agricola.data.api.dao.AnnualTreatmentDAO;
import agricola.data.utils.DAOException;

public class AnnualTreatmentDAOImpl implements AnnualTreatmentDAO {
    private final Connection conn;

    public AnnualTreatmentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Treatment> getAnnualTreatments(int anno) {
        List<Treatment> result = new ArrayList<>();
        String sql = """
            SELECT T.*
            FROM TRATTAMENTO T
            JOIN ASSEGNAZIONE_TRATTAMENTO AT ON T.ID_TRATTAMENTO = AT.ID_TRATTAMENTO
            WHERE YEAR(AT.data) = ?
            """;
            try (PreparedStatement st = conn.prepareStatement(sql)){
                st.setInt(1, anno);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    result.add(new TreatmentImpl(rs.getInt("ID_Trattamento"),
                    rs.getDate("data"),
                    rs.getString("tipo"),
                    rs.getString("descizione")));
                }
            } catch (Exception e) {
                throw new DAOException("Errore lista trattamenti annui", e);
            }
            return result;
        }
    }
