import java.util.List;

public class Artista {
    public int id;
    public String nome;
    public String paese;
    public String genere;
    public List<Canzone> canzoni;

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

    public Artista(){} //per JSON

    public String toString(){
        String msg = "Id: " + id + "\nNome: " + nome + "\nPaese: " + paese + "\nGenere: " + genere + "\n###### Canzoni ######\n" + "Id\tTitolo\tDurata\tAnno\n";
        for (Canzone c : canzoni)
            msg += c.toString();

        return msg;
    }
}