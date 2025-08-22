package main.java.agriterra.data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.LandCultivationDAO;
import utils.DAOException;

public class LandCultivationDAOImpl implements LandCultivationDAO{
    private final Connection conn;

    public LandCultivationDAOImpl(Connection conn) {
        this.conn = conn;
    }
    public List<String> coltureInTerrenoAnno(int ID_Terreno, int anno) {
        List<String> result = new ArrayList<>();
        String sql = """
            SELECT P.nome, P.tipo P.descrizione
            FROM Pianta P
            JOIN Ciclo_Colturale C ON C.ID_Pianta = P.ID_Pianta
            WHERE C.ID_Terreno = ? AND C.anno = ?
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, ID_Terreno);
            st.setInt(2, anno);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("nome") + rs.getString("tipo")+ rs.getString("descrizione"));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore caricamento colture terreno", e);
        }
        return result;
    }
}