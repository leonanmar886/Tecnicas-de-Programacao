import service.FileService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final String pathPrefix = "C:\\UFC\\Tecnicas De Programacao\\TP01\\src\\service\\";
        FileService service = new FileService(pathPrefix + "testeEntrada.txt", pathPrefix + "testeSaida.txt");
        try{
            service.buildRelationship();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}