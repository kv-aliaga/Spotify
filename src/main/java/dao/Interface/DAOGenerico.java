package dao.Interface;

import model.base.ModelBase;

import java.sql.ResultSet;

public interface DAOGenerico<TipoGenerico extends ModelBase>{
//    C -> CREATE
    void inserir(TipoGenerico tipoGenerico);

//    R -> READ
    ResultSet buscar();
    TipoGenerico buscarPorId(int id);

//    U -> UPDATE
    boolean atualizar(String campo, String novoValor, int id);

//    D -> DELETE
    void deletar(int id);
}
