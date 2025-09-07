package dao;

import model.Artista;
import util.Conexao;
import util.Validador;
import dao.Interface.DAOGenerico;

import java.sql.*;

public class ArtistaDAO implements DAOGenerico<Artista> {
//    Criando Atributos
    private Validador val;
    private ResultSet rs;
    private Conexao conex = new Conexao();

// === Métodos ====
//    C -> Create
    @Override
    public void inserir(Artista artista) {
        try (Connection conn = conex.conectar()){ // Abrindo (e fechando) conexão com try-with-resources
            String sql = "INSERT INTO ARTISTA (NOME, ARQ_BANNER) VALUES (?, ?)"; // Script SQL
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Prepara Script

//            Setando valores
            pstmt.setString(1, artista.getNome());
            pstmt.setBytes(2, artista.getArqBanner());
            pstmt.executeUpdate();

            this.rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                artista.setId(rs.getInt(1));
            }


        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

//    R -> Read
    @Override
    public ResultSet buscar() {
        try (Connection conn = conex.conectar()){
            String sql = "SELECT id, nome, arq_banner\n" +
                    "FROM ARTISTA;";
            Statement stmt = conn.createStatement();

            this.rs = stmt.executeQuery(sql);

        } catch (SQLException sqle){
            sqle.printStackTrace();
        } finally {
            return this.rs;
        }
    }

    @Override
    public Artista buscarPorId(int id) {
        try (Connection conn = conex.conectar()){
//            Criando atributos
            Artista artista = new Artista();
            String sql = "SELECT id, nome, arq_banner\n" +
                    "FROM ARTISTA\n" +
                    "WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores
            pstmt.setInt(1, id);
            this.rs = pstmt.executeQuery();

//            Mostrando valores
            if (this.rs.next()){
                artista.setId(rs.getInt("id"));
                artista.setNome(rs.getString("nome"));
                artista.setArqBanner(rs.getBytes("arq_banner"));

                return artista;
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        } return null;
    }

//    U -> Update
    @Override
    public boolean atualizar(String campo, String novoValor, int id) {
        if (this.val.artistaColunaValida(campo)){
            try (Connection conn = conex.conectar()){
                String sql = "UPDATE ARTISTA SET " + campo + " = ? WHERE ID = ?";
                PreparedStatement pstmt = conn.prepareCall(sql);

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

//    D  -> Delete
    @Override
    public void deletar(int id) {
        try (Connection conn = conex.conectar()){
            String sql = "DELETE FROM ARTISTA WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            Setando valores
            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
