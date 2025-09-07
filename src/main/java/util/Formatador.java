package util;

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

//    Método para testes
    public void laHaine(){
        System.out.println("Até aqui tudo bem");
    }
}