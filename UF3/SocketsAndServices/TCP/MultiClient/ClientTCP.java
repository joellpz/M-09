package UF3.SocketsAndServices.TCP.MultiClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
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
    private Llista t;

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
        String msg = null;
        while (continueConnected) {
            //Llegir info del servidor (estat del tauler)
            t = getRequest();

            //Crear codi de resposta a missatge
            System.out.println("Llista Ordenada Rebuda ....");
            System.out.println(t.getNumberList());


            System.out.println("Entra un número: ");
            //j.num = scin.nextInt();
            //j.Nom = Nom;

            try {
                ObjectOutputStream oos = null;
                oos = new ObjectOutputStream(out);
                //oos.writeObject(j);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        close(socket);

    }

    private Llista getRequest() {
        try {
            ObjectInputStream ois = new ObjectInputStream(in);
            t = (Llista) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
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
        System.out.println("Nom jugador:");
        jugador = sip.next();

        ClientTCP clientTcp = new ClientTCP(ipSrv, 5558);
        clientTcp.Nom = jugador;
        clientTcp.start();
    }
}

