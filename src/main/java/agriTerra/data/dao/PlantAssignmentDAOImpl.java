package agricola.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agricola.data.CropCycleImpl;
import agricola.data.api.CropCycle;
import agricola.data.api.dao.PlantAssignmentDAO;
import agricola.data.utils.DAOException;

public class PlantAssignmentDAOImpl implements PlantAssignmentDAO {
     private final Connection conn;

    public PlantAssignmentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertPlantAssignment(CropCycle cropCycle) {
        String checkOccupazione = """
        SELECT COUNT(*) 
        FROM Ciclo_Colturale 
        WHERE ID_Terreno = ?
            AND data_inizio <= ?
            AND data_fine >= ?
        """;

        try (PreparedStatement checkSt = conn.prepareStatement(checkOccupazione)) {
            checkSt.setInt(1, cropCycle.getIdTerreno());
            checkSt.setDate(2, cropCycle.getDataFine());   // fine nuovo ciclo
            checkSt.setDate(3, cropCycle.getDataInizio()); // inizio nuovo ciclo
            try (ResultSet rs = checkSt.executeQuery()) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    throw new DAOException("Errore: il terreno " + cropCycle.getIdTerreno() +
                    " è già occupato in questo periodo.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nella verifica disponibilità terreno", e);
        }
        String sql = "INSERT INTO Ciclo_Colturale(ID_Ciclo, ID_Pianta, anno, data_inizio, data_fine, rendimento, unità_misura, descrizione, ID_Terreno, ID_Vendita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, cropCycle.id());
            st.setInt(2, cropCycle.getIdPianta());
            st.setInt(3, cropCycle.getAnno());
            st.setDate(4, cropCycle.getDataInizio());
            st.setDate(5, cropCycle.getDataFine());
            st.setDouble(6, cropCycle.getRendimento());
            st.setString(7, cropCycle.getUnitaMisura());
            st.setString(8, cropCycle.getDescrizione());
            st.setInt(9, cropCycle.getIdTerreno());
            if (cropCycle.getIdVendita() == null) {
                st.setNull(10, java.sql.Types.INTEGER);
            } else {
                st.setInt(10, cropCycle.getIdVendita());
            }
            st.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Errore assegnazione della coltura al terreno", e);
        }
    }

    @Override
    public List<CropCycle> getCycle(){
         List<CropCycle> result = new ArrayList<>();
        String sql = """
            SELECT C.ID_Ciclo, C.anno, C.data_inizio, 
                   C.data_fine, C.rendimento, C.unità_misura, 
                   C.descrizione, C.ID_Terreno, C.ID_Pianta
            FROM  Ciclo_Colturale C
        """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) { 
                result.add(new CropCycleImpl(
                    rs.getInt("ID_Ciclo"),
                    rs.getInt("anno"), 
                    rs.getDate("data_inizio"),
                    rs.getDate("data_fine"), 
                    rs.getDouble("rendimento"),
                    rs.getString("unità_misura"), 
                    rs.getString("descrizione"),
                    rs.getInt("ID_Terreno"), 
                    rs.getInt("ID_Pianta")));
            }
        } catch (SQLException e){
            throw new DAOException("Errore caricamento colture terreno", e);
        }
        return result;
    }
}
