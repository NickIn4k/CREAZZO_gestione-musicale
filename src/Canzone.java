public class Canzone {
    public int id;
    public String titolo;
    public int durata;
    public int annoPubblicazione;
    public Artista artista;    // Per la chiave esterna del db locale

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

    public Canzone(){} // per JSON
}


