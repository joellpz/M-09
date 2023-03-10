package UF3.SocketsAndServices.TCP.MultiClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorTCP {
    private int port;
    private ServidorTCP(int port ) {
        this.port = port;

    }

    private void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while(true) { //esperar connexió del client i llançar thread
                clientSocket = serverSocket.accept();
                //Llançar Thread per establir la comunicació
                //sumem 1 al numero de jugadors
                ThreadServidorTCP FilServidor = new ThreadServidorTCP(clientSocket);
                Thread client = new Thread(FilServidor);
                client.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        ServidorTCP srv = new ServidorTCP(5558);
        srv.listen();
    }
}
