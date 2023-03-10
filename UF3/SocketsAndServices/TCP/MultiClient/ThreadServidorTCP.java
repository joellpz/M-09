package UF3.SocketsAndServices.TCP.MultiClient;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;


public class ThreadServidorTCP implements Runnable {
    /* Thread que gestiona la comunicació de SrvTcPAdivina_Obj.java i un cllient ClientTcpAdivina_Obj.java */

    private InputStream in = null;
    private OutputStream out = null;
    private boolean acabat;

    public ThreadServidorTCP(Socket clientSocket) throws IOException {
        //Enllacem els canals de comunicació
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
        System.out.println("canals i/o creats amb un nou client");
    }

    @Override
    public void run() {
        Llista j = null;
        try {
            while (true) {
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(j);
                oos.flush();

                try {
                    ObjectInputStream ois = new ObjectInputStream(in);
                    j = (Llista) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (j != null) {
                    System.out.println(j.getNom() + " a enviat aquesta Llista " + j.getNumberList());
                    //Retornem la LLista Ordenada y Sense valor repetits
                    j.setNumberList(new HashSet<>(j.getNumberList()).stream().sorted().toList());
                    //clientSocket.close();
                } else System.out.println("Client Desconectat!");
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

}