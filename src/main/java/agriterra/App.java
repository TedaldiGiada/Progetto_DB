package code;
import java.sql.SQLException;

public final class App {
    public static void main(String[] args) throws SQLException{
        var connection = DAOUtils.localMySQLConnection("AgriTerra", "root", "");
        var model = Model.fromConnection(connection);
        var view = new View (() -> {
            try {
                connection.close();
            } catch (Exception ignored) {}
        });
        var controller = new Controller(model, view);
        view.setController(controller);
        //controller.userRequestedInitialPage();
    }
}
