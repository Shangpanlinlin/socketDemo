package com.asd.socket;

import sun.security.ssl.SSLSocketImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        //1 create Socket, specified the server ip and port
        Socket socket = new Socket("127.0.0.1", 8888);
        //2 get the outputstream
        OutputStream os = socket.getOutputStream();
        os.write("服务器你好".getBytes());

        //get the input stream
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len = is.read(buffer);
        //close the socket, release the resource
        System.out.println(new String(buffer, 0, len));
        socket.close();

    }
}
