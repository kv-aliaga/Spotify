package model;

import jakarta.persistence.*;
import model.base.ModelBase;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artista")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
        @AttributeOverride(name = "nome", column = @Column(name = "nome", length = 200))
})
public class Artista extends ModelBase {
//    Criando Atributos
    @Column(name = "arq_banner")
    private byte[] arqBanner;

    @OneToMany(mappedBy = "codArtista")
    private Set<Album> albums = new LinkedHashSet<>();

    @OneToMany(mappedBy = "codArtista")
    private Set<model.Single> singles = new LinkedHashSet<>();

//    Construtor
    public Artista(){}

    public Artista(byte[] arqBanner, Set<Single> singles, Set<Album> albums) {
        this.arqBanner = arqBanner;
        this.singles = singles;
        this.albums = albums;
    }

    public Artista(int id, String nome, Set<Album> albums, Set<Single> singles, byte[] arqBanner) {
        super(id, nome);
        this.albums = albums;
        this.singles = singles;
        this.arqBanner = arqBanner;
    }

    public Artista(String nome, byte[] arqBanner) {
        super(nome);
        this.arqBanner = arqBanner;
    }

    public Artista(int id, String nome, byte[] arqBanner) {
        super(id, nome);
        this.arqBanner = arqBanner;
    }

    //    Getters e Setters
    public byte[] getArqBanner() {
        return arqBanner;
    }

    public void setArqBanner(byte[] arqBanner) {
        this.arqBanner = arqBanner;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<model.Single> getSingles() {
        return singles;
    }

    public void setSingles(Set<model.Single> singles) {
        this.singles = singles;
    }
    //    toString
    @Override
    public String toString() {
        return super.toString() +
                "\nArquivo do Banner: " + Arrays.toString(this.arqBanner);
    }

}