package agricola.data;

import agricola.data.api.Customer;

public class CustomerImpl implements Customer {
    private String cf;
    private String nome;
    private String cognome;

    public CustomerImpl(String cf, String nome, String cognome) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
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
    public String toString(){
        return this.cf + ";" + this.nome + ";" + this.cognome;
    }
    
}
