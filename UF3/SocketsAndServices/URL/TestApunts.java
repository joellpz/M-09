package UF3.SocketsAndServices.URL;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TestApunts {

    private static void printContentWithSpecificEtiqueta(URL url, String etiqueta){
        InputStream in;
        char[] cbuf = new char[512];
        int caractersLlegits;

        try {
            in = url.openStream();
            InputStreamReader inr = new InputStreamReader(in);
            while((caractersLlegits=inr.read(cbuf))!=-1){
                String str = String.copyValueOf(cbuf, 0, caractersLlegits);
                    if (str.contains("<"+etiqueta+" ")){
                        String export = str.substring(str.indexOf("<"+etiqueta));
                        System.out.print(export+"\n");
                    }


                //System.out.println(str);
            }
            System.out.println();
        } catch (IOException ex) {
            Logger.getLogger(TestApunts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void sendForm(String urlPage, String prop1, String prop2, String val1, String val2){
        try {
            URL url = new URL(urlPage);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(prop1+"="+val1"&"+prop2+"="+val2);
            out.close();
            InputStream in = con.getInputStream();
            System.out.println(con.getURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
            //TestApunts.printContentWithSpecificEtiqueta(new URL(args[0]),args[1]);
            sendForm("https://docs.google.com/forms/d/e/1FAIpQLSdV5QvhChK0fBpAMo5pN7sIvktqwHGu1vdoWJFvBguCeMvYUw/formResponse","entry.835030737","entry.1616686619","JoelProva","No");
    }
}
