import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlainSockets {

    private static final Logger LOG = Logger.getLogger(PlainSockets.class.getName());

    public static final String HTML = "<html><head>Hello from plain socket</head><body>Hi Hi<body></html>\r\n";
    public static final String[] RSP_LINES = new String[]{
            "HTTP/1.1 200 OK\r\n",
            "Content-Type: text/html;\r\n",
            "\r\n",
            HTML
    };

    public static void main(String[] args) {

        int port = 8080;

        try {
            ServerSocket srvSocket = new ServerSocket(port);

            while(!Thread.currentThread().isInterrupted()) {
                LOG.info("Listening for connection on port " + port);
                Socket clientSocket = srvSocket.accept();

                OutputStream outputStream = clientSocket.getOutputStream();
                InputStream inputStream = clientSocket.getInputStream();
                PrintWriter out = new PrintWriter(outputStream, true);
                int dataSize = inputStream.available();
                byte[] data = new byte[dataSize];
                inputStream.read(data);
                //List<String> strings = IOUtils.readLines(inputStream);
                //strings.forEach(System.out::println);

                for(String line : RSP_LINES){
                    out.write(line);
                }

                inputStream.close();
                outputStream.close();

            }

        }
        catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
        }

        //listen for connection on port 80


    }



}
