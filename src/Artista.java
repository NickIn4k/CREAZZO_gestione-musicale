import java.util.List;

public class Artista {
    private int id;
    private String nome;
    private String paese;
    private String genere;
    private List<Canzone> canzoni;

    public String toString(){
        String msg = id + "\t" + nome + "\t" + paese + "\t" + genere + "\nCanzoni:\n";
        for (Canzone c : canzoni)
            msg += c.toString();

        return msg;
    }
}