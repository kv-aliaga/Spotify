package util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
//    Criando atributos
    private Dotenv dotenv = Dotenv.load();
    private String driver;
    private String url;
    private String user;
    private String password;

//    Método Contrutor
    public Conexao(){
        this.driver = dotenv.get("DRIVER");
        this.url = dotenv.get("DB_URL");
        this.user = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASSWORD");
    }

    public Connection conectar(){
        Connection conn = null;

        try {
//            Conectando Driver Postgresql
            Class.forName(this.driver);

//            Criando conexão com Banco de Dados
            conn = DriverManager.getConnection(this.url, this.user, this.password);

        } catch (SQLException | ClassNotFoundException exce){
            exce.printStackTrace();
        } finally {
            return conn;
        }
    }

    public void desconectar(Connection conn){
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public String getPassword(){
        return password;
    }
}
