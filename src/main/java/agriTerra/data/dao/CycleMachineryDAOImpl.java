package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agricola.data.MachineryImpl;
import agricola.data.api.Machinery;
import agricola.data.api.dao.CycleMachineryDAO;
import agricola.data.utils.DAOException;

public class CycleMachineryDAOImpl implements CycleMachineryDAO {
    private final Connection conn;

    public CycleMachineryDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Machinery> usedMachineryCycle(int idCiclo){
    List<Machinery> result = new ArrayList<>();
    String sql = """
        SELECT M.ID_Macchinario, M.nome, M.marca_modello
        FROM Macchinario M
        JOIN Utilizzo_Macchinario UM ON UM.ID_Macchinario = M.ID_Macchinario
        WHERE UM.ID_Ciclo = ?
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idCiclo);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                result.add(new MachineryImpl(
                    rs.getString("ID_Macchinario"),
                    rs.getString("nome"), 
                    rs.getString("marca_modello")));
            }
        } catch (SQLException e) {
            throw new DAOException("Errore caricamento macchinari ciclo", e);
        }
    return result;
    }
}
