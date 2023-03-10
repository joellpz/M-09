package UF3.SocketsAndServices.TCP.MultiClient;

import java.io.*;
import java.net.Socket;

public class ThreadServidorTCP implements Runnable {
    /* Thread que gestiona la comunicació de SrvTcPAdivina_Obj.java i un cllient ClientTcpAdivina_Obj.java */

    private Socket clientSocket = null;
    private InputStream in = null;
    private OutputStream out = null;
    private boolean acabat;

    public ThreadServidorTCP(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        //Enllacem els canals de comunicació
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
        System.out.println("canals i/o creats amb un nou client");
    }

    @Override
    public void run() {
        Llista j = null;
        try {
            //Llegim la Llista
            ObjectInputStream ois = new ObjectInputStream(in);
            try {
                j = (Llista) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(j.getNom() +" a enviat aquesta Llista " + j.getNumberList());

            //Retornem la LLista Ordenada y Sense valor repetits
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject();
            oos.flush();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        //Enviem últim estat del tauler abans de acabar amb la comunicació i acabem
        try {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}