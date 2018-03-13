import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Apparat implements  ActiveDomainObject {

    public static int apparatIDCounter = 0;

    private final int apparatID;
    private String apparatNavn;
    private String funksjonsbeskrivelse;

    public Apparat(String apparatNavn, String funksjonsbeskrivelse) {
        this.apparatID = apparatIDCounter++;
        this.apparatNavn = apparatNavn;
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    public void setApparatNavn(String apparatNavn) {
        this.apparatNavn = apparatNavn;
    }

    public void setFunksjonsBeskrivelse(String funksjonsbeskrivelse) {
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    @Override
    public void initialize(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select ApparatNavn, Funksjonsbeskrivelse from Apparat where ApparatID = " + this.apparatID);

            while (rs.next()) {
                this.apparatNavn = rs.getString("ApparatNavn");
                this.funksjonsbeskrivelse = rs.getString("Funksjonsbeskrivelse");
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
            stmt.executeUpdate("insert into Apparat values ("+this.apparatID+","+this.apparatNavn+","+this.funksjonsbeskrivelse+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
