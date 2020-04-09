package com.xp.socketLearn.socketLearn_TCPSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 客户端实现
 * 测试多分支
 * 0409Test1 多添加
 */
public class Client {
	public static void main(String[] args) {
		try {
			//1.建立socket指定ip & 端口号
			Socket socket = new Socket("localhost", 8888);
			//2.客户端输出流
			OutputStream os = socket.getOutputStream();//字节输出流
			PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
			pw.write("用户名：xxxx 密码：123456");
			pw.flush();
			socket.shutdownOutput();//关闭输出流
			//3.客户端接受服务器响应
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info = br.readLine()) != null){
				System.out.println("服务器说："+info);
			}
			//关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
