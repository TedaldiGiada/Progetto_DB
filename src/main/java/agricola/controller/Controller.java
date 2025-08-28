package agricola.controller;

import java.util.List;

import javax.swing.JPanel;

import agricola.data.api.CropCycle;
import agricola.data.api.Customer;
import agricola.data.api.Employee;
import agricola.data.api.Machinery;
import agricola.data.api.Maintenance;
import agricola.data.api.Plant;
import agricola.data.api.Treatment;
import agricola.data.api.VehicleAssignment;
import agricola.data.utils.Rule;
import agricola.model.Model;
import agricola.view.AdminView;
import agricola.view.LoginView;
import agricola.view.ManagerView;
import agricola.view.SellerView;
import agricola.view.View;

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

    public double getAnnualSalesCalculation (int anno){
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
