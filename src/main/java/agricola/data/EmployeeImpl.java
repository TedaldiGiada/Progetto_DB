package agricola.data;

import agricola.data.api.Employee;

public class EmployeeImpl implements Employee {

    private String cf;
    private String nome;
    private String cognome;
    private long telefono;
    private String via;
    private int numCiv;
    private String citta;
    private String note;

    public EmployeeImpl(String cf, String nome, String cognome, long telefono, String via, int numCiv, String citta, String note) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.via = via;
        this.numCiv = numCiv;
        this.citta = citta;
        this.note = note;
    }

    @Override
    public String getCf() {
        return this.cf;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getCognome() {
        return this.cognome;
    }

    @Override
    public long getTelefono() {
        return this.telefono;
    }

    @Override
    public String getVia() {
        return this.via;
    }

    @Override
    public int getNumCiv() {
        return this.numCiv;
    }

    @Override
    public String getCitta() {
        return this.citta;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    @Override
    public String toString(){
        return this.cf + ";" + this.nome + ";" 
        + this.cognome + ";" + this.telefono + ";"
        + this.via + ";" + this.numCiv + ";"
        + this.citta + ";" + this.note;
    }


}
