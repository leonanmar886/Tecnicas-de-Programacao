import service.FileService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final String pathPrefix = "C:\\UFC\\Tecnicas De Programacao\\TP01\\src\\service\\"; //pasta dos meus arquivos de entrada e de saída
        FileService service = new FileService(pathPrefix + "testeEntrada.txt", pathPrefix + "testeSaida.txt"); //cria um novo objeto com os caminhos dos arquivos de entrada e saída
        try{ //essa linha tem que estar em um bloco try catch pois ele é um método que pode lançar um erro
            service.buildRelationship();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}