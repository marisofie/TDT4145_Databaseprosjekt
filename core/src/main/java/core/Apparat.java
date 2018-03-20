import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Apparat implements  ActiveDomainObject {

    public int apparatID;
    private String apparatNavn;
    private String funksjonsbeskrivelse;

    public Apparat(int apparatID, String apparatNavn, String funksjonsbeskrivelse) {
        this.apparatID = apparatID;
        this.apparatNavn = apparatNavn;
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    public void setApparatID() {
        this.apparatID = apparatID++;
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

    //metode for å få ut alle apparater fra database.
    public static List<Apparat> getAll(Connection connection) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Apparat");

            List<Apparat> apparater = new ArrayList<>();


            while (rs.next()) {
                int apparatID = rs.getInt("ApparatID");
                String apparatNavn = rs.getString("ApparatNavn");
                String funkbeskr = rs.getString("Funksjonsbeskrivelse");

                apparater.add(new Apparat(apparatID, apparatNavn, funkbeskr));
                System.out.print(apparater);
            }

            return apparater;

        }catch (SQLException e) {
            throw new RuntimeException("Unable to load all Apparater" + e);
        }



    }

    public static String toString(List<Apparat> results) {
        String strResults = "";
        int x = 0;

        while (x <= results.size()) {
            strResults += results.get(x);
            x++;
        }

        return strResults;
    }

    public static void main(String[] args) {
        System.out.print("hei");
        DBConnection db = new DBConnection() {
            @Override
            public void connect() {
                super.connect();
            }
        };

        List<Apparat> results = getAll(db.connection);

        System.out.print(toString(results));
    }
}

