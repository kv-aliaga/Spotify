package util.interfaces;

public interface ValidadorColunaGenerico {
//    método genérico
    default boolean colunaVaida(String campo, String[] colunasValidas){
        for (int i = 0; i < colunasValidas.length; i++) {
            if (colunasValidas[i].equals(campo)){
                return true;
            }
        } return false;
    }

//    métodos específicos
    default boolean albumColunaValida(String campo){
        return colunaVaida(campo, new String[]{"nome", "dt_lancamento", "arq_capa", "cod_artista"});
    }

    default boolean artistaColunaValida(String campo){
        return colunaVaida(campo, new String[]{"nome", "arq_banner"});
    }

    default boolean musicaColunaValida(String campo){
        return colunaVaida(campo, new String[]{"nome", "duracao", "arq_audio", "cod_album"});
    }

    default boolean singleColunaValida(String campo){
        return colunaVaida(campo, new String[]{"nome", "duracao", "dt_lancamento", "arq_audio", "arq_capa", "cod_artista"});
    }
}

