package agriterra.data;

import java.sql.Date;

import agriterra.data.api.VehicleAssignment;

public class VehicleAssignmentImpl implements VehicleAssignment {
    private String cF;
    private String idMacchina;
    private Date data;
    private String note;

    public VehicleAssignmentImpl(String cF, String idMacchina, Date data, String note) {
        this.cF = cF;
        this.idMacchina = idMacchina;
        this.data = data;
        this.note = note;
    }

    @Override
    public String getCf() {
        return this.cF;
    }

    @Override
    public String getIdMacchina() {
        return this.idMacchina;
    }

    @Override
    public Date getData() {
        return this.data;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    @Override
    public String toString(){
        return this.cF + ";" + this.idMacchina + ";" + this.data + ";" + this.note;
    }

    
}
