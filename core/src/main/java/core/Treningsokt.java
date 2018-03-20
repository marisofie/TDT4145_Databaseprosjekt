package core;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;

public class Treningsokt implements ActiveDomainObject{

    public static int oktIDCounter = 0;

    private final int oktID;

    private Date dato;
    private Time tidspunkt;

    private int varighet = -1;
    private int form = -1;
    private int prestasjon = -1;

    private List<Ovelse> ovelser;

    public Treningsokt(Date dato, Time tidspunkt) {
        this.oktID = oktIDCounter++;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.ovelser = new ArrayList<>();
    }

    //Må kanskje endres
    public void setVarighet(int varighet) {
        this.varighet = varighet;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public void setPrestasjon(int prestasjon) {
        this.prestasjon = prestasjon;
    }

    @Override
    public void initialize(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select Dato, Tidspunkt, Varighet, Prestasjon, Form from Treningsokt where OktID = " + this.oktID);

            while (rs.next()) {
                this.dato = rs.getDate("Dato");
                this.tidspunkt = rs.getTime("Tidspunkt");
                this.varighet = rs.getInt("Varighet");
                this.prestasjon = rs.getInt("Prestasjon");
                this.form = rs.getInt("Form");
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
            stmt.executeUpdate("insert into Treningsokt values ("+this.oktID+","+this.dato+","+this.tidspunkt+","+this.varighet+","+this.form+","+this.prestasjon+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
