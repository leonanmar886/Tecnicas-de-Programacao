import service.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String pathPrefix = "C:\\UFC\\Tecnicas De Programacao\\TP01\\src\\service\\"; //pasta dos meus arquivos de entrada e de saída
        List<String> pathSaidas = new ArrayList<>();
        pathSaidas.add(pathPrefix + "testeSaida.txt");
        pathSaidas.add(pathPrefix + "testeEntrada2.txt");
        FileService service = new FileService(pathPrefix + "testeEntrada.txt", pathSaidas); //cria um novo objeto com os caminhos dos arquivos de entrada e saída
        try{ //essa linha tem que estar em um bloco try catch pois ele é um método que pode lançar um erro
            service.buildRelationship();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}