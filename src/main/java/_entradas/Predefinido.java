package _entradas;

import dao.*;
import model.*;
import util.*;

import java.time.LocalDate;

public class Predefinido {
    public static void main(String[] args) {
//   ========== Criando atributos ==========
//        utils
        Conexao conex = new Conexao();
        Formatador form = new Formatador();
        Validador val = new Validador();

//        Arquivos a serem inseridos
        byte[] capa = {10, 20, 30};
        byte[] banner = {100, 123, 10, 56};
        byte[] audio = {10, 20, 36, 5};

        LocalDate data1 = form.intParaLocalDate(26, 9, 1969); // abbey road
        LocalDate data2 = form.intParaLocalDate(1, 6, 1967); // sgt peppers (a)
        LocalDate data3 = form.intParaLocalDate(26, 8, 1968); // hey jude
        LocalDate data4 = form.intParaLocalDate(2, 11, 2023); // now and then

//        daos
        ArtistaDAO artistaDAO = new ArtistaDAO();
        AlbumDAO albumDAO = new AlbumDAO();
        MusicaDAO musicaDAO = new MusicaDAO();
        SingleDAO singleDAO = new SingleDAO();

//        ========== INSERINDO VALORES NOS MODELS, E INSERINDO NOS DAOS e BANCO DE DADOS ==========
//        Artista
        Artista artista1 = new Artista("The Beatles", banner);
        artistaDAO.inserir(artista1);

//        Álbuns
        Album album1 = new Album("Abbey Road", data1, capa, artista1);
        Album album2 = new Album("Sgt. Pepper Loney Hearts Club Band", data2, capa, artista1);
        albumDAO.inserir(album1);
        albumDAO.inserir(album2);

//        Músicas
        Musica musica1 = new Musica("Something", 3.02, audio, album1);
        Musica musica2 = new Musica("The End", 2.21, audio, album1);
        Musica musica3 = new Musica("Sgt Peppers Lonely Hearts Club Band", 2.2, audio, album2);
        Musica musica4 = new Musica("With A Little Help From My Friends", 2.44, audio, album2);
        musicaDAO.inserir(musica1);
        musicaDAO.inserir(musica2);
        musicaDAO.inserir(musica3);
        musicaDAO.inserir(musica4);

//        Singles
        Single single1 = new Single("Hey Jude", 7.11, artista1, capa, audio, data3);
        Single single2 = new Single("Now And Then", 4.08, artista1, capa, audio, data4);
        singleDAO.inserir(single1);
        singleDAO.inserir(single2);

//        Saída de dados
        System.out.println("Todas as informações foram inseridas no banco de dados com sucesso!");
    }
}
