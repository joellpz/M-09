package UF1.Cryptography.ClausJCE;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
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

    public static void xifratDexifratPassword(boolean incorrect) {
        try {
            System.out.println("** Password to Cipher data **");
            SecretKey secretKey = Xifrats.passwordKeyGeneration(sc.nextLine(), 128);
            System.out.println("** What text do you want to test **");
            String textClar = sc.nextLine();

            System.out.println("** Cipher Process of " + textClar + " **");
            byte[] bytesXifrat = Xifrats.encryptData(secretKey, textClar.getBytes());
            String textXifrat = new String(bytesXifrat, "UTF-8");

            System.out.println("*** Ciphered Text: " + textXifrat + " ***");

            System.out.println("** To dechipher introduce the password: ");
            String passwd = sc.nextLine();
            System.out.println("** Decipher Process of " + textXifrat + " **");
            System.out.println("*** Deciphered Text: " +
                    new String(Xifrats.decryptData(
                            Xifrats.passwordKeyGeneration(passwd, 128),
                            bytesXifrat), "UTF-8") + " ***");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testSecretKeyMethods() {
        try {
            SecretKey secretKey = Xifrats.keygenKeyGeneration(128);
            System.out.println("Algorithm: " + secretKey.getAlgorithm());
            System.out.println("Format: " + secretKey.getFormat());
            System.out.println("Encoded: " + new String(secretKey.getEncoded(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
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
            System.out.println("0. Exit");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> xifratDesxifratKeyGen();
                case 2 -> xifratDexifratPassword(false);
                case 3 -> testSecretKeyMethods();
                case 0 -> rep = false;
            }
        } while (rep);
    }
}
