package agricola.app;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import agricola.controller.Controller;
import agricola.data.utils.DAOUtils;
import agricola.data.utils.Rule;
import agricola.model.Model;
import agricola.view.View;

public class App {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) throws SQLException{
       try {
        var connection = DAOUtils.localMySQLConnection("dbAgriTerra", "root", "password123");
        var model = new Model(connection);
        model.register("admin", "admin123", Rule.ADMIN);
        model.register("vendite", "vendite123", Rule.SELLER);
        model.register("campo", "campo123", Rule.MANAGER);
        SwingUtilities.invokeLater(() -> {
            try {
                var view = new View();
                var controller = new Controller(model, view);
                view.setController(controller);
                view.initializeGUI();
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(); // qui vedi tutti gli errori GUI
            }
        });
        } catch (Exception e) {
            e.printStackTrace(); // qui vedi errori di connessione
        }
    }
}
