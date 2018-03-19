import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notat implements ActiveDomainObject{

    public static int notatIDCounter = 0;

    private final int notatID;
    private String formaal;
    private String opplevelse;
    private int oktID;


    public Notat(String formaal, String opplevelse, int oktID) {
        this.notatID = notatIDCounter++;
        //litt usikker her på om vi skal kreve at dette blir oppgitt for å lage notat
        //føler det gir mening
        this.formaal = formaal;
        this.opplevelse = opplevelse;
        this.oktID = oktID;
    }

    public void setFormaal(String formaal) {
        this.formaal = formaal;
    }

    public void setOpplevelse(String opplevelse) {
        this.opplevelse = opplevelse;
    }

    @Override
    public void initialize(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select Formaal, Opplevelse, OktID from Notat where NotatID = " + this.notatID);

            while (rs.next()) {
                this.formaal = rs.getString("Formaal");
                this.opplevelse = rs.getString("Opplevelse");
            }

        }catch (SQLException e) {
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
            stmt.executeUpdate("insert into Notat values ("+this.notatID+","+this.formaal+","+this.opplevelse+","+this.oktID+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
