package core;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class Ovelsesgruppe implements ActiveDomainObject{

    public static int gruppeIDCounter = 0;

    private final int gruppeID;
    private String gruppeNavn;

    public Ovelsesgruppe(String gruppeNavn) {
        this.gruppeID = gruppeIDCounter++;
        this.gruppeNavn = gruppeNavn;
    }

    public void setGruppeNavn(String gruppeNavn) {
        this.gruppeNavn = gruppeNavn;
    }


    @Override
    public void initialize(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select GruppeNavn from OvelsesGruppe where GruppeID = " + this.gruppeID);

            while (rs.next()) {
                this.gruppeNavn = rs.getString("GruppeNavn");
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
            stmt.executeUpdate("insert into OvelsesGruppe values ("+this.gruppeID+","+this.gruppeNavn+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
