package agricola.app;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import agricola.controller.Controller;
import agricola.data.utils.DAOUtils;
import agricola.model.Model;
import agricola.view.View;

public class App {
    public static void main(String[] args) throws SQLException{
       try {
        var connection = DAOUtils.localMySQLConnection("dbAgriTerra", "root", "password123");
        var model = new Model(connection);
        
        SwingUtilities.invokeLater(() -> {
            try {
                var view = new View();
                var controller = new Controller(model, view);
                view.setController(controller);
            } catch (Exception e) {
                e.printStackTrace(); // qui vedi tutti gli errori GUI
            }
        });
        } catch (Exception e) {
            e.printStackTrace(); // qui vedi errori di connessione
        }
    }
}
