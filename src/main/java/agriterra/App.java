package agriterra;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import agriterra.data.utils.DAOUtils;
import agriterra.model.Model;
import agriterra.view.LoginView;

public final class App {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) throws SQLException{
       try {
        var connection = DAOUtils.localMySQLConnection("dbAgriTerra", "root", "password123");
        var model = Model.fromConnection(connection);
        
        SwingUtilities.invokeLater(() -> {
            try {
                var view = new LoginView();
                var controller = new Controller(model, view);
                view.setController(controller);
                controller.initialScene();
            } catch (Exception e) {
                e.printStackTrace(); // qui vedi tutti gli errori GUI
            }
        });
        } catch (Exception e) {
            e.printStackTrace(); // qui vedi errori di connessione
        }
    }
}