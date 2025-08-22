package main.java.agriterra.data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.agruterrra.api.UsedVehiclesDAO;
import utils.DAOException;

public class UsedVehiclesDAOImpl implements UsedVehiclesDAO{
    private final Connection conn;

    public UsedVehiclesDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public List<String> macchinariUsatiInCiclo(int ID_Ciclo) {
    List<String> result = new ArrayList<>();
    String sql = """
        SELECT M:ID_Macchinario, M.nome, M.marca_modello
        FROM Macchinario M
        JOIN AssegnazioneMacchinario A ON A.ID_Macchinario = M.ID_Macchinario
        WHERE A.coltivazione_id = ?
    """;
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, ID_Ciclo);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            result.add(rs.getString("ID_Macchinario") + "-" + rs.getString("nome") + " - " + rs.getString("marca_modello"));
        }
    } catch (SQLException e) {
        throw new DAOException("Errore caricamento macchinari ciclo", e);
    }
    return result;
}
}