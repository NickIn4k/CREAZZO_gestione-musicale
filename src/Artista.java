import java.util.List;

public class Artista {
    private int id;
    private String nome;
    private String paese;
    private String genere;
    private List<Canzone> canzoni;

    public Artista(int id, String nome, String paese, String genere, List<Canzone> canzoni) {
        this.id = id;
        this.nome = nome;
        this.paese = paese;
        this.genere = genere;
        this.canzoni = canzoni;
    }

    // Per POST e PUT
    public Artista(String nome, String paese, String genere) {
        this.nome = nome;
        this.paese = paese;
        this.genere = genere;
    }

    public String toString(){
        String msg = id + "\t" + nome + "\t" + paese + "\t" + genere + "\nCanzoni:\n";
        for (Canzone c : canzoni)
            msg += c.toString();

        return msg;
    }
}