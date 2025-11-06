import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiClient apiClient = new ApiClient();

        System.out.println(apiClient.healthRequest());


        System.out.println("====================== MENU ======================");
        String scelta;
        do{
            System.out.println("Vuoi continuare? [si/no]");
            scelta = sc.nextLine();
        }while(scelta.equalsIgnoreCase("si"));
    }
}