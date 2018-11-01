package com.asd.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len = is.read(buffer);
        System.out.println(new String(buffer, 0,buffer.length));
        OutputStream os = socket.getOutputStream();
        os.write("你好".getBytes());
        socket.close();
        serverSocket.close();
    }
}
