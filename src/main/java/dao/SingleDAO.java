package dao;

import dao.Interface.DAOGenerico;
import model.Artista;
import model.Single;
import util.Conexao;
import util.Formatador;
import util.Validador;

import java.sql.*;
import java.time.Duration;

public class SingleDAO implements DAOGenerico<Single> {
//    Criando atributos
    private Conexao conex = new Conexao();
    private ResultSet rs;
    private Formatador form = new Formatador();
    private Validador val = new Validador();

//    C -> CREATE
    @Override
    public void inserir(Single single) {
        try (Connection conn = conex.conectar()){
            String sql = "INSERT INTO SINGLE (NOME, DURACAO, DT_LANCAMENTO, ARQ_AUDIO, ARQ_CAPA, COD_ARTISTA) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores
            pstmt.setString(1, single.getNome());
            pstmt.setDouble(2, single.getDuracao());
            pstmt.setDate(3, Date.valueOf(single.getDtLancamento()));
            pstmt.setBytes(4, single.getArqAudio());
            pstmt.setBytes(5, single.getArqCapa());
            pstmt.setInt(6, single.getCodArtista().getId());
            pstmt.executeUpdate();

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

//    R -> READ
    @Override
    public ResultSet buscar() {
        try (Connection conn = conex.conectar()){
            String sql = "SELECT s.id, s.nome as \"single\", s.duracao, s.dt_lancamento, s.arq_audio, s.arq_capa, a.nome as \"artista\"\n" +
                    "FROM single s\n" +
                    "JOIN artista a on a.id = s.cod_artista;";
            Statement stmt = conn.createStatement();

            this.rs = stmt.executeQuery(sql);

        } catch (SQLException sqle){
            sqle.printStackTrace();
        } finally {
            return this.rs;
        }
    }

    @Override
    public Single buscarPorId(int id) {
        try (Connection conn = conex.conectar()) {
            Single single = new Single();
            Artista artista = new Artista();

            String sql = "SELECT S.ID, S.NOME AS \"single\", S.DURACAO, S.DT_LANCAMENTO, S.ARQ_AUDIO, S.ARQ_CAPA, A.NOME AS \"artista\"\n" +
                    "FROM SINGLE S\n" +
                    "JOIN ARTISTA A ON A.ID = S.COD_ARTISTA\n" +
                    "WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores
            pstmt.setInt(1, id);
            this.rs = pstmt.executeQuery();

//            Mostrando valores
            if (this.rs.next()) {
                artista.setNome(rs.getString("artista")); // Dados de Artista

                single.setNome(rs.getString("single")); // Dados de Single
                single.setDuracao(rs.getDouble("duracao"));
                single.setDtLancamento(rs.getDate("dt_lancamento").toLocalDate());
                single.setArqAudio(rs.getBytes("arq_audio"));
                single.setArqCapa(rs.getBytes("arq_capa"));
                single.setCodArtista(artista);

                return single;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } return null;
    }

    //    U -> UPDATE
    @Override
    public boolean atualizar(String campo, String novoValor, int id) {
        if (this.val.singleColunaValida(campo)){
            try (Connection conn = conex.conectar()){
                String sql = "UPDATE ALBUM\n" +
                        "SET" + campo + " = ?\n" +
                        "WHERE ID = ?;";
                PreparedStatement pstmt = conn.prepareStatement(sql);

//                Setando valores
                pstmt.setString(1, novoValor);
                pstmt.setInt(2, id);

                int linhasAfetadas = pstmt.executeUpdate();
                return linhasAfetadas > 0;

            } catch (SQLException sqle){
                sqle.printStackTrace();
            }
        } return false;
    }

    //    D -> DELETE
    @Override
    public void deletar(int id) {
        try (Connection conn = conex.conectar()){
            String sql = "DELETE FROM SINGLE WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores
            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
