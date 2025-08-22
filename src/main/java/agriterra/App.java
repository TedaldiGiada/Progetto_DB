import java.sql.SQLException;
import utils.DAOUtils;

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
