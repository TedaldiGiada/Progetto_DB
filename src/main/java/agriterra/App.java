package agriterra;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import agriterra.data.utils.DAOUtils;
import agriterra.model.Model;
import agriterra.view.LoginView;


public final class App {
    public static void main(String[] args) throws SQLException{
       try {
        var connection = DAOUtils.localMySQLConnection("dbAgriTerra", "root", "");
        var model = Model.fromConnection(connection);
        
        SwingUtilities.invokeLater(() -> {
            try {
                var view = new LoginView();
                var controller = new Controller(model, view);
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
