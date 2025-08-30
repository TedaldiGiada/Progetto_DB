package agricola.data.api.dao;

import java.util.List;

import agricola.data.api.Machinery;

public interface CycleMachineryDAO {

    List<Machinery> usedMachineryCycle(int idCiclo);
}
