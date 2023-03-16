package UF3.SocketsAndServices.UDP.UnicsatUDP.Strings;

import UF3.SocketsAndServices.UDP.UnicsatUDP.Integers.SecretNum;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class DatagramSocketServer {

    DatagramSocket socket;
    SecretNum secret;
    int secretNumMax;

    public static void main(String[] args) {
        DatagramSocketServer datagramSocketServer = new DatagramSocketServer();
        try {
            datagramSocketServer.init(2735);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void init(int port) throws IOException {
        socket = new DatagramSocket(port);
        secretNumMax = 50;
        secret = new SecretNum(secretNumMax);
        runServer();
    }

    public void runServer() throws IOException {
        byte[] receivingData = new byte[1024];
        byte[] sendingData;
        InetAddress clientIP;
        int clientPort;

        //el servidor atén el port indefinidament
        while (true) {
            //creació del paquet per rebre les dades
            DatagramPacket packet = new DatagramPacket(receivingData, 1024);
            //espera de les dades
            socket.receive(packet);
            //processament de les dades rebudes i obtenció de la resposta
            sendingData = processData(packet.getData(), packet.getLength());
            //obtenció de l'adreça del client
            clientIP = packet.getAddress();
            //obtenció del port del client
            clientPort = packet.getPort();
            //creació del paquet per enviar la resposta
            packet = new DatagramPacket(sendingData, sendingData.length,
                    clientIP, clientPort);
            //enviament de la resposta
            socket.send(packet);
        }
    }

    private byte[] processData(byte[] data, int length) {
        //procés diferent per cada aplicació
        String msg = new String(data, 0, length);
        System.out.println(msg);
        return msg.toUpperCase().getBytes();
    }
}