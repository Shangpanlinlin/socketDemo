package com.asd.socket.fileupload;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    /*
    * 功能： 上传本地文件到服务器端，读取服务器返回的结果信息
    * 数据源：本地文件， 目的地：服务器，
    * 使用本地输入流和网络输出流上传文件，网络输入流读取结果
    *
    * 1.创建客户端的Socket对象，指定服务器地址和端口。（创建Socket的时候就会和服务器三次握手建立链接）
    * 2.本地输入流读取文件内容
    * 3.获取网络输出流，
    * 4.本地输入流将数据传输到网络输出流
    * 5.获取网络初入流，读取服务器反馈结果
    * 6.关闭Socket 本地输入流
    * */
    public static void main(String[] args) throws IOException {
        //1.创建客户端的Socket对象，指定服务器地址和端口。（创建Socket的时候就会和服务器三次握手建立链接）
        Socket client = new Socket("127.0.0.1" , 9999);

        //2.本地输入流读取文件内容
        FileInputStream fis = new FileInputStream("/Users/panlinlin/Desktop/SocketProgramming/src/1.jpg");

        //3.获取网络输出流，
        OutputStream os = client.getOutputStream();

        //4.本地输入流将数据传输到网络输出流
        int len = 0;
        byte[] buffer = new byte[1024];
        while(( len = fis.read(buffer)) != -1){
            os.write(buffer, 0 ,len);
        }

        client.shutdownOutput();

        //5.获取网络初入流，读取服务器反馈结果
        InputStream is = client.getInputStream();
        len = is.read(buffer);
        System.out.println(new String(buffer, 0, len));

        //6.关闭Socket 本地输入流
        client.close();
        fis.close();
    }

}
