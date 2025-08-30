package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agricola.data.CropCycleImpl;
import agricola.data.api.CropCycle;
import agricola.data.api.dao.LowYieldLandsDAO;
import agricola.data.utils.DAOException;

public class LowYieldLandsDAOImpl implements LowYieldLandsDAO {
    private final Connection conn;

    public LowYieldLandsDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<CropCycle> getLowYieldLands(String nome){
        List<CropCycle> result = new ArrayList<>();
        String sql = """
            SELECT C.ID_Terreno, C.rendimento
            FROM CICLO_COLTURALE C
            JOIN Pianta P ON C.ID_Pianta = P.ID_Pianta
            WHERE P.nome = ?
            ORDER BY C.rendimento ASC
            LIMIT 5
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new CropCycleImpl(rs.getInt("ID_Terreno"), rs.getDouble("rendimento")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore ricerca terreni con rendimento più basso", e);
        }
        return result;
    }
}