package UF3.SocketsAndServices.MulticastUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerFrasesAleatorias {
    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    boolean continueRunning = true;
    List<String> frases;

    public ServerFrasesAleatorias(int portValue, String strIp) throws IOException {
        socket = new MulticastSocket(portValue);
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        frases = initFrases();
    }

    public void runServer() throws IOException{
        DatagramPacket packet;
        byte [] sendingData;

        while(continueRunning){
            sendingData = frases.get((int) (Math.random()*15)).getBytes();
            packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, port);
            socket.send(packet);

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }


        }
        socket.close();
    }

    public List<String> initFrases() {
        List<String> list = new ArrayList<>();
        list.add("El éxito no es definitivo, el fracaso no es fatal, es el coraje de continuar lo que cuenta.");
        list.add("La felicidad no es tener lo que quieres, sino querer lo que tienes.");
        list.add("Si quieres cambiar el mundo, cámbiate a ti mismo.");
        list.add("No te preocupes por los fracasos, preocúpate por las oportunidades que pierdes al no intentarlo.");
        list.add("La vida es 10% lo que nos sucede y 90% cómo reaccionamos ante ello.");
        list.add("La belleza está en los ojos del espectador.");
        list.add("El éxito es la suma de pequeños esfuerzos repetidos día tras día.");
        list.add("La educación es el arma más poderosa que puedes usar para cambiar el mundo.");
        list.add("Todo lo que siempre has querido está al otro lado del miedo.");
        list.add("Si puedes soñarlo, puedes lograrlo.");
        list.add("El optimismo es la fe que conduce al logro. Nada puede ser hecho sin esperanza y confianza.");
        list.add("La creatividad es la inteligencia divirtiéndose.");
        list.add("No hagas a los demás lo que no quieras que te hagan a ti.");
        list.add("La paciencia es amarga, pero sus frutos son dulces.");
        list.add("La libertad no es tener lo que quieres, sino querer lo que tienes.");
        return list;
    }

    public static void main(String[] args) throws IOException {
        //Canvieu la X.X per un número per formar un IP.
        //Que no sigui la mateixa que la d'un altre company
        ServerFrasesAleatorias serverFrasesAleatorias = new ServerFrasesAleatorias(5557, "224.0.11.111");
        serverFrasesAleatorias.runServer();
        System.out.println("Parat!");

    }

}
