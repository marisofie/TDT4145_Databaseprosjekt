import java.sql.SQLException;

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

    public void addApparat (String apparatNavn, String funksjonsbeskrivelse) {
        //må finne en måte å hente ut riktig apparat her.


    }

    public void setOvelsesgruppe () {

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

