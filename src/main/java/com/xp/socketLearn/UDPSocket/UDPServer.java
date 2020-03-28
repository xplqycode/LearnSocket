package com.xp.socketLearn.UDPSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 服务器端Socket
 */
public class UDPServer {
	public static void main(String[] args) throws IOException {
		/*
		 * 接收客户端响应
		 */
		//1.创建服务器端DatagramSocket，指定端口
		DatagramSocket socket=new DatagramSocket(8800);
		//2.创建数据报，用于接收
		byte[] data =new byte[1024];//创建字节数组，指定接收的数据包的大小
		DatagramPacket packet=new DatagramPacket(data, data.length);
		//3.接受客户端数据，保存到数据报中
		System.out.println("****服务器启动，等待客户端数据****");
		socket.receive(packet);//此方法在接收到数据之前会一直阻塞
		//4.读取客户端数据
		//转换字节数组为String
		String info=new String(data, 0, packet.getLength());
		System.out.println("我是服务器，客户端说："+info);
		
		/*
		 * 服务器返回响应
		 */
		//1.定义客户端地址端口号数据
		InetAddress address=packet.getAddress();//通过之前的数据报获取地址和端口号
		int port=packet.getPort();
		byte[] data2="welcome!".getBytes();
		//创建数据报，返回响应信息
		DatagramPacket packet2=new DatagramPacket(data2, data2.length, address, port);
		//3.发送给客户端
		socket.send(packet2);
		//4.关闭
		socket.close();
	}
}
