package agriterra.controller;

import java.util.List;

import javax.swing.JPanel;

import agriterra.data.api.CropCycle;
import agriterra.data.api.Customer;
import agriterra.data.api.Employee;
import agriterra.data.api.Machinery;
import agriterra.data.api.Maintenance;
import agriterra.data.api.Plant;
import agriterra.data.api.Treatment;
import agriterra.data.api.VehicleAssignment;
import agriterra.data.utils.Rule;
import agriterra.model.Model;
import agriterra.view.AdminView;
import agriterra.view.LoginView;
import agriterra.view.ManagerView;
import agriterra.view.SellerView;
import agriterra.view.View;

public class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view){
        this.view = view;
        this.model = model;
    }

    public JPanel checkPanel(String username, String password) {
    Rule role = model.logIn(username, password);
        return switch (role) {
            case ADMIN -> new AdminView(this);
            case SELLER -> new SellerView(this);
            case MANAGER -> new ManagerView(this);
            default -> new LoginView(this);
        };
}

    public void setView(JPanel panel) {
        this.view.addPanel(panel);
    }

    public List<Plant> getAnnualLandCultivation (int idTerreno, int anno){
        return this.model.getAnnualLandCultivation(idTerreno, anno);
    }

    public String getAnnualSalesCalculation (int anno){
        return this.model.getAnnualSalesCalculation(anno);
    }

    public List<Treatment> getAnnualTreatments(int anno){
        return this.model.getAnnualTreatments(anno);
    }

    public List<Customer> getAnnualCustomerList (int anno){
        return this.model.getAnnualCustomerList(anno);
    }

    public List<Machinery> usedMachineryCycle(int idCiclo){
        return this.model.usedMachineryCycle(idCiclo);
    }

    public List<CropCycle> getLowYieldLands(String nome){
        return this.model.getLowYieldLands(nome);
    }

    public void registerMaintenance(Maintenance maintenance) {
        this.model.registerMaintenance(maintenance);
    }

    public List<Maintenance> getMaintenances(){
        return this.model.getMaintenances();
    }

    public void insertPlantAssignment(CropCycle cropCycle){
        this.model.insertPlantAssignment(cropCycle);
    }

    public List<CropCycle> getCycle(){
        return this.model.getCycle();
    }

    public void registerEmployee(Employee employee){
        this.model.registerEmployee(employee);
    }

    public List<Employee> getEmployeeList(){
        return this.model.getEmployeeList();
    }

    public void assignVehicleToEmployee(VehicleAssignment assignment){
        this.model.assignVehicleToEmployee(assignment);
    }

    public List<VehicleAssignment> assignment(){
        return this.model.assegnment();
    }
    
}
