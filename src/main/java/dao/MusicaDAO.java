package dao;

import model.Album;
import model.Musica;
import util.Conexao;
import dao.Interface.DAOGenerico;
import util.Formatador;
import util.Validador;

import java.sql.*;
import java.time.Duration;

public class MusicaDAO implements DAOGenerico<Musica> {
    //    Criando atributos
    private Conexao conex = new Conexao();
    private ResultSet rs;
    private Validador val;
    private Formatador form;

    //    Construtor
    public MusicaDAO(){
        this.val = new Validador();
        this.form = new Formatador();
    }

    //    == MÉTODOS ==
    //    C -> Create
    @Override
    public void inserir(Musica musica) {
        String sql = "INSERT INTO MUSICA (NOME, DURACAO, ARQ_AUDIO, COD_ALBUM) VALUES (?, ?, ?, ?)";
        try (Connection conn = conex.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Setando valores de pstmt
            pstmt.setString(1, musica.getNome());
            // Enviar Duration diretamente para coluna INTERVAL
            pstmt.setObject(2, musica.getDuracao()); // java.time.Duration -> INTERVAL
            pstmt.setBytes(3, musica.getArqAudio());
            pstmt.setInt(4, musica.getCodAlbum().getId());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    musica.setId(keys.getInt(1));
                }
            }

        } catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }

    //    R -> Read
    @Override
    public ResultSet buscar() {
        try (Connection conn = conex.conectar()){
            String sql = "SELECT m.id, m.nome, m.duracao, m.arq_audio, a.nome " +
                         "FROM musica m " +
                         "JOIN ALBUM a on a.ID = m.COD_ALBUM;";
            Statement stmt = conn.createStatement();

            this.rs = stmt.executeQuery(sql);

        } catch (SQLException sqle){
            sqle.printStackTrace();
        } finally {
            return this.rs;
        }
    }

    @Override
    public Musica buscarPorId(int id) {
        try (Connection conn = conex.conectar()){
            Musica musica = new Musica();
            Album album = new Album();
            String sql = "SELECT m.id, m.nome as \"musica\", m.duracao, m.arq_audio, a.nome as \"album\" " +
                         "FROM musica m " +
                         "JOIN ALBUM a on a.ID = m.COD_ALBUM " +
                         "WHERE m.id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores
            pstmt.setInt(1, id);
            this.rs = pstmt.executeQuery();

//            Mostrando valores
            if (this.rs.next()){
                album.setNome(rs.getString("album"));

//                Valores de Música
                musica.setId(rs.getInt("id"));
                musica.setNome(rs.getString("musica"));
                musica.setDuracao(rs.getDouble("duracao"));
                musica.setArqAudio(rs.getBytes("arq_audio"));
                musica.setCodAlbum(album);

                return musica;
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }

    //    U -> Update
    @Override
    public boolean atualizar(String campo, String novoValor, int id) {
        if (this.val.musicaColunaValida(campo)){
            try (Connection conn = conex.conectar()){
                String sql = "UPDATE MUSICA SET " + campo + " = ? WHERE ID = ?;";
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
        }
        return false;
    }

    //    D -> Delete
    @Override
    public void deletar(int id) {
        try (Connection conn = conex.conectar()){
            String sql = "DELETE FROM MUSICA WHERE ID = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}