package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseMedApparat extends Ovelse implements ActiveDomainObject {

    private static String ovelseNavn;
    private int ovelseID;
    private int apparatID;

    public void OvelseMedApparat(String ovelseNavn, int apparatID) {
        this.ovelseID = ovelseID++;
        this.ovelseNavn = ovelseNavn;
        this.apparatID = apparatID;
    }

    @Override
    public void initialize(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select OvelseNavn from OvelseMedApparat where OvelseMAID = " + this.ovelseID);

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
            stmt.executeUpdate("insert into OvelseMedApparat values ("+this.ovelseID+","+this.ovelseNavn+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
