import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static DataBase instance;
    private Connection connection;

    // Costruttore privato => pattern
    private DataBase() {
        try{
            String url = "jdbc:sqlite:Database/artisti.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connessione al database");
        } catch (SQLException e) {
            System.err.println("Errore di connessione: " + e.getMessage());
        }
    }

    // Getter pubblic => pattern
    public static DataBase getInstance() {
        if(instance == null)
            instance = new DataBase();
        return instance;
    }

    // Metodo di check => evito ripetizioni
    private boolean checkConnection() {
        try{
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione");
                return false;
            }
        }catch(SQLException e){
            System.err.println("Errore di connessione: " + e.getMessage());
            return false;
        }
        return true;
    }

    public
}
