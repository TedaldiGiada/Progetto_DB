package main.java.agruterrra.api;

import java.util.List;

public interface MinimumYieldDAO {
    List<String> terreniConRendimentoMin(String nome, double min);
}
