import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\V E A\\Documents\\remessa_teste.txt"; // Substitua pelo caminho do seu arquivo
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

        // Lê todo o conteúdo do arquivo
        byte[] fileBytes = Files.readAllBytes(Path.of(filePath));

        // Verifica se o arquivo tem exatamente 400 bytes
        if (fileBytes.length != 404) {
            System.out.println("O arquivo não possui 400 bytes.");
            return false;
        }

        // Verifica se arquivo começa com o número '0'
        if (fileBytes[0] != '0') {
            System.out.println("POSIÇÃO 01: O caractere precisar ser '0'.");
            return false;
            }

        // Verifica se o segundo caractere do arquivo é '1'
        if (fileBytes[1] != '1') {
            System.out.println("POSIÇÃO 02: O caractere precisar ser '1'.");
            return false;
        }

        // Verifica se da posição 3 a 9 está escrito 'REMESSA'
        String substring = fileContent.substring(2, 9);
        if (!substring.equals("REMESSA")) {
            System.out.println("POSIÇÃO 03 a 09: Os caracteres da não formam 'REMESSA'.");
            return false;
        }

        // Se chegou até aqui, o arquivo é considerado válido
        return true;
    }

}