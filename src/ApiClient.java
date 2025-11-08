import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String urlBase = "http://localhost:4567/api";
    private final Gson gson = new Gson();

    // Metodo comune per evitare ripetizioni
    private HttpResponse<String> sendRequest(HttpRequest req){
        HttpResponse<String> response;
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
    public List<Artista> getArtisti(){
        String url = urlBase + "/artisti";

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return gson.fromJson(response.body(), new TypeToken<List<Artista>>(){}.getType());
    }

    // GET di un artista specifico
    public Artista getArtista(int index){
        String url = urlBase + "/artisti/"+index;

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return gson.fromJson(response.body(), Artista.class);
    }

    // GET di tutte le canzoni di un artista specifico
    public List<Canzone> getCanzoniByArtist(int index){
        String url = urlBase + "/artisti/" + index + "/canzoni";

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return gson.fromJson(response.body(), new TypeToken<List<Canzone>>(){}.getType());
    }

    // GET di tutte le canzoni
    public List<Canzone> getCanzoni(){
        String url = urlBase + "/canzoni";

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);

        return gson.fromJson(response.body(), new TypeToken<List<Canzone>>(){}.getType());
    }

    // GET di una canzone tramite il suo id
    public Canzone getCanzoneById(int id){
        String url = urlBase + "/canzoni/"+id;

        HttpRequest req = getRequest(url);
        HttpResponse<String> response = sendRequest(req);
        return gson.fromJson(response.body(), Canzone.class);
    }

    // Metodo comune per l'ottenimento della req
    private HttpRequest getRequest(String url) {
        return HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .uri(java.net.URI.create(url))
            .GET()
            .build();
    }
    //endregion

    //region HTTP request generiche

    // POST inserimento dati nuovo artista
    public String postNewArtista(String bodyJson){
        String url = urlBase + "/artisti";

        HttpRequest req = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .uri(java.net.URI.create(url))
            .POST(HttpRequest.BodyPublishers.ofString(bodyJson))
            .build();

        HttpResponse<String> response = sendRequest(req);

        return response.body();
    }

    //PUT aggiornamento dati di un artista specifico
    public String putArtistaById(int id, String jsonBody){
        String url = urlBase + "/artisti/"+id;

        HttpRequest req = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .uri(java.net.URI.create(url))
            .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = sendRequest(req);

        return response.body();
    }

    //DELETE di un artista specifico
    public String deleteArtistaById(int id){
        String url = urlBase + "/artisti/"+id;

        HttpRequest req = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .uri(java.net.URI.create(url))
            .DELETE()
            .build();

        HttpResponse<String> response = sendRequest(req);
        return response.body();
    }
    //endregion
}
