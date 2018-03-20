package core;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class TreningsoktController extends DBConnection {

    private OvelseUtenApparat ovelseUA;
    private OvelseMedApparat ovelseMA;
    private Treningsokt treningsokt;
    private List<Ovelse> ovelser;
    private Notat notat;


    public void TreningsoktController() {
        connect();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTreningsokt(Date dato, Time tidspunkt, int varighet, int prestasjon, int form) {
        this.treningsokt = new Treningsokt(dato, tidspunkt);
        this.treningsokt.setForm(form);
        this.treningsokt.setVarighet(varighet);
        this.treningsokt.setPrestasjon(prestasjon);

    }

    public void chooseOvelse(boolean medApparat) {
        //Enten direkte spørring til database, eller noe annet her.

    }

    public void addNotat(String formaal, String opplevelse) {
        this.notat = new Notat(formaal, opplevelse, this.treningsokt.oktID);

    }

    public void saveTreningsokt() {
        this.treningsokt.save(connection);

    }
}
