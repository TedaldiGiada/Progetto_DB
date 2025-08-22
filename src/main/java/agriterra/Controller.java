import java.util.Objects;

import javax.swing.JOptionPane;

import main.java.agriterra.model.Model;
import main.java.agriterra.view.LoginView;

public class Controller {
    private final Model model;
    private final LoginView view;

    public Controller(Model model, LoginView view) {
        Objects.requireNonNull(model, "Controller created with null model");
        Objects.requireNonNull(view, "Controller created with null view");
        this.view = view;
        this.model = model;
    }

    public void initialScene() {
        this.view.initializeGUI();
    }

    public void check() {
        if (view.getUsername() == "admin" && view.getPassword() == "admin123") {
            AdminView admin = new AdminView();
            admin.initializeGUI();
            
        } else if (view.getUsername() == "vendite" && view.getPassword() == "vendite123") {
            SellerView seller = new SellerView();
            seller.initializeGUI();
        } else if (view.getUsername() == "campo" && view.getPassword() == "campo123") {
            ManagerView manager = new ManagerView();
            manager.initializeGUI();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
            this.view.clearFields();
        }
    }


}