import java.sql.*;

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

    public String selectAll(){
        String query = "SELECT * FROM artisti";
        checkConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            String msg = "";
            while(rs.next()){
                msg += rs.getString(1) + ".\tNome: ";
                msg += rs.getString(2) + ",\tPaese: ";
                msg += rs.getString(3) + ",\tGenere: ";
                msg += rs.getString(4) + "\n";
            }
            return msg;
        }catch(SQLException e){
            System.err.println("Errore di connessione: " + e.getMessage());
            return null;
        }
    }

    public Boolean addArtisti(int id, String nome, String paese, String genere){
        String query = "INSERT INTO artisti (id, nome, paese, genere) VALUES(?, ?, ?, ?)";

        if(!checkConnection())
            return false;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, nome);
            statement.setString(3, paese);
            statement.setString(4, genere);

            statement.executeUpdate();

            return true;
        }catch(SQLException e){
            System.err.println("Errore di connessione: " + e.getMessage());
            return false;
        }
    }

    public boolean addCanzoni(int id, String titolo, int durata, int annoPubblicazione, int idArtista){
        String query = "INSERT INTO canzoni (id, titolo, durata, annoPubblicazione, idArtista) VALUES(?, ?, ?, ?, ?)";

        if(!checkConnection())
            return false;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, titolo);
            statement.setInt(3, durata);
            statement.setInt(4, annoPubblicazione);
            statement.setInt(5, idArtista);

            statement.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Errore di connessione: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteArtisti(int id){
        String query = "DELETE FROM artisti WHERE id = ?";

        if(!checkConnection())
            return false;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Errore di connessione: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCanzoni(int idArtista){
        String query = "DELETE FROM canzoni WHERE idArtista = ?";

        if(!checkConnection())
            return false;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idArtista);

            statement.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Errore di connessione: " + e.getMessage());
            return false;
        }
    }
}
