import com.google.gson.Gson;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiClient apiClient = new ApiClient();

        // Testo colorato per la connessione
        final String grn = "\u001B[32m";
        final String rst = "\u001B[0m";

        System.out.println(grn + apiClient.healthRequest() + rst);

        String scelta;
        do{
            // Menu
            System.out.println("====================== MENU ======================");
            System.out.println(
                "1.\t Get di tutti gli artisti\n" +
                "2.\t Get di un artista specifico\n" +
                "3.\t Get delle canzoni di un artista specifico\n" +
                "4.\t Get di tutte le canzoni\n" +
                "5.\t Get di una canzone specifica\n" +
                "6.\t Post di un nuovo artista\n" +
                "7.\t Put (aggiornamento) di dati artista\n" +
                "8.\t Delete di un artista\n" +
                "9.\t Salva un artista tra i preferiti\n" +
                "10.\t Elimina un artista dai preferiti"
            );

            //selezione operazione
            int index = sc.nextInt();
            if(index < 1 || index > 10)
                System.exit(1);
            switch (index) {
                case 1:
                    System.out.println(apiClient.getArtisti());
                    break;
                case 2:
                    artistaSelect(sc,  apiClient);
                    break;
                case 3:
                    canzoniByArtist(sc,  apiClient);
                    break;
                case 4:
                    System.out.println(apiClient.getCanzoni());
                    break;
                case 5:
                    canzoniById(sc,  apiClient);
                    break;
                case 6:
                    creaNuovoArtista(sc,  apiClient);
                    break;
                case 7:
                    aggiornaArtista(sc,  apiClient);
                    break;
                case 8:
                    eliminaArtista(sc,  apiClient);
                    break;
                case 9:
                    // Salva nel DB
                    break;
                case 10:
                    // Elimina dal DB
                    break;
            }


            // Gestione ciclicità
            sc.nextLine();
            System.out.println("Vuoi continuare? [si/no]");
            scelta = sc.nextLine();
        }while(scelta.equalsIgnoreCase("si"));
    }


    private static void artistaSelect(Scanner sc, ApiClient apiClient) {
        System.out.println("Indica l'indice dell'artista");
        int i = sc.nextInt();
        System.out.println(apiClient.getArtista(i));
    }

    private static void canzoniByArtist(Scanner sc, ApiClient apiClient) {
        System.out.println("Indica l'indice dell'artista");
        int i = sc.nextInt();
        System.out.println(apiClient.getCanzoniByArtist(i));
    }

    private static void canzoniById(Scanner sc, ApiClient apiClient) {
        System.out.println("Indica l'indice della canzone");
        int i = sc.nextInt();
        System.out.println(apiClient.getCanzoneById(i));
    }

    private static void creaNuovoArtista(Scanner sc, ApiClient apiClient) {
        sc.nextLine(); // pulizia buffer

        System.out.println("Inserisci il nome dell'artista:");
        String nome = sc.nextLine();

        System.out.println("Inserisci il genere musicale:");
        String genere = sc.nextLine();

        System.out.println("Inserisci la nazionalità:");
        String nazionalita = sc.nextLine();

        // Crea l’oggetto Artista
        Artista artista = new Artista(nome, genere, nazionalita);

        // Serializza in JSON
        Gson gson = new Gson();
        String jsonBody = gson.toJson(artista);

        // Invio della richiesta
        String response = apiClient.postNewArtista(jsonBody);
        System.out.println("Risposta server: " + response);
    }

    private static void aggiornaArtista(Scanner sc, ApiClient apiClient) {
        System.out.println("Inserisci l'ID dell'artista da aggiornare:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Inserisci il nuovo nome:");
        String nome = sc.nextLine();

        System.out.println("Inserisci il nuovo genere:");
        String genere = sc.nextLine();

        System.out.println("Inserisci la nuova nazionalità:");
        String nazionalita = sc.nextLine();

        Artista artista = new Artista(nome, genere, nazionalita);
        Gson gson = new Gson();
        String jsonBody = gson.toJson(artista);

        String response = apiClient.putArtistaById(id, jsonBody);
        System.out.println("Risposta server: " + response);
    }

    private static void eliminaArtista(Scanner sc, ApiClient apiClient) {
        System.out.println("Indica l'indice della canzone");
        int i = sc.nextInt();

        String response = apiClient.deleteArtistaById(i);
        System.out.println("Risposta server: " + response);
    }
}