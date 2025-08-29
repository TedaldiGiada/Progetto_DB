package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agricola.data.PlantImpl;
import agricola.data.api.Plant;
import agricola.data.api.dao.AnnualLandCultivationDAO;
import agricola.data.utils.DAOException;

public class AnnualLandCultivationDAOImpl implements AnnualLandCultivationDAO {
    private final Connection conn;

    public AnnualLandCultivationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Plant> getAnnualLandCultivation (int idTerreno, int anno){
        List<Plant> result = new ArrayList<>();
        String sql = """
            SELECT P.ID_Pianta, P.nome 
            FROM PIANTA P 
            JOIN CICLO_COLTURALE CC ON P.ID_Pianta = CC.ID_Pianta 
            WHERE CC.ID_Terreno = ? 
            AND C.anno = ?
            """;
            try(PreparedStatement st = conn.prepareStatement(sql)){
                st.setInt(1, idTerreno);
                st.setInt(2, anno);
                ResultSet rs = st.executeQuery();
                while (rs.next()){
                    result.add(new PlantImpl(rs.getInt("ID_Pianta"), rs.getString("nome")));
                }
            } catch (SQLException e) {
                throw new DAOException("Errore caricamento colture terreno", e);
            }
            return result;
        }
    }
