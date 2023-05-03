package UF1.Cryptography.ClausJCE;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void xifratDesxifratKeyGen() {
        try {
            SecretKey secretKey = Xifrats.keygenKeyGeneration(128);
            System.out.println("** What text do you want to test **");
            String textClar = sc.nextLine();

            System.out.println("** Cipher Process of " + textClar + " **");
            byte[] bytesXifrat = Xifrats.encryptData(secretKey, textClar.getBytes());
            String textXifrat = new String(bytesXifrat, "UTF-8");

            System.out.println("*** Ciphered Text: " + textXifrat + " ***");

            System.out.println("** Decipher Process of " + textXifrat + " **");
            System.out.println("*** Deciphered Text: " + new String(Xifrats.decryptData(secretKey, bytesXifrat), "UTF-8") + " ***");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void xifratDexifratPassword() {
        System.out.println("** Password to Cipher data **");
        SecretKey secretKey = Xifrats.passwordKeyGeneration(sc.nextLine(), 128);
        System.out.println("** What text do you want to test **");
        String textClar = sc.nextLine();

        System.out.println("** Cipher Process of " + textClar + " **");
        byte[] bytesXifrat = Xifrats.encryptData(secretKey, textClar.getBytes());
        String textXifrat = new String(bytesXifrat, StandardCharsets.UTF_8);

        System.out.println("*** Ciphered Text: " + textXifrat + " ***");

        System.out.println("** To dechipher introduce the password: ");
        String passwd = sc.nextLine();
        System.out.println("** Decipher Process of " + textXifrat + " **");
        System.out.println("*** Deciphered Text: " +
                new String(Xifrats.decryptData(
                        Xifrats.passwordKeyGeneration(passwd, 128),
                        bytesXifrat), StandardCharsets.UTF_8) + " ***");
    }

    public static void testSecretKeyMethods() {
        SecretKey secretKey = Xifrats.keygenKeyGeneration(128);
        System.out.println("Algorithm: " + secretKey.getAlgorithm());
        System.out.println("Format: " + secretKey.getFormat());
        System.out.println("Encoded: " + new String(secretKey.getEncoded(), StandardCharsets.UTF_8));

    }

    public static void decryptFile(String pathEncryptedFile, String pathDictionary){
        try {
            byte[] encrypted = Files.readAllBytes(Paths.get(pathEncryptedFile));
            BufferedReader br = new BufferedReader(new FileReader(pathDictionary));
            String line, decrypted = null;
            SecretKey secretKey;
            byte[] decryptedBytes;
            while ((line = br.readLine()) != null && decrypted == null){
                secretKey = Xifrats.passwordKeyGeneration(line, 128);
                decryptedBytes = Xifrats.decryptData(secretKey, encrypted);
                if (decryptedBytes != null)
                    decrypted = new String(decryptedBytes, StandardCharsets.UTF_8);
            }
            System.out.println(decrypted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        boolean rep = true;
        do {
            System.out.println("** Select an option **");
            System.out.println("1. Xifrar i desxifrar un text en clar amb una clau generada amb el codi 1.1.1");
            System.out.println("2. Xifrar i desxifrar un text en clar amb una clau (codi 1.1.2) generada a partir de la paraula de pas.");
            System.out.println("3. Prova alguns dels mètodes que proporciona la classe SecretKey");
            System.out.println("4. Dexifra arxiu amb diccionari.");
            System.out.println("0. Exit");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> xifratDesxifratKeyGen();
                case 2 -> xifratDexifratPassword();
                case 3 -> testSecretKeyMethods();
                case 4 -> decryptFile("UF1/Cryptography/ClausJCE/resources/textamagat.crypt","UF1/Cryptography/ClausJCE/resources/clausA4.txt");
                case 0 -> rep = false;
            }
        } while (rep);
    }
}
