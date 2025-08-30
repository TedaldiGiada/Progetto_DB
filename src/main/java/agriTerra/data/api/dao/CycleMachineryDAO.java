package agriterra.data.api.dao;

import java.util.List;

import agriterra.data.api.Machinery;

public interface CycleMachineryDAO {

    List<Machinery> usedMachineryCycle(int idCiclo);
}
