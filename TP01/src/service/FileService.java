package service;

import java.io.*;
import java.util.HashMap;

public class FileService {

    private HashMap<String, String> relationships = new HashMap<>(); //aqui vão ficar armazenados os cpf's e os nomes

    private String pathEntrada; //caminho do meu arquivo de entrada

    private String pathSaida; //caminho do meu arquivo de saída

    private void readFile() throws IOException { //aqui temos esse throws pois a classe FileReader precisa dele

        BufferedReader reader = new BufferedReader(new FileReader(pathEntrada));//aqui basicamente a gente cria um objeto da classe BufferedReader que é capaz de ler linha a linha os registros do nosso arquivo de entrada

        String linha = reader.readLine();//método do nosso objeto que retorna uma linha do nosso arquivo
        while(linha != null) {//quando lermos todas as linhas do arquivo, essa variável linha vai ter o valor null. Então esse while se mantém enquanto temos linhas para ler
            String[] record = linha.split(";");//aqui nós dividimos a linha em duas, levando em consideração o ";". O retorno dessa função split é um vetor de String.
            if(isNumeric(record[0])){//como os registros podem estar invertidos, ou seja "cpf;nome" ou "nome;cpf", é preciso identificar em qual posição do array está o cpf, e nós o encontramos verificando qual dos elementos do array é numérico
                if(!relationships.containsKey(record[0])){//aqui é feita a verificação que impede que registros com o mesmo cpf sejam inserido no nosso HashMap, pois nós verificamos através do método "containsKey" se o HashMap já possui uma chave com o cpf da linha atual
                    relationships.put(record[0], record[1]);//se não houver nenhuma chave com o cpf atual, a gente coloca o cpf e o nome no hashmap
                }
            } else { //aqui é caso o registro esteja invertido, ou seja, se estiver como "nome;cpf"
                if(!relationships.containsKey(record[1])){
                    relationships.put(record[1], record[0]);
                }
            }
            linha = reader.readLine();
        }
        reader.close();//aqui não tem influencia na logica do nosso programa, só fecha o nosso leitor.
    }

    private boolean isNumeric(String record){ //método usado pra verificar se uma string é numerica.
        return record.matches( "\\d+");//esse método matches retorna verdadeiro ou falso, considerando se a string bate com o Regex passado como parametro. Qualquer coisa pesquisem um pouco sobre regex.
    }

    private void writeFile() throws IOException { //método que escreve no nosso arquivo de saída.
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathSaida)); //assim como no método de leitura, aqui a gente cria um objeto da classe BufferedWriter que é capaz de escrever no nosso aquivo
        //aqui é a parte mais complicadinha de entender, mas dá pra pegar. O que é feito é que eu pego todas as chaves do nosso HashMap, que no caso são os CPFs,
        //através desse método "keySet", e depois pra cada uma desssas chaves, eu vou usar meu objeto pra escrever no arquivo de saída.
        relationships.keySet().forEach(k -> {
            try {
                writer.write(k + ";" + relationships.get(k) + "\n");//aqui é onde de fato é escrito no arquivo, esse "k" corresponde ao cpf, e o relationships.get(k) vai retornar o nome associado aquele cpf.
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //um jeito de fzr a mesma coisa do que foi feito aí em cima é a seguinte.
        //Se ficarem com dúvidas, pesquisem sobre "forEach em java", mas resumindo, é basicamente um for que roda pra cada item de um vetor, collection e etc.
        /*
            Set<Strings> CPFs = relationships.keySet()
            for(String CPF : CPFs){
                try {
                    writer.write(CPF + ";" + relationships.get(CPF) + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        * */
        writer.close();
    }

    public void buildRelationship() throws IOException {
        readFile();
        writeFile();
    }

    public FileService(String pathEntrada, String pathSaida){ //Método construtor da classe.
        this.pathSaida = pathSaida;
        this.pathEntrada = pathEntrada;
    }

}
