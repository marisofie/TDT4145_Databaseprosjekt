import java.sql.SQLException;

public class TreningsoktController extends DBConnection {

    public void TreningsoktController() {
        connect();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTreningsokt() {

    }

    public void chooseOvelse() {

    }

    public void addNotat() {

    }

    public void saveTreningsokt() {

    }
}


}
