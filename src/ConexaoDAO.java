import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    public Connection conectaBD() {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String password = "carros2011";

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException erro) {
            System.out.println("Erro na conexão: " + erro.getMessage());
        }

        return conn;
    }
}
