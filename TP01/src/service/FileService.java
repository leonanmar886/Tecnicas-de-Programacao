package service;

import java.io.*;
import java.util.HashMap;

public class FileService {

    private HashMap<String, String> relationships = new HashMap<>();

    private String pathEntrada;

    private String pathSaida;

    private void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathEntrada));

        String linha = reader.readLine();
        while(linha != null) {
            String[] record = linha.split(";");
            if(!relationships.containsKey(record[0])){
                relationships.put(record[0], record[1]);
            }
            linha = reader.readLine();
        }
        reader.close();
    }

    private void writeFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathSaida));

        relationships.keySet().forEach(k -> {
            try {
                writer.write(k + ";" + relationships.get(k) + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.close();
    }

    public void buildRelationship() throws IOException {
        readFile();
        writeFile();
    }

    public FileService(String pathEntrada, String pathSaida){
        this.pathSaida = pathSaida;
        this.pathEntrada = pathEntrada;
    }

    public HashMap<String, String> getRelationships() {
        return relationships;
    }

}
