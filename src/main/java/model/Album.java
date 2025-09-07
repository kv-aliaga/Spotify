package model;

import jakarta.persistence.*;
import model.base.ModelBase;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "album")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
        @AttributeOverride(name = "nome", column = @Column(name = "nome", length = 200))
})
public class Album extends ModelBase {
//    Criando Atributos
    @Column(name = "dt_lancamento")
    private LocalDate dtLancamento;

    @Column(name = "arq_capa")
    private byte[] arqCapa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_artista")
    private model.Artista codArtista;

    @OneToMany(mappedBy = "codAlbum")
    private Set<model.Musica> musicas = new LinkedHashSet<>();

//    Construtores
    public Album(){}

    public Album(Set<Musica> musicas, Artista codArtista, byte[] arqCapa, LocalDate dtLancamento) {
        this.musicas = musicas;
        this.codArtista = codArtista;
        this.arqCapa = arqCapa;
        this.dtLancamento = dtLancamento;
    }

    public Album(Artista codArtista, byte[] arqCapa, LocalDate dtLancamento) {
        this.codArtista = codArtista;
        this.arqCapa = arqCapa;
        this.dtLancamento = dtLancamento;
    }

    public Album(int id, String nome, LocalDate dtLancamento, Set<Musica> musicas, Artista codArtista, byte[] arqCapa) {
        super(id, nome);
        this.dtLancamento = dtLancamento;
        this.musicas = musicas;
        this.codArtista = codArtista;
        this.arqCapa = arqCapa;
    }

    public Album(String nome, LocalDate dtLancamento, byte[] arqCapa, Artista codArtista) {
        super(nome);
        this.dtLancamento = dtLancamento;
        this.arqCapa = arqCapa;
        this.codArtista = codArtista;
    }

    //    Getters e Setters
    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public byte[] getArqCapa() {
        return arqCapa;
    }

    public void setArqCapa(byte[] arqCapa) {
        this.arqCapa = arqCapa;
    }

    public model.Artista getCodArtista() {
        return codArtista;
    }

    public void setCodArtista(model.Artista codArtista) {
        this.codArtista = codArtista;
    }

    public Set<model.Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(Set<model.Musica> musicas) {
        this.musicas = musicas;
    }

//    toString
    @Override
    public String toString() {
        return super.toString() +
                "\nData de Lançamento: " + this.dtLancamento +
                "\nArquivo da Capa: " + Arrays.toString(this.arqCapa) +
                "\nArtista: " + this.codArtista.getNome();
    }
}