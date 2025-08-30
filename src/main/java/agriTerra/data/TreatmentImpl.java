package agricola.data;
import java.sql.Date;

import agricola.data.api.Treatment;

public class TreatmentImpl implements Treatment {
    private final int id;
    private final Date data;
    private final String tipo;
    private String descrizione;

    public TreatmentImpl(int id, Date data, String tipo, String descrizione) {
        this.id = id;
        this.data = data;
        this.tipo = tipo;
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public Date getData(){
        return this.data;
    }

    @Override
    public String getTipo(){
        return this.tipo;
    }

    @Override
    public String getDescrizione(){
        return this.descrizione;
    }

    @Override
    public String toString(){
        return this.id + ";" + this.data + ";" + this.tipo + ";" + this.descrizione;
    }

}
