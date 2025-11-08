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

        HttpRequest req = getRequest(url);
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

    // Metodo get di tutti gli artisti
    public String getArtisti(){
        String url = urlBase + "/artisti";

        HttpRequest req = getRequest(url);
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

    // Metodo get di un artista specifico
    public String getArtista(int index){
        String url = urlBase + "/artisti/"+index;

        HttpRequest req = getRequest(url);
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

    public String getCanzoni(int index){
        String url = urlBase + "/artisti/" + index + "/canzoni";

        HttpRequest req = getRequest(url);

        HttpResponse<String> response = null;
        try{
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        return response.body();
    }

    private HttpRequest getRequest(String url) {
        HttpRequest req = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .uri(java.net.URI.create(url))
            .GET()
            .build();

        return req;
    }
}
