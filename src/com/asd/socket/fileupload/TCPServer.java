package com.asd.socket.fileupload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer {
    /*
    * 1. create ServerSocket and bind the port
    * 2. accept the client to get the scoket of client
    * 3. use socket of client get the web InputStream
    * 4. get local output stream
    * 5. write from web inputstream to local output stream
    * 6. get the web output stream
    * 7. write back the result the server side
    * 8. close the server socket and socket and the local output stream
    *
    * */
    public static void main(String[] args) throws IOException {
        //1. create ServerSocket and bind the port
        ServerSocket server= new ServerSocket(9999);


        while(true) {
            //2. accept the client to get the scoket of client
            Socket socket =  server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        //3. use socket of client get the web InputStream
                        InputStream is = socket.getInputStream();

                        //4. get local output stream
                        File file = new File("/Users/panlinlin/Desktop/SocketProgramming/src/download");

                        if(!file.exists()){
                            file.mkdir();
                        }
                        String filename = "/" + System.currentTimeMillis() + new Random().nextInt(9999) + "download2.jpg";
                        FileOutputStream fos = new FileOutputStream(file + filename);

                        //5. write from web inputstream to local output stream
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while((len = is.read(buffer) )!= -1){
                            fos.write(buffer, 0, len);
                        }
                        //6. get the web output stream
                        OutputStream os = socket.getOutputStream();

                        //7. write back the result the server side
                        os.write("成功上传".getBytes());

                        fos.close();
                        socket.close();
                    }catch (IOException e){
                        System.out.println(e.getStackTrace());
                    }finally {

                    }
                }
            }).start();

        }

       // if while true continuesly accept the client, so this statement should be commented server.close();
    }
}
