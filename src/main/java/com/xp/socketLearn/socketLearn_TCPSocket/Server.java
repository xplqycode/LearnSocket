package com.xp.socketLearn.socketLearn_TCPSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP的Socket通信
 * 服务器端：
 * 创建ServerSocket对象，绑定监听端口。
 * 通过accept()方法监听客户端请求。
 * 连接建立后，通过输入流读取客户端发送的请求信息。
 * 通过输出流向客户端发送响应信息。
 * 关闭相关资源。
 */
public class Server {
	public static void main(String[] args) {
		try {
			//1.创建一个服务端Socket，指定绑定端口
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = null;

			int count = 0;
			System.out.println("***服务器即将启动，等待客户端连接***");
			//循环监听，使用多线程
			while(true){
				//使用accept()方法监听，等待客户端连接
				socket = serverSocket.accept();
				//创建一个线程
				ServerThread serverThread = new ServerThread(socket);
				//启动线程，开始通信
				serverThread.start();
				
				count++;//连接了多少个客户端
				System.out.println("客户端数量： "+count);
				//获取客户端的信息
				InetAddress address = socket.getInetAddress();
				System.out.println("客户端IP地址："+address.getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
