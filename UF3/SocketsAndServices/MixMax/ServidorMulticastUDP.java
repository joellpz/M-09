package UF3.SocketsAndServices.MixMax;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMulticastUDP implements Runnable {
    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    boolean continueRunning = true;
    Tauler t;

    public ServidorMulticastUDP(int portValue, String strIp, Tauler t) throws IOException {
        socket = new MulticastSocket(portValue);
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        this.t = t;
    }



    @Override
    public void run() {
        DatagramPacket packet;
        byte [] sendingData;

        while(t.map_jugadors.size() != t.acabats || t.map_jugadors.size()==0){
            sendingData = t.toString().getBytes();
            packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }


        }
        System.out.println("Parat!");
        socket.close();
    }
}
