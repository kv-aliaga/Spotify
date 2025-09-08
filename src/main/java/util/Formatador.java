package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Formatador {
//    Converter três int para Localdate
    public LocalDate intParaLocalDate(int dia, int mes, int ano){
        return LocalDate.of(ano, mes, dia);
    }

//    Converter double para Tempo
    public String doubleParaTempo(double tempoDouble){
        int minutos = (int) tempoDouble;
        int segundos = (int) ((tempoDouble - minutos) * 100);

        return (minutos + "min " + segundos + "seg");
    }

//    Converter Caminho para bytes[]
    public byte[] stringParaBytes(String caminho){
        try {
            return Files.readAllBytes(Paths.get(caminho));
        } catch (IOException ioe){
            ioe.printStackTrace();
        } return null;
    }

//    Método para testes
    public void laHaine(){
        System.out.println("Até aqui tudo bem");
    }
}