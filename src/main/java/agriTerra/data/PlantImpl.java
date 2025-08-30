package agricola.data;

import agricola.data.api.Plant;
 
public class PlantImpl implements Plant {
    private final int id;
    private final String nome;

    public PlantImpl(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return this.id + ";" + this.nome;
    }


    
}
