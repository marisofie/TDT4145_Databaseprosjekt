import java.sql.Connection;

public interface ActiveDomainObject {

    void initialize (Connection connection);
    void refresh (Connection connection);
    void save (Connection connection);
}
