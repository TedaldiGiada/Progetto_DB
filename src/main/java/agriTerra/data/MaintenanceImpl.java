package agriterra.data;

import agriterra.data.api.Maintenance;

public class MaintenanceImpl implements Maintenance {
    private final int id;
    private final String azienda;
    private final String descrizione;
    private final String tipo;
    private final int idSpesa;
    private final String idMacchinario;

    public MaintenanceImpl(int id, String azienda, String descrizione, String tipo, int idSpesa, String idMacchinario) {
        this.id = id;
        this.azienda = azienda;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.idSpesa = idSpesa;
        this.idMacchinario = idMacchinario;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getAzienda() {
        return this.azienda;
    }

    @Override
    public String getDescrizione() {
        return this.descrizione;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public int getIdSpesa() {
        return this.idSpesa;
    }

    @Override
    public String getIdMacchinario() {
        return this.idMacchinario;
    }

    @Override
    public String toString(){
        return this.id + ";" + this.azienda + ";" + this.descrizione 
        + ";" + this.tipo + ";" + this.idSpesa + ";" + this.idMacchinario;
    }



}
