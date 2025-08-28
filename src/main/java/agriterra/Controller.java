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
        JFrame frame = new JFrame("Pannello Amministratore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        if ("admin".equals(username) && "admin123".equals(password)) {
            frame.setContentPane(new AdminView(model));
            view.dispose();
        } else if ("vendite".equals(username) && "vendite123".equals(password)) {
            frame.setContentPane(new SellerView(model));
            view.dispose();
        } else if ("campo".equals(username) && "campo123".equals(password)) {
            frame.setContentPane(new ManagerView(model));
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Username o password errati", "Login fallito", JOptionPane.ERROR_MESSAGE);
            this.view.clearFields();
        }
    }
}
