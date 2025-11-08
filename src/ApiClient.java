import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String urlBase = "http://localhost:4567/api";

    // Metodo di test per assicurarsi che l'API vada
    public String healthRequest() {
        String url = urlBase + "/health";

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

    public String getArtisti(){
        String url = urlBase + "/artisti";

        HttpRequest req = HttpRequest.newBuilder()
                .header("Content-Type","application/json")
                .uri(java.net.URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try{
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        if(response == null)
            throw new RuntimeException("Error sending request");

        return response.body();
    }

    public String getArtista(int index){
        String url = urlBase + "/artisti/"+index;

        HttpRequest req = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;

        try{
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        if(response == null)
            throw new RuntimeException("Error sending request");

        return response.body();
    }
}
