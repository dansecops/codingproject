import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainSockets2 {


    public static void main(String[] args) throws IOException {

        int port = 8080;

        ServerSocket srvSocket = new ServerSocket(port);

        Socket socket = srvSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s;
        // this is a test code which just reads in everything the requester sends
        while ((s = in.readLine()) != null)
        {
            System.out.println(s);
            if (s != null && s.length() == 0)
            {
                break;
            }
        }
        // send the response to close the tab/window
        String response = "<html><head>Hello from plain socket</head><body>Hi Hi<body></html>\\r\\n";

        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + response.length());
        out.println();
        out.println(response);
        out.flush();
        out.close();
        socket.close();


    }
}
