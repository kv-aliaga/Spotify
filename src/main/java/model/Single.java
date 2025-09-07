package model;

import jakarta.persistence.*;
import model.base.ModelBase;
import util.Formatador;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "single")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
        @AttributeOverride(name = "nome", column = @Column(name = "nome", length = 200))
})
public class Single extends ModelBase {
//    Atributos
    @Column(name = "duracao")
    private double duracao;

    @Column(name = "dt_lancamento")
    private LocalDate dtLancamento;

    @Column(name = "arq_audio")
    private byte[] arqAudio;

    @Column(name = "arq_capa")
    private byte[] arqCapa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_artista")
    private Artista codArtista;

//    Construtores
    public Single(){}

    public Single(double duracao, Artista codArtista, byte[] arqAudio, LocalDate dtLancamento, byte[] arqCapa) {
        this.duracao = duracao;
        this.codArtista = codArtista;
        this.arqAudio = arqAudio;
        this.dtLancamento = dtLancamento;
        this.arqCapa = arqCapa;
    }

    public Single(String nome, double duracao, Artista codArtista, byte[] arqCapa, byte[] arqAudio, LocalDate dtLancamento) {
        super(nome);
        this.duracao = duracao;
        this.codArtista = codArtista;
        this.arqCapa = arqCapa;
        this.arqAudio = arqAudio;
        this.dtLancamento = dtLancamento;
    }

    public Single(int id, String nome, LocalDate dtLancamento, byte[] arqAudio, byte[] arqCapa, Artista codArtista, double duracao) {
        super(id, nome);
        this.dtLancamento = dtLancamento;
        this.arqAudio = arqAudio;
        this.arqCapa = arqCapa;
        this.codArtista = codArtista;
        this.duracao = duracao;
    }

    //    Getters e Setters
    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public byte[] getArqAudio() {
        return arqAudio;
    }

    public void setArqAudio(byte[] arqAudio) {
        this.arqAudio = arqAudio;
    }

    public byte[] getArqCapa() {
        return arqCapa;
    }

    public void setArqCapa(byte[] arqCapa) {
        this.arqCapa = arqCapa;
    }

    public Artista getCodArtista() {
        return codArtista;
    }

    public void setCodArtista(Artista codArtista) {
        this.codArtista = codArtista;
    }

    @Override
    public String toString() {
        Formatador form = new Formatador();

        return super.toString() +
                "\nDuração: " + form.doubleParaTempo(this.duracao) +
                "\nData de Lançamento: " + this.dtLancamento +
                "\nArquivo de Áudio: " + Arrays.toString(this.arqAudio) +
                "\nArquivo de Capa: " + Arrays.toString(this.arqCapa) +
                "\nArtista: " + this.codArtista.getNome();
    }
}