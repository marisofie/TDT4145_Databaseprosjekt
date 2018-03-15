import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseUtenApparat extends Ovelse {

    private String ovelseNavn;
    private int ovelseID;

    public void OvelseUtenApparat(String ovelseNavn) {
        this.ovelseID = ovelseID++;
        this.ovelseNavn = ovelseNavn;
    }

    @Override
    public void initialize(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select OvelseNavn from OvelseUtenApparat where OvelseUAID = " + this.ovelseID);

            while (rs.next()) {
                this.ovelseNavn = rs.getString("OvelseNavn");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(Connection connection) {
        initialize(connection);

    }

    @Override
    public void save(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into OvelseUtenApparat values ("+this.ovelseID+","+this.ovelseNavn+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
