package chat3.client;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

	public static void main(String[] args) {

		/**
		 * 需求：至少两个线程，
		 * 一 个发 信息
		 * 一个收信息
		 */
		Socket client = null;
		
		try {
			//此处的127.0.0.1相当于localhost
			client = new Socket("127.0.0.1",20000);
			
			
			//记得开一个子线程，不停的收取服务端转发过来的信息
			new Thread(new MyClient_receivemess_thread(client)).start();
			
			
			//希望能够群聊，我能发信息出去
			try(
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
					
					){
				while(true) {
					//控制台输入要发送的信息
					Scanner s = new Scanner(System.in);
					String message = s.next();
					pw.println(message);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
