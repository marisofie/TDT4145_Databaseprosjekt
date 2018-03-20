import java.sql.SQLException;
import java.sql.Statement;

public class OvelseController extends DBConnection {

    private OvelseMedApparat ovelseMA;
    private OvelseUtenApparat ovelseUA;
    private Apparat apparat;


    public void OvelseController() {
        connect();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOvelse(boolean medApparat, String ovelseNavn) {
        if (medApparat) {
            this.ovelseMA = new OvelseMedApparat();
            this.ovelseMA.OvelseMedApparat(ovelseNavn, this.apparat.apparatID);

        } else {
            this.ovelseUA = new OvelseUtenApparat();
            this.ovelseUA.OvelseUtenApparat(ovelseNavn);

        }
    }

    //usikker på om vi her skal legge til apparatet til en øvelse, eller om vi skal opprette ett nytt apparat?
    public void addApparat (int apparatID, String apparatNavn, String funksjonsbeskrivelse) {
        Apparat apparat = new Apparat(apparatID, apparatNavn, funksjonsbeskrivelse);
        apparat.save(connection);


    }

    public void setOvelsesgruppe (boolean medApparat, String ovelsesNavn) {
        //hjelp

    }

    public void saveOvelse (boolean medApparat) {
        if (medApparat) {
            this.ovelseMA.save(connection);
        }
        else {
            this.ovelseUA.save(connection);
        }
    }
}

