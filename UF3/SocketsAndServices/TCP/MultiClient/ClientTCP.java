package UF3.SocketsAndServices.TCP.MultiClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTCP extends Thread {
    /* CLient TCP que ha endevinar un número pensat per SrvTcpAdivina_Obj.java */

    private String Nom;
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private Scanner scin;
    private boolean continueConnected;
    private Llista j;

    private ClientTCP(String hostname, int port) {
        try {
            socket = new Socket(InetAddress.getByName(hostname), port);
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (UnknownHostException ex) {
            System.out.println("Error de connexió. No existeix el host: " + ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        continueConnected = true;
        scin = new Scanner(System.in);
    }

    public void run() {
        String valor = "Y";
        while (continueConnected) {
            //Llegir info del servidor (estat del tauler)

            j = getRequest();
            System.out.println("a");
            if (j != null) {
                System.out.println(j.getNumberList());
                do {
                    System.out.println("Vols enviar una altre llista (Y/N)?");
                    valor = scin.nextLine();
                    if (valor.equalsIgnoreCase("N")) {
                        continueConnected = false;
                    } else if (!valor.equalsIgnoreCase("Y")) {
                        System.out.println("Error, valor incorrecte!");
                    }
                } while (!valor.equalsIgnoreCase("N") && !valor.equalsIgnoreCase("Y"));
            }

            if (continueConnected) {
                System.out.println("Introdueix els valor de la Llista de Integers, introdueix N quan hagis acabat:");

                List<Integer> llistaIntegers = new ArrayList<>();
                do {
                    try {
                        valor = scin.nextLine();
                        if (!Objects.requireNonNull(valor).equalsIgnoreCase("N"))
                            llistaIntegers.add(Integer.parseInt(valor));
                    } catch (NumberFormatException e) {
                        System.out.println("Error, mal format del valor!");
                    }
                } while (!Objects.requireNonNull(valor).equalsIgnoreCase("N"));
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(out);
                    oos.writeObject(new Llista(Nom, llistaIntegers));
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Crear codi de resposta a missatge
        }

        close(socket);

    }

    private Llista getRequest() {
        try {
            ObjectInputStream ois = new ObjectInputStream(in);
            j = (Llista) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return j;
    }


    private void close(Socket socket) {
        //si falla el tancament no podem fer gaire cosa, només enregistrar
        //el problema
        try {
            //tancament de tots els recursos
            if (socket != null && !socket.isClosed()) {
                if (!socket.isInputShutdown()) {
                    socket.shutdownInput();
                }
                if (!socket.isOutputShutdown()) {
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            //enregistrem l'error amb un objecte Logger
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        String jugador, ipSrv;

        //Demanem la ip del servidor i nom del jugador
        System.out.println("Ip del servidor?");
        Scanner sip = new Scanner(System.in);
        ipSrv = sip.next();
        System.out.println("Nom Client:");
        jugador = sip.next();

        ClientTCP clientTcp = new ClientTCP(ipSrv, 5558);
        clientTcp.Nom = jugador;
        clientTcp.start();
    }
}

