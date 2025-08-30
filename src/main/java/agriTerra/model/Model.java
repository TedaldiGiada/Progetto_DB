package agriterra.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import agriterra.data.api.CropCycle;
import agriterra.data.api.Customer;
import agriterra.data.api.Employee;
import agriterra.data.api.Machinery;
import agriterra.data.api.Maintenance;
import agriterra.data.api.Plant;
import agriterra.data.api.Treatment;
import agriterra.data.api.VehicleAssignment;
import agriterra.data.api.dao.AnnualLandCultivationDAO;
import agriterra.data.api.dao.AnnualSalesCalculationDAO;
import agriterra.data.api.dao.AnnualTreatmentDAO;
import agriterra.data.api.dao.CustomerListDAO;
import agriterra.data.api.dao.CycleMachineryDAO;
import agriterra.data.api.dao.LowYieldLandsDAO;
import agriterra.data.api.dao.MaintenanceRegistrationDAO;
import agriterra.data.api.dao.PlantAssignmentDAO;
import agriterra.data.api.dao.RegistrationEmployeeDAO;
import agriterra.data.api.dao.VehicleAssignmentDAO;
import agriterra.data.dao.AnnualLandCultivationDAOImpl;
import agriterra.data.dao.AnnualSalesCalculationDAOImpl;
import agriterra.data.dao.AnnualTreatmentDAOImpl;
import agriterra.data.dao.CustomerListDAOImpl;
import agriterra.data.dao.CycleMachineryDAOImpl;
import agriterra.data.dao.LowYieldLandsDAOImpl;
import agriterra.data.dao.MaintenanceRegistrationDAOImpl;
import agriterra.data.dao.PlantAssignmentDAOImpl;
import agriterra.data.dao.RegistrationEmployeeDAOImpl;
import agriterra.data.dao.VehicleAssignmentDAOImpl;
import agriterra.data.utils.Rule;
import agriterra.data.utils.User;

public class Model {
    private final AnnualLandCultivationDAO annualLandCult;
    private final AnnualSalesCalculationDAO annualSalesCalc;
    private final AnnualTreatmentDAO annualTrat;
    private final CustomerListDAO customerList;
    private final CycleMachineryDAO cycleMach;
    private final LowYieldLandsDAO lowYieldLands;
    private final MaintenanceRegistrationDAO maintananceReg;
    private final PlantAssignmentDAO plantAssign;
    private final RegistrationEmployeeDAO regEmpl;
    private final VehicleAssignmentDAO vehicleAssign;
    private final List<User> users;
    
    public Model(Connection conn){
        Objects.requireNonNull(conn, "Model created with null connection");
        this.annualLandCult = new AnnualLandCultivationDAOImpl(conn);
        this.annualSalesCalc = new AnnualSalesCalculationDAOImpl(conn);
        this.annualTrat = new AnnualTreatmentDAOImpl(conn);
        this.customerList = new CustomerListDAOImpl(conn);
        this.cycleMach = new CycleMachineryDAOImpl(conn);
        this.lowYieldLands = new LowYieldLandsDAOImpl(conn);
        this.maintananceReg = new MaintenanceRegistrationDAOImpl(conn);
        this.plantAssign = new PlantAssignmentDAOImpl(conn);
        this.regEmpl = new RegistrationEmployeeDAOImpl(conn);
        this.vehicleAssign = new VehicleAssignmentDAOImpl(conn);
        this.users = new ArrayList<>();
    }

    public boolean register(String username, String password, Rule rule){
        for (User u : users){
            if (u.getUsername().equals(username)){
                return false;
            }
        }
        users.add(new User(username, password, rule));
        return true;
    }

    public Rule logIn(String username, String password) {
        for (User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u.getRuolo();
            }
        }
        return null;
    }

    public List<Plant> getAnnualLandCultivation (int idTerreno, int anno){
        return this.annualLandCult.getAnnualLandCultivation(idTerreno, anno);
    }

    public String getAnnualSalesCalculation (int anno){
        return this.annualSalesCalc.calcoloVenditeAnnue(anno);
    }

    public List<Treatment> getAnnualTreatments(int anno){
        return this.annualTrat.getAnnualTreatments(anno);
    }

    public List<Customer> getAnnualCustomerList (int anno){
        return this.customerList.getAnnualCustomerList(anno);
    }

    public List<Machinery> usedMachineryCycle(int idCiclo){
        return this.cycleMach.usedMachineryCycle(idCiclo);
    }

    public List<CropCycle> getLowYieldLands(String nome){
        return this.lowYieldLands.getLowYieldLands(nome);
    }

    public void registerMaintenance(Maintenance maintenance) {
        this.maintananceReg.registerMaintenance(maintenance);
    }

    public List<Maintenance> getMaintenances(){
        return this.maintananceReg.manutenzioni();
    }

    public void insertPlantAssignment(CropCycle cropCycle){
        this.plantAssign.insertPlantAssignment(cropCycle);
    }

    public List<CropCycle> getCycle(){
        return this.plantAssign.getCycle();
    }

    public void registerEmployee(Employee employee){
        this.regEmpl.registerEmployee(employee);
    }

    public List<Employee> getEmployeeList(){
        return this.regEmpl.getEmployeeList();
    }

    public void assignVehicleToEmployee(VehicleAssignment assignment){
        this.vehicleAssign.assignVehicleToEmployee(assignment);
    }

    public List<VehicleAssignment> assegnment(){
        return this.vehicleAssign.assegnazioni();
    }


}