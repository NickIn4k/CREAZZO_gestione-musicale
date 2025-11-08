import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiClient apiClient = new ApiClient();

        System.err.println(apiClient.healthRequest());

        String scelta;
        do{
            // Menu
            System.out.println("====================== MENU ======================");
            System.out.println(
                "1.\t Get di tutti gli artisti\n" +
                "2.\t Get di un artista specifico\n" +
                "3.\t Palle\n" +
                "4.\t Palle2\n" +
                "5.\t Palle\n" +
                "6.\t Palle2\n"
            );

            //selezione operazione
            int index = sc.nextInt();
            if(index > 6 || index < 1)
                System.exit(1);
            switch (index) {
                case 1:
                    System.out.println(apiClient.getArtisti());
                    break;
                case 2:
                    artistaSelect(sc,  apiClient);
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
}