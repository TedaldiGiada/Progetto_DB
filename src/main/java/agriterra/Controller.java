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
        String username = view.getUsername();
        String password = view.getPassword();

        if ("admin".equals(username) && "admin123".equals(password)) {
            openRoleFrame("Pannello Amministratore", new AdminView());
        } else if ("vendite".equals(username) && "vendite123".equals(password)) {
            openRoleFrame("Pannello Vendite", new SellerView());
        } else if ("campo".equals(username) && "campo123".equals(password)) {
            openRoleFrame("Pannello Responsabile Terreno", new ManagerView());
        } else {
            JOptionPane.showMessageDialog(null, "Username o password errati", "Login fallito", JOptionPane.ERROR_MESSAGE);
            this.view.clearFields();
        }
    }

    private void openRoleFrame(String title, javax.swing.JPanel panel) {
        this.view.setVisible(false);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
