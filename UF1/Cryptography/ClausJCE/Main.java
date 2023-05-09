package UF1.Cryptography.ClausJCE;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void xifratDesxifratKeyGen() {
        try {
            SecretKey secretKey = Xifrats.keygenKeyGeneration(128);
            System.out.println("** What text do you want to test **");
            String textClar = sc.nextLine();

            System.out.println("** Cipher Process of " + textClar + " **");
            byte[] bytesXifrat = Xifrats.encryptData(secretKey, textClar.getBytes(), "AES");
            String textXifrat = new String(bytesXifrat, "UTF-8");

            System.out.println("*** Ciphered Text: " + textXifrat + " ***");

            System.out.println("** Decipher Process of " + textXifrat + " **");
            System.out.println("*** Deciphered Text: " + new String(Xifrats.decryptData(secretKey, bytesXifrat, "AES"), "UTF-8") + " ***");
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
        byte[] bytesXifrat = Xifrats.encryptData(secretKey, textClar.getBytes(), "AES");
        String textXifrat = new String(bytesXifrat, StandardCharsets.UTF_8);

        System.out.println("*** Ciphered Text: " + textXifrat + " ***");

        System.out.println("** To dechipher introduce the password: ");
        String passwd = sc.nextLine();
        System.out.println("** Decipher Process of " + textXifrat + " **");
        System.out.println("*** Deciphered Text: " +
                new String(Xifrats.decryptData(Xifrats.passwordKeyGeneration(passwd, 128),
                        bytesXifrat, "AES"), StandardCharsets.UTF_8) + " ***");
    }

    public static void testSecretKeyMethods() {
        SecretKey secretKey = Xifrats.keygenKeyGeneration(128);
        System.out.println("Algorithm: " + secretKey.getAlgorithm());
        System.out.println("Format: " + secretKey.getFormat());
        System.out.println("Encoded: " + new String(secretKey.getEncoded(), StandardCharsets.UTF_8));

    }

    public static void decryptFile(String pathEncryptedFile, String pathDictionary) {
        try {
            byte[] encrypted = Files.readAllBytes(Paths.get(pathEncryptedFile));
            BufferedReader br = new BufferedReader(new FileReader(pathDictionary));
            String line, decrypted = null;
            SecretKey secretKey;
            byte[] decryptedBytes;
            while ((line = br.readLine()) != null && decrypted == null) {
                secretKey = Xifrats.passwordKeyGeneration(line, 128);
                decryptedBytes = Xifrats.decryptData(secretKey, encrypted, "AES");
                if (decryptedBytes != null)
                    decrypted = new String(decryptedBytes, StandardCharsets.UTF_8);
            }
            System.out.println(decrypted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //A5, Exercici 1
    public static void genEncDecKeyPair() {
        KeyPair keys = Xifrats.randomGenerate(1024);
        System.out.println("** Introdueix un missatge a xifrar: **");
        String toEncrypt = sc.nextLine();
        byte[] toDecrypt = Xifrats.encryptData(keys.getPublic(), toEncrypt.getBytes(), "RSA");
        System.out.println("** Text Encryptat: " + new String(toDecrypt, StandardCharsets.UTF_8));
        String finalText = new String(Xifrats.decryptData(keys.getPrivate(), toDecrypt, "RSA"), StandardCharsets.UTF_8);
        System.out.println("** El text era: " + finalText + "\n");
    }

    public static void getLocalKeystore(String keystorePath, String keystorePassword) {
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(keystorePath);
            keyStore.load(fis, keystorePassword.toCharArray());
            fis.close();

            System.out.println(" * Tipus de keystore: " + keyStore.getType());
            System.out.println(" * Mida del magatzem: " + keyStore.size());

            // Obtener los alias de las claves almacenadas
            System.out.println("Alias de les claus emmagatzemades:");
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                System.out.println(" * Alias " + alias);
                Key key = keyStore.getKey(alias, keystorePassword.toCharArray());
                Certificate cert = keyStore.getCertificate(alias);
                System.out.println(" * Certificado de la clave " + alias + ":");
                System.out.println(cert);
                System.out.println(" * Algoritme de cifrat de la clau " + alias + ": " + key.getAlgorithm());
            }

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException |
                 UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveRamdomKeyIntoKeystore(String keystorePath, String keystorePassword) {
        KeyStore keyStore = null;
        try {
            keyStore = Xifrats.loadKeyStore(keystorePath,keystorePassword);
            SecretKey secretKey = Xifrats.keygenKeyGeneration(128);
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
            KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keystorePassword.toCharArray());
            System.out.println("Introduce el alias a esta clave: ");
            keyStore.setEntry(sc.nextLine(), secretKeyEntry, entryPassword);

            FileOutputStream fos = new FileOutputStream(keystorePath);
            keyStore.store(fos, keystorePassword.toCharArray());
            fos.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PublicKey getPublicKey(String fitxer) {
        try {
            System.out.println(" ** Getting the pulbicKey from " + fitxer + " ** ");
            FileInputStream fis = new FileInputStream(fitxer);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
            return cert.getPublicKey();
        } catch (FileNotFoundException | CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    public static PublicKey getPublicKey(KeyStore ks, String alias, String pwMyKey) throws Exception {
        Key key = ks.getKey(alias, pwMyKey.toCharArray());
        if (key instanceof PrivateKey) {
            Certificate cert = ks.getCertificate(alias);
            if (cert != null) {
                return cert.getPublicKey();
            }
        }
        throw new Exception("No s'ha pogut obtenir la clau per l'alias: " + alias);
    }


    public static void main(String[] args) {
        boolean rep = true;
        do {
            System.out.println("** Select an option **");
            System.out.println("1. Xifrar i desxifrar un text en clar amb una clau generada amb el codi 1.1.1");
            System.out.println("2. Xifrar i desxifrar un text en clar amb una clau (codi 1.1.2) generada a partir de la paraula de pas.");
            System.out.println("3. Prova alguns dels mètodes que proporciona la classe SecretKey");
            System.out.println("4. Dexifra arxiu amb diccionari.");
            System.out.println("5. A5 -> ex1.1");
            System.out.println("6. A5 -> ex1.2");
            System.out.println("7. A5 -> ex1.2.2");
            System.out.println("8. A5 -> ex1.3");
            System.out.println("9. A5 -> ex1.4");
            System.out.println("10. A5 -> ex1.5 i 1.6");
            System.out.println("0. Exit");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> xifratDesxifratKeyGen();
                case 2 -> xifratDexifratPassword();
                case 3 -> testSecretKeyMethods();
                case 4 ->
                        decryptFile("UF1/Cryptography/ClausJCE/resources/textamagat.crypt", "UF1/Cryptography/ClausJCE/resources/clausA4.txt");
                case 5 -> genEncDecKeyPair();
                case 6 -> getLocalKeystore("/home/isard/.keystore", "admin1234");
                case 7 -> saveRamdomKeyIntoKeystore("/home/isard/.keystore", "admin1234");
                case 8 -> {
                    PublicKey publicKey = getPublicKey("/home/isard/Escriptori/keytool/certjoel.crt");
                    System.out.println("Algorithm: " + publicKey.getAlgorithm());
                    System.out.println("Format: " + publicKey.getFormat());
                }
                case 9 -> {
                    try {

                        KeyStore ks = KeyStore.getInstance("PKCS12");
                        ks.load(new FileInputStream("/home/isard/.keystore"), "admin1234".toCharArray());

                        // Obtener la PublicKey del alias especificado
                        PublicKey publicKey = getPublicKey(ks, "lamevaclaum9", "admin1234");
                        System.out.println("Key: " + publicKey.toString());
                        // Imprimir la PublicKey
                        System.out.println("Algoritme: " + publicKey.getAlgorithm());
                        System.out.println("Format: " + publicKey.getFormat());
                        System.out.println("Encoded: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 10 -> {
                    KeyPair keys = Xifrats.randomGenerate(1024);
                    byte[] encrypted = Xifrats.signData("Texto Ejemplo".getBytes(), keys.getPrivate());
                    System.out.println(new String(encrypted));
                    System.out.println("Validació \"Ejemplo\":" +Xifrats.validateSignature("Ejemplo".getBytes(),encrypted,keys.getPublic()));
                    System.out.println("Validació \"Texto Ejemplo\":" +Xifrats.validateSignature("Texto Ejemplo".getBytes(),encrypted,keys.getPublic()));
                }
                case 0 -> rep = false;
            }
        } while (rep);
    }
}
//
//    mport java.io.FileInputStream;
//        import java.security.Key;
//        import java.security.KeyStore;
//
//
//public class KeystoreExample {
//    public static void main(String[] args) throws Exception {
//        String  = "ruta/al/keystore";
//        String  = "contraseña-del-keystore";
//
//        // Cargar el keystore
//
//
//        // Obtener información del keystore
//        System.out.println("Tipo de keystore: " + keyStore.getType());
//        System.out.println("Mida del magatzem: " + keyStore.size());
//
//        // Obtener los alias de las claves almacenadas
//        System.out.println("Alias de las claves almacenadas:");
//        for (String alias : keyStore.aliases()) {
//            System.out.println(alias);
//        }
//
//        // Obtener el certificado de una de las claves
//        String alias = "alias-de-la-clave";
//        Key key = keyStore.getKey(alias, keystorePassword.toCharArray());
//        Certificate cert = keyStore.getCertificate(alias);
//        System.out.println("Certificado de la clave " + alias + ":");
//        System.out.println(cert);
//
//        // Obtener el algoritmo de cifrado de una de las claves
//        System.out.println("Algoritmo de cifrado de la clave " + alias + ":");
//        System.out.println(key.getAlgorithm());
//    }
//}
