package agriterra.data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agriterra.data.api.MinimumYieldDAO;
import agriterra.data.utils.DAOException;



public class MinimumYieldDAOImpl implements MinimumYieldDAO{
    private final Connection conn;

    public MinimumYieldDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<String> terreniConRendimentoMin(String nome) {
        List<String> result = new ArrayList<>();
        String sql = """
            SELECT TOP(1) C.ID_Terreno, C.rendimento
            FROM Ciclo_Colturale
            JOIN Pianta P ON C.ID_Pianta = P.ID_Pianta
            WHERE P.nome = ?
            ORDERED BY C.rendimento ASC
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nome);
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