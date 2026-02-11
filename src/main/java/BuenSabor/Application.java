package BuenSabor;

import com.mercadopago.MercadoPagoConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        initDatabaseIfMissing();
        setupKeyMp();
        SpringApplication.run(Application.class, args);
    }

    private static void initDatabaseIfMissing() {
        String hostUrl = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String dbName = "el_buen_sabor";

        try (Connection conn = DriverManager.getConnection(hostUrl, user, password)) {
            if (!databaseExists(conn, dbName)) {
                System.out.println("Base de datos no existe. Ejecutando script SQL de inicialización...");
                executeSqlScript(conn);
                System.out.println("Inicialización de base de datos completa.");
            } else {
                System.out.println("Base de datos existente detectada. No se ejecuta el script.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error verificando/creando base de datos: " + dbName, e);
        }
    }

    private static boolean databaseExists(Connection rootConn, String dbName) throws SQLException {
        try (ResultSet rs = rootConn.getMetaData().getCatalogs()) {
            while (rs.next()) {
                if (dbName.equalsIgnoreCase(rs.getString(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void executeSqlScript(Connection rootConn) {
        try {
            InputStream in = Application.class.getResourceAsStream("/data/db1.sql");
            if (in == null) {
                throw new IllegalStateException("No se encontró el script en el classpath: " + "/data/db1.sql");
            }

            StringBuilder sqlBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("--")) continue;
                    sqlBuilder.append(line).append('\n');
                }
            }

            String fullSql = sqlBuilder.toString();

            try (Statement s = rootConn.createStatement()) {
                s.execute("SET FOREIGN_KEY_CHECKS=0");
            }

            try (Statement stmt = rootConn.createStatement()) {
                for (String raw : fullSql.split(";(\\s)*\\n")) {
                    String sql = raw.trim();
                    if (sql.isEmpty()) continue;
                    stmt.execute(sql);
                }
            }

            try (Statement s = rootConn.createStatement()) {
                s.execute("SET FOREIGN_KEY_CHECKS=1");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar el script SQL de inicialización", e);
        }
    }

    private static void setupKeyMp() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
        String mpKey = dotenv.get("PROD_ACCESS_TOKEN");

        if (mpKey == null || mpKey.isEmpty()) {
            throw new IllegalStateException("Falta la variable de entorno PROD_ACCESS_TOKEN.");
        }

        MercadoPagoConfig.setAccessToken(mpKey);
    }
}
