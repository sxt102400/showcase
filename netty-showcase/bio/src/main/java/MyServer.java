import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private static int PORT = 8800;


    public static String processRequest(String inStr) {
        return "";
    }

    public static void main(String[] args) {


        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
            Socket so = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(so.getInputStream()));
            PrintWriter pw = new PrintWriter(so.getOutputStream(), true);

            String inStr, outStr;

            while ((inStr = in.readLine()) != null) {
                if ("Done".equals(inStr)) {
                    break;
                }
                outStr = processRequest(inStr);
                pw.println(outStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
