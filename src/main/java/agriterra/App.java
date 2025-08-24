package agriterra;

import java.sql.SQLException;

import agriterra.data.utils.DAOUtils;
import agriterra.model.Model;
import agriterra.view.LoginView;


public final class App {
    public static void main(String[] args) throws SQLException{
        var connection = DAOUtils.localMySQLConnection("dbAgriTerra", "root", "");
        var model = Model.fromConnection(connection);
        var view = new LoginView ();
        var controller = new Controller(model, view);
        controller.initialScene();
    }
}
