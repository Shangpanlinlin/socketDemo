package com.asd.socket.broswer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BSTCPServer {
    public static void main(String[] args) throws IOException {
        /*
        *  监听8080，端口，如果有请求进来，我们可以获取很多数据
        *
        * */
        ServerSocket server = new ServerSocket(8080);
        while(true){
              Socket socket = server.accept();
              InputStream is = socket.getInputStream();
              //return the resource that request by the browser client
              //1.get the resource  uri
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String uri = br.readLine();
            System.out.println(uri);
                        uri = uri.split(" ")[1].substring(1);

              //2.create local output stream,
                File file = new File(uri);
                FileInputStream fis = new FileInputStream(file);
                int len = 0;
                byte[] buffer = new byte[1024];
                OutputStream os = socket.getOutputStream();
                //write the http response header, this two lines are fixed information
                os.write("HTTP/1.1 200 OK\r\n".getBytes());
                os.write("Content-Type:text/html\r\n".getBytes());
                //must write a blank line , to make sure that this content will be parse by client browser.
                os.write("\r\n".getBytes());
                while( (len = fis.read(buffer)) != -1){
                    os.write(buffer,0,len);
                }
                fis.close();
                socket.close();
        }
        ///Users/panlinlin/Desktop/SocketProgramming/index.html


      /*  File file = new File("web/index.html");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));*/
    }
}
