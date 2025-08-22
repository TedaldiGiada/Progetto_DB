package main.java.agriterra;
import java.sql.SQLException;

import main.java.agriterra.data.utils.DAOUtils;
import main.java.agriterra.model.Model;
import main.java.agriterra.view.LoginView;

public final class App {
    public static void main(String[] args) throws SQLException{
        var connection = DAOUtils.localMySQLConnection("AgriTerra", "root", "");
        var model = Model.fromConnection(connection);
        var view = new LoginView (() -> {
            try {
                connection.close();
            } catch (Exception ignored) {}
        });
        var controller = new Controller(model, view);
        //controller.userRequestedInitialPage();
    }
}
