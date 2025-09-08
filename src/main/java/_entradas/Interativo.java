package _entradas;
//fixme implementar interatividade com o usuário
//fixme implementar servlets
//fixme implementar mostrarArtistas e mostrarAlbuns

import dao.*;
import model.*;
import util.Formatador;

import java.time.LocalDate;
import java.util.Scanner;

public class Interativo {
//    Criando atributos
    static Scanner input = new Scanner(System.in);
    static Formatador form = new Formatador();

    static AlbumDAO albumDAO = new AlbumDAO();
    static ArtistaDAO artistaDAO = new ArtistaDAO();
    static MusicaDAO musicaDAO = new MusicaDAO();
    static SingleDAO singleDAO = new SingleDAO();

    static Album album = new Album();
    static Artista artista = new Artista();
    static Musica musica = new Musica();
    static Single single = new Single();

//    Main
    public static void main(String[] args) {
        Interativo it = new Interativo();
        it.menuInicial();
    }

//    Métodos Estáticos
    public void tabelas(){
        System.out.println("[1] - Álbum");
        System.out.println("[2] - Artista");
        System.out.println("[3] - Música");
        System.out.println("[4] - Single");
        System.out.println("[0] - Sair");
        System.out.print("Escolha: ");
    }

    public void menuInicial(){
        System.out.println("=== MENU ===");
        System.out.println("[1] - Ver tabelas");
        System.out.println("[2] - Inserir dados nas tabelas");
        System.out.println("[3] - Atualizar dados nas tabelas");
        System.out.println("[4] - Excluir dados nas tabelas");
        System.out.println("[0] - Sair");
        System.out.print("Escolha: ");
        inputInicial(input.nextInt());
    }

    public void inputInicial(int escolha){
        LocalDate data;
        String caminho;
        int tabelaEscolha;
        int dia, mes, ano;

        switch (escolha) {
            case (0) -> System.out.println("Finalizando...");
            case (1) -> {
                System.out.println("\n=== VER TABELAS ===");
                tabelas();
                tabelaEscolha = input.nextInt();

                switch (tabelaEscolha) {
                    case (0) -> System.out.println("Finalizando...");
                    case (1) -> albumDAO.buscar();
                    case (2) -> artistaDAO.buscar();
                    case (3) -> musicaDAO.buscar();
                    case (4) -> singleDAO.buscar();
                }
            }

            case (2) -> {
                System.out.println("\n=== INSERIR NA TABELA ===");
                tabelas();
                tabelaEscolha = input.nextInt();

                switch (tabelaEscolha){
                    case (0) -> System.out.println("Finalizando...");
                    case (1) -> {

                        System.out.println("\n== INSERIR EM ÁLBUM ==");
                        System.out.print("Nome: "); input.nextLine(); album.setNome(input.nextLine());
                        System.out.println("Data de Lançamento: (APENAS NÚMEROS)");
                            System.out.print("    Dia: "); dia = input.nextInt();
                            System.out.print("    Mês: "); mes = input.nextInt();
                            System.out.print("    Ano: "); ano = input.nextInt();
                                data = LocalDate.of(ano, mes, dia);
                                album.setDtLancamento(data);
                        System.out.print("Caminho do Arquivo da Capa: "); input.nextLine(); caminho = input.nextLine();
                            album.setArqCapa(form.stringParaBytes(caminho));
                        System.out.println("Escolha o Artista: ");

                    }
                }
            }
        }
    }

    public void mostrarArtistas(){}

    public void mostrarAlbuns(){}
}
