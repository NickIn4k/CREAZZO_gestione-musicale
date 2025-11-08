public class Canzone {
    private int id;
    private String titolo;
    private int durata;
    private int annoPubblicazione;
    Artista artista;    // Per la chiave esterna del db locale

    public String toString() {
        return id + "\t" + titolo + "\t" + durata + "\t" + annoPubblicazione + "\n";
    }
}
