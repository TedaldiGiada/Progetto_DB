package agriterra.data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.LandCultivationDAO;
import agriterra.data.utils.DAOException;

public class LandCultivationDAOImpl implements LandCultivationDAO{
    private final Connection conn;

    public LandCultivationDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<String> coltureInTerrenoAnno(int ID_Terreno, int anno) {
        List<String> result = new ArrayList<>();
        String sql = """
            SELECT C.ID_Pianta
            FROM CICLO_COLTURALE C
            WHERE C.ID_Terreno = ? 
                AND YEAR(C.data_inizio) = ?
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, ID_Terreno);
            st.setInt(2, anno);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add("ID_Pianta" + rs.getInt("ID_Pianta"));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore caricamento colture terreno", e);
        }
        return result;
    }
}