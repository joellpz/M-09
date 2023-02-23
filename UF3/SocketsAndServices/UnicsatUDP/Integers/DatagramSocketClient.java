package UF3.SocketsAndServices.UnicsatUDP.Integers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class DatagramSocketClient {
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DatagramSocketClient datagramSocketClient = new DatagramSocketClient();
        try {
            datagramSocketClient.init("localhost",2375);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void init(String host, int port) throws IOException {
        serverIP = InetAddress.getByName(host);
        serverPort = port;
        socket = new DatagramSocket();

        runClient();
    }

    public void runClient() throws IOException {
        byte [] receivedData = new byte[1024];
        byte [] sendingData;

        //a l'inici
        sendingData = getFirstRequest();
        //el servidor atén el port indefinidament
        while(mustContinue(sendingData)){
            DatagramPacket packet = new DatagramPacket(sendingData,
                    sendingData.length,
                    serverIP,
                    serverPort);
            //enviament de la resposta
            socket.send(packet);

            //creació del paquet per rebre les dades
            packet = new DatagramPacket(receivedData, 1024);
            //espera de les dades
            socket.receive(packet);
            //processament de les dades rebudes i obtenció de la resposta
            sendingData = getDataToRequest(packet.getData(), packet.getLength());
        }

        System.out.println("Tancant....");
    }

    private byte[] getDataToRequest(byte[] data, int length) {
        //procés diferent per cada aplicació
        String msg = new String(data,0,length);
        int n = ByteBuffer.wrap(data).getInt();
        if (n == 0) System.out.println("** Has acertat el Numero!! **");
        else if (n == 1) System.out.println("Més petit.");
        else System.out.println("Més GRAN.");

        System.out.println("Valor a enviar:");
        return ByteBuffer.allocate(4).putInt(sc.nextInt()).array();
    }



    private byte[] getFirstRequest() {
        //procés diferent per cada aplicació
        System.out.println("El Juego del Numero Random, introduce tu numero:");
        return ByteBuffer.allocate(4).putInt(sc.nextInt()).array();

    }


    private boolean mustContinue(byte[] sendingData) {
        //procés diferent per cada aplicació
        if (new String(sendingData,0,sendingData.length).equals("adeu")) return false;
        else return true;
    }
}