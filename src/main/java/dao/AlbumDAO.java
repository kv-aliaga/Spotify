package dao;

import model.Album;
import model.Artista;
import util.Conexao;
import util.Validador;
import dao.Interface.DAOGenerico;

import java.sql.*;

public class AlbumDAO implements DAOGenerico<Album> {
//    Criando Atributos
    private Validador val;
    private ResultSet rs;
    private Conexao conex = new Conexao();


//    === Métodos ===
//    C → Create
    @Override
    public void inserir(Album album){
        try (Connection conn = conex.conectar()){ // Abrindo conexão com o banco de dados
            String sql = "INSERT INTO album (NOME, DT_LANCAMENTO, ARQ_CAPA, COD_ARTISTA) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

//            Setando valores
            pstmt.setString(1, album.getNome());
            pstmt.setDate(2, Date.valueOf(album.getDtLancamento()));
            pstmt.setBytes(3, album.getArqCapa());
            pstmt.setInt(4, album.getCodArtista().getId());
            pstmt.executeUpdate();

            this.rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                album.setId(rs.getInt(1));
            }

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

//    R -> Read
    @Override
    public ResultSet buscar(){
        try (Connection conn = conex.conectar()){
            String sql = "SELECT al.id, al.nome as \"album\", al.dt_lancamento, al.arq_capa, ar.nome as \"artista\" \n" +
                    "FROM ALBUM al\n" +
                    "JOIN ARTISTA ar ON al.COD_ARTISTA = ar.ID\n";
            Statement stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            mostrarResultSet(rs);
            return rs;

        } catch (SQLException sqle){
            sqle.printStackTrace();
        } return null;
    }

    @Override
    public Album buscarPorId(int id){
        try (Connection conn = conex.conectar()){
//            Criando atributos
            Album album = new Album();
            Artista artista = new Artista();
            String sql = "SELECT al.id, al.nome as \"album\", al.dt_lancamento, al.arq_capa, ar.nome as \"artista\"\n" +
                    "FROM ALBUM al\n" +
                    "JOIN ARTISTA ar ON al.COD_ARTISTA = ar.ID\n" +
                    "WHERE al.id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores e mostrando
            pstmt.setInt(1, id);
            this.rs = pstmt.executeQuery();

            if (rs.next()){
//                Valores de Artista
                artista.setNome(rs.getString("artista"));

//                Valores de Album
                album.setId(rs.getInt("id"));
                album.setNome(rs.getString("album"));
                album.setDtLancamento(rs.getDate("dt_lancamento").toLocalDate()); //fixme formatar na classe formatador
                album.setArqCapa(rs.getBytes("arq_capa"));
                album.setCodArtista(artista);

                return album;
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }

//    U -> Update
    @Override
    public boolean atualizar(String campo, String novoValor, int id){
        if (this.val.albumColunaValida(campo)){
            try (Connection conn = conex.conectar()){
                String sql = "UPDATE ALBUM SET " + campo + " = ? WHERE ID = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

//                Setando valores
                pstmt.setString(1, novoValor);
                pstmt.setInt(2, id);

                int linhasAfetadas = pstmt.executeUpdate();
                return linhasAfetadas > 0;

            } catch (SQLException sqle){
                sqle.printStackTrace();
                return false;
            }
        } return false;
    }

//    D -> Delete
    @Override
    public void deletar(int id){
        try (Connection conn = conex.conectar()) {
            String sql = "DELETE FROM ALBUM\n" +
                    "WHERE ID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores
            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

//    Mostrar ResultSet
    public void mostrarResultSet(ResultSet rs){
        try {
            while (rs.next()){
                String linha = "\n=== " + rs.getString("album") + " ===" +
                        "\nID: " + rs.getInt("id") +
                        "\nData de Lançamento: " + rs.getDate("dt_lancamento") +
                        "\nArquivo da Capa: " + rs.getBytes("arq_capa") +
                        "\nArtista: " + rs.getString("artista");
                System.out.println(linha);
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}