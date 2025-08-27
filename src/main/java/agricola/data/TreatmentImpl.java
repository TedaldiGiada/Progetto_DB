package agricola.data;
import java.sql.Date;

import agricola.data.api.Treatment;

public class TreatmentImpl implements Treatment {
    private int id;
    private Date data;
    private int quantita;
    private String tipo;
    private String descrizione;
    private int idSpesa;

    public TreatmentImpl(int id, Date data, int quantita, String tipo, String descrizione, int idSpesa) {
        this.id = id;
        this.data = data;
        this.quantita = quantita;
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
    public int getQuantita(){
        return this.quantita;
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
    public int getIdSpesa(){
        return this.idSpesa;
    }

    @Override
    public String toString(){
        return this.id + ";" + this.data + ";" + this.tipo + ";" + this.descrizione;
    }

}
