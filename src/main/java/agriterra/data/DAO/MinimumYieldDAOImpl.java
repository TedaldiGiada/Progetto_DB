import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.MinimumYieldDAO;
import utils.DAOException;

public class MinimumYieldDAOImpl implements MinimumYieldDAO{
    private final Connection conn;

    public MinimumYieldDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public List<String> terreniConRendimentoMin(String nome, double min) {
        List<String> result = new ArrayList<>();
        String sql = """
            SELECT T.ID_Terreno, C.rendimento
            FROM Terreno T
            JOIN Ciclo_Colturale C ON C.ID_Terreno = T.ID_Terreno
            JOIN Pianta P ON C.ID_Pianta = P.ID_Pianta
            WHERE P.nome = ? AND C.rendimento < ?
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nome);
            st.setDouble(2, min);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add("Terreno:" + rs.getString("ID_Terreno") + " rendimento: " + rs.getDouble("rendimento"));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore ricerca terreni minimi", e);
        }
        return result;
    }
}