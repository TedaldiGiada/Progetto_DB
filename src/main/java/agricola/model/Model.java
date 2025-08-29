package agricola.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import agricola.data.api.CropCycle;
import agricola.data.api.Customer;
import agricola.data.api.Employee;
import agricola.data.api.Machinery;
import agricola.data.api.Maintenance;
import agricola.data.api.Plant;
import agricola.data.api.Treatment;
import agricola.data.api.VehicleAssignment;
import agricola.data.api.dao.AnnualLandCultivationDAO;
import agricola.data.api.dao.AnnualSalesCalculationDAO;
import agricola.data.api.dao.AnnualTreatmentDAO;
import agricola.data.api.dao.CustomerListDAO;
import agricola.data.api.dao.CycleMachineryDAO;
import agricola.data.api.dao.LowYieldLandsDAO;
import agricola.data.api.dao.MaintenanceRegistrationDAO;
import agricola.data.api.dao.PlantAssignmentDAO;
import agricola.data.api.dao.RegistrationEmployeeDAO;
import agricola.data.api.dao.VehicleAssignmentDAO;
import agricola.data.dao.AnnualLandCultivationDAOImpl;
import agricola.data.dao.AnnualSalesCalculationDAOImpl;
import agricola.data.dao.AnnualTreatmentDAOImpl;
import agricola.data.dao.CustomerListDAOImpl;
import agricola.data.dao.CycleMachineryDAOImpl;
import agricola.data.dao.LowYieldLandsDAOImpl;
import agricola.data.dao.MaintenanceRegistrationDAOImpl;
import agricola.data.dao.PlantAssignmentDAOImpl;
import agricola.data.dao.RegistrationEmployeeDAOImpl;
import agricola.data.dao.VehicleAssignmentDAOImpl;
import agricola.data.utils.Rule;
import agricola.data.utils.User;

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