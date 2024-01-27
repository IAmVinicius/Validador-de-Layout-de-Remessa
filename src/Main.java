import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\V E A\\Documents\\remessa_teste.txt"; // caminho do arquivo
        try {
            if (validateRemessaFile(filePath)) {
                System.out.println("O arquivo de remessa é válido.");
            } else {
                System.out.println("O arquivo de remessa não é válido.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateRemessaFile(String filePath) throws IOException {
        // Verifica se o arquivo existe
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Arquivo não encontrado.");
            return false;
        }

        // Lê todo o conteúdo do arquivo como uma string
        String fileContent = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);

        // Verifica se o arquivo tem exatamente 400 bytes
        if (fileContent.length() != 404) {
            System.out.println("O arquivo não possui 400 bytes.");
            return false;
        }

        // Verifica se o primeiro caractere é '0'
        if (fileContent.charAt(0) != '0') {
            System.out.println("O primeiro caractere do arquivo não é '0'.");
            return false;
        }

        // Verifica se o segundo caractere é '1'
        if (fileContent.charAt(1) != '1') {
            System.out.println("O segundo caractere do arquivo não é '1'.");
            return false;
        }

        // Verifica se da posição 3 a 9 está escrito 'REMESSA'
        String substring = fileContent.substring(2, 9);
        if (!substring.equals("REMESSA")) {
            System.out.println("Os caracteres da posição 3 a 9 não formam 'REMESSA'.");
            return false;
        }

        // Verifica se da posição 10 a 11 está presente o número '01'
        String substringNumber = fileContent.substring(9, 11);
        if (!substringNumber.equals("01")) {
            System.out.println("Os caracteres da posição 10 a 11 não formam '01'.");
            return false;
        }

        // Verifica se da posição 12 a 26 está escrito 'COBRANCA'
        String substring2 = fileContent.substring(11, 19);
        if (!substring2.equals("COBRANCA")) {
            System.out.println("Os caracteres da posição 11 a 19 não formam 'COBRANCA'.");
            return false;
        }

        // Verifica se da posição 31 a 32 está presente '00'
        String substringNumber2 = fileContent.substring(30, 32);
        if (!substringNumber2.equals("00")) {
            System.out.println("Os caracteres da posição 31 a 32 não formam '00'.");
            return false;
        }

        // Verifica o código do banco '341' nas posições 77 a 79
        String substringNumber3 = fileContent.substring(76, 79);
        if (!substringNumber3.equals("341")) {
            System.out.println("Os caracteres da posição 77 a 79 não formam o código do banco '341'.");
            return false;
        }

        // Verifica nome do banco por extenso 'BANCO ITAU SA '
        String substring3 = fileContent.substring(79, 94);
        if (!substring3.equals("BANCO ITAU SA  ")) {
            System.out.println("Os caracteres da posição 80 a 94 não formam o nome do banco 'BANCO ITAU SA  '.");
            return false;
        }

        // Verifica caractere sequencial remessa. Não pode ser '00000'
        String stringNumber4 = fileContent.substring(394, 400);
        if (stringNumber4.equals("000000")) {
            System.out.println("O número sequencial remessa não pode ser '000000' utilizar da posição 395 a 400 para preenchimento do número.");
            return false;
        }

        // Se chegou até aqui, o arquivo é considerado válido
        return true;
    }
}