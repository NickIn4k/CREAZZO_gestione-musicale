import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String urlBase = "http://localhost:4567/api";

    private HttpResponse sendRequest(HttpRequest req){
        HttpResponse<String> response = null;
        try{
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
        return response;
    }

    //region HTTP GET
    // Metodo di test per assicurarsi che l'API vada
    public String healthRequest() {
        String url = urlBase + "/health";

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return response.body();
    }

    // GET di tutti gli artisti
    public String getArtisti(){
        String url = urlBase + "/artisti";

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return response.body();
    }

    // GET di un artista specifico
    public String getArtista(int index){
        String url = urlBase + "/artisti/"+index;

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return response.body();
    }

    // GET di tutte le canzoni di un artista specifico
    public String getCanzoniByArtist(int index){
        String url = urlBase + "/artisti/" + index + "/canzoni";

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return response.body();
    }

    // GET di tutte le canzoni
    public String getCanzoni(){
        String url = urlBase + "/canzoni";

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return response.body();
    }

    // GET di una canzone tramite il suo id
    public String getCanzoneById(int id){
        String url = urlBase + "/canzoni/"+id;

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);
        return response.body();
    }

    // Metodo comune per l'ottenimento della req
    private HttpRequest getRequest(String url) {
        HttpRequest req = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .uri(java.net.URI.create(url))
            .GET()
            .build();

        return req;
    }
    //endregion

    //region HTTP request generiche
    //endregion
}
