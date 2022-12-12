import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtils.connectToDataBase();
        Statement statement=JdbcUtils.createStatement();



    }
}
