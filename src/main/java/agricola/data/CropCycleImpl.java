package agricola.data;

import java.sql.Date;

import agricola.data.api.CropCycle;

public class CropCycleImpl implements CropCycle {

    private int id;
    private int anno;
    private double rendimento;
    private Date dataInizio;
    private Date dataFine;
    private String unitaMisura;
    private String descrizione;
    private int IdTerreno;
    private int IdPianta;
    private int IdVendita;


    public CropCycleImpl(int id, double rendimento) {
        this.id = id;
        this.rendimento = rendimento;
    }

    public CropCycleImpl(int id, int anno, Date dataInizio,
     Date dataFine, double rendimento, String unitaMisura,
     String descrizione, int IdTerreno, int IdPianta, int IdVendita) {
        this.id = id;
        this.anno = anno;
        this.rendimento = rendimento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.unitaMisura = unitaMisura;
        this.descrizione = descrizione;
        this.IdTerreno = IdTerreno;
        this.IdPianta = IdPianta;
        this.IdVendita = IdVendita;
     }

     public CropCycleImpl(int id, int anno, Date dataInizio,
     Date dataFine, double rendimento, String unitaMisura, String descrizione,
     int IdTerreno, int idPianta){
        this.id = id;
        this.anno = anno;
        this.rendimento = rendimento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.unitaMisura = unitaMisura;
        this.descrizione = descrizione;
        this.IdTerreno = IdTerreno;
        this.IdPianta = idPianta;
    }


    @Override
    public int id() {
        return this.id;
    }

    @Override
    public double getRendimento() {
        return this.rendimento;
    }

    @Override
    public Date getDataInizio() {
        return this.dataInizio;
    }

    @Override
    public Date getDataFine() {
        return this.dataFine;
    }

    @Override
    public String getUnitaMisura() {
        return this.unitaMisura;
    }

    @Override
    public String getDescrizione() {
        return this.descrizione;
    }

    @Override
    public int getIdTerreno() {
        return this.IdTerreno;
    }

    @Override
    public int getIdPianta() {
        return this.IdPianta;
    }

    @Override
    public int getIdVendita() {
        return this.IdVendita;
    }

    @Override
    public int getAnno() {
        return this.anno;
    }

    @Override
    public String toString() {
        return this.id + ";" + this.rendimento + ";" + this.dataInizio + ";"
         + this.dataFine + ";" + this.unitaMisura + ";" + this.descrizione + ";"
         + this.IdTerreno + ";" + this.IdPianta;
    }
}
