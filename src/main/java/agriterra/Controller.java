package agriterra;

import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import agriterra.model.Model;
import agriterra.view.AdminView;
import agriterra.view.LoginView;
import agriterra.view.ManagerView;
import agriterra.view.SellerView;

public class Controller {
    @SuppressWarnings("unused")
    private final Model model;
    private final LoginView view;

    public Controller( Model model, LoginView view) {
        Objects.requireNonNull(model, "Controller created with null model");
        Objects.requireNonNull(view, "Controller created with null view");
        this.view = view;
        this.model = model;
    }

    public void initialScene() {
        this.view.initializeGUI();
        this.view.display();
    }

    public void check() {
        String username = view.getUsername();
        String password = view.getPassword();

        if ("admin".equals(username) && "admin123".equals(password)) {
            AdminView admin = new AdminView(model);
            JFrame adminFrame = new JFrame("Pannello Amministratore");
            adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adminFrame.setContentPane(admin);
            adminFrame.setSize(900, 600);
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);
            view.dispose();
        } else if ("vendite".equals(username) && "vendite123".equals(password)) {
            SellerView seller = new SellerView(model);
            JFrame sellerFrame = new JFrame("Pannello Amministratore");
            sellerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sellerFrame.setContentPane(seller);
            sellerFrame.setSize(900, 600);
            sellerFrame.setLocationRelativeTo(null);
            sellerFrame.setVisible(true);
            view.dispose();
        } else if ("campo".equals(username) && "campo123".equals(password)) {
            ManagerView manager = new ManagerView(model);
            JFrame manFrame = new JFrame("Pannello Amministratore");
            manFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            manFrame.setContentPane(manager);
            manFrame.setSize(900, 600);
            manFrame.setLocationRelativeTo(null);
            manFrame.setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Username o password errati", "Login fallito", JOptionPane.ERROR_MESSAGE);
            this.view.clearFields();
        }
    }
}
