package com.xp.socketLearn.socketLearn_TCPSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * 服务器线程处理类
 */
public class ServerThread extends Thread {
	// 和线程相关的Socket
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	//线程执行的操作，响应客户端请求
	public void run(){
		InputStream is = null;//字节流
		InputStreamReader isr = null;//字符流
		BufferedReader br = null;//缓冲区
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			//获取输入流，读取客户端信息
			is = socket.getInputStream();
			//转换为字符流
			isr = new InputStreamReader(is);
			//缓冲区
			br = new BufferedReader(isr);
			String info = null;
			while((info = br.readLine()) != null){//循环读取客户端的信息
				System.out.println("客户端发送："+info);
			}
			socket.shutdownInput();//socket停止发送
			//服务器响应
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("欢迎！");
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				if(pw!=null)
					pw.close();
				if(os!=null)
					os.close();
				if(br!=null)
					br.close();
				if(isr!=null)
					isr.close();
				if(is!=null)
					is.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
