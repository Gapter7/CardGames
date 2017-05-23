import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


/**
 * Created by bwgarret on 5/23/2017.
 */
public class Server {
    public static final int PORT = 2017;
    private static HashSet<String> clientNames = new HashSet<>();
    private static HashSet<PrintWriter> clientWriters = new HashSet<>();

    private static class PlayerHandler extends Thread {
        private Socket socket;
        private String name;
        private BufferedReader in;
        private PrintWriter out;

        public PlayerHandler(Socket socket){
            this.socket = socket;
        }

        public void run(){
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true); //Output stream with automatic flushing
                while (true){
                    out.println("Enter Desired Player Name: ");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (clientNames) {
                        if (!clientNames.contains(name)) {
                            clientNames.add(name);
                            break;
                        }
                    }
                }
            } catch (Exception IOException){
                IOException.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        try {
            ServerSocket listener = new ServerSocket(PORT);
            while (true) {
                new PlayerHandler(listener.accept()).start();
            }
        } catch (Exception IOException) {
            System.out.println("Can't open on PORT: " + PORT);
        } finally{

        }
    }
}
