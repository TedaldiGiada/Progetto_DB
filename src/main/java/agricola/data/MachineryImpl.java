package agricola.data;

import agricola.data.api.Machinery;

public class MachineryImpl implements Machinery {
    private String id;
    private String nome;
    private String marcaModello;

    public MachineryImpl(String id, String nome, String marcaModello) {
        this.id = id;
        this.nome = nome;
        this.marcaModello = marcaModello;
    }
    
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getMarcaModello() {
        return this.marcaModello;
    }

    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.marcaModello ;
    }


}
