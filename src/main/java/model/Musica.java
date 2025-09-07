package model;

import jakarta.persistence.*;
import model.base.ModelBase;
import util.Formatador;

import java.util.Arrays;

@Entity
@Table(name = "musica")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
        @AttributeOverride(name = "nome", column = @Column(name = "nome", length = 200))
})
public class Musica extends ModelBase {
//    Criando Atributos
    @Column(name = "duracao")
    private double duracao;

    @Column(name = "arq_audio")
    private byte[] arqAudio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_album")
    private Album codAlbum;

//    Construtores
    public Musica() {}

    public Musica(double duracao, Album codAlbum, byte[] arqAudio) {
        this.duracao = duracao;
        this.codAlbum = codAlbum;
        this.arqAudio = arqAudio;
    }

    public Musica(int id, String nome, double duracao, Album codAlbum, byte[] arqAudio) {
        super(id, nome);
        this.duracao = duracao;
        this.codAlbum = codAlbum;
        this.arqAudio = arqAudio;
    }

    public Musica(String nome, double duracao, byte[] arqAudio, Album codAlbum) {
        super(nome);
        this.duracao = duracao;
        this.arqAudio = arqAudio;
        this.codAlbum = codAlbum;
    }

    //       Getters e Setters
    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public byte[] getArqAudio() {
        return arqAudio;
    }

    public void setArqAudio(byte[] arqAudio) {
        this.arqAudio = arqAudio;
    }

    public Album getCodAlbum() {
        return codAlbum;
    }

    public void setCodAlbum(Album codAlbum) {
        this.codAlbum = codAlbum;
    }

//    toString
    public String toString(){
        Formatador form = new Formatador();

        return super.toString() +
                "Duração: " + form.doubleParaTempo(this.duracao) +
                "Arquivo de Áudio: " + Arrays.toString(this.arqAudio) +
                "Álbum: " + this.codAlbum.getNome();
    }
}