public class Canzone {
    private int id;
    private String titolo;
    private int durata;
    private int annoPubblicazione;
    Artista artista;    // Per la chiave esterna del db locale

    public String toString() {
        return id + "\t" + titolo + "\t" + durata + "\t" + annoPubblicazione + "\n";
    }

    public Canzone(int id, String titolo, int durata, int annoPubblicazione, Artista artista) {
        this.id = id;
        this.titolo = titolo;
        this.durata = durata;
        this.annoPubblicazione = annoPubblicazione;
        this.artista = artista;
    }
}


