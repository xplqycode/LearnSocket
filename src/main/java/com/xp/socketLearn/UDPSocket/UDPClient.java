package com.xp.socketLearn.UDPSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * 客户端
 */
public class UDPClient {
	public static void main(String[] args) throws IOException {
		/*
		 * 向服务器发
		 */
		//1.定义服务器地址、端口号、数据
		InetAddress address=InetAddress.getByName("localhost");//通过名字获取实例
		int port=8800;
		byte[] data="用户名 : admin;密码：123".getBytes();
		//2.创建数据报，包含发送的数据信息
		DatagramPacket packet=new DatagramPacket(data, data.length, address, port);
		//3.创建DatagramSocket对象
		DatagramSocket socket=new DatagramSocket();
		//4.向服务器发送数据报
		socket.send(packet);
		
		/*
		 * 接收服务器响应
		 */
		//1.创建数据包，接收服务器响应
		byte[] data2 = new byte[1024];
		DatagramPacket packet2 = new DatagramPacket(data2, data2.length);//数据保存在data2中，长度
		//2.接收响应
		socket.receive(packet2);
		//3.读取数据
		String reply=new String(data2, 0, packet2.getLength());//直接构造器转换为String
		System.out.println("我是客户端，服务器说"+reply);
		//4.关闭
		socket.close();
	}
}
