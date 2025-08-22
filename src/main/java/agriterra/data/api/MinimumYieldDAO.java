package agriterra.data.api;

import java.util.List;

public interface MinimumYieldDAO {
    List<String> terreniConRendimentoMin(String nome, double min);
}
