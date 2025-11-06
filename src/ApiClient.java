import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();

    // Metodo di test per assicurarsi che l'API vada
    public String healthRequest() {
        String url = "http://localhost:4567/api/health";

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        if(response == null)
            throw new RuntimeException("Error sending request");

        return response.body();
    }
}
