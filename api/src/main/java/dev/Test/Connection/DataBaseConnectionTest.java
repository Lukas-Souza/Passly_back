package dev.Test.Connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBaseConnectionTest implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    public DataBaseConnectionTest(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            
        jdbcTemplate.execute((Connection conn)->{
           
            System.out.println("*---------------------------------------------------------------");
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("| STATUS: \u001B[32mConexão realizada com sucesso!\u001B[0m");
            System.out.println("| URL: " + metaData.getURL());
            System.out.println("| Usuário: " + metaData.getUserName());
            System.out.println("| Driver: " + metaData.getDriverName());
            System.out.println("| Versão Driver: " + metaData.getDriverVersion());
            System.out.println("| Banco: " + metaData.getDatabaseProductName());
            System.out.println("| Versão Banco: " + metaData.getDatabaseProductVersion());
            System.out.println("*---------------------------------------------------------------\n");
            
            return null;
        });
        } catch (Exception e) {
            System.out.println("*\n++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("| STATUS: \"\\u001B[31mErro ao conectar ao banco!\\u001B[0m\"");
            System.out.println("| Menssagen: "+e.getMessage().toString()+"\n");
            System.out.println("*--------------------------------------------------------------\n");

        }
        
    }
    
    //  public String testarBanco() {
    //     try {
    //         

    //         return resultado == 1
    //             ? "Conexão com o banco realizada com sucesso!"
    //             : "Falha na consulta.";
    //     } catch (Exception e) {
    //         return "Erro ao conectar ao banco: " + e.getMessage();
    //     }
}
