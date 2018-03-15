import java.sql.SQLException;

public class OvelseController extends DBConnection {

    public void OvelseController() {
        connect();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOvelse() {

    }

    public void addApparat() {

    }

    public void setOvelsesgruppe() {

    }

    public void saveOvelse() {

    }
}
