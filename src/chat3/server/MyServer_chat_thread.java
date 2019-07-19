package chat3.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
* <p>Title: MyServer_chat_thread</p>  
* <p>
*	Description: 
*	服务端和客户端聊天的线程类
*	需求：一直不停地接收客户端发来的信息然后转发至其他客户端
* </p> 
* @author xianxian 
* @date 2019年7月19日
 */
public class MyServer_chat_thread implements Runnable {

	private Socket client;//能够接收客端发送的信息，必定持有该客户端套接字
	
	public MyServer_chat_thread(Socket socket) {
		this.client = socket;//利用构造方法构造当前类的实例的同时，给套接字属性赋值
	}
	@Override
	public void run() {
		//一直不停地接收客户端发来的信息然后转发至其他客户端
		try(
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				){
			String message = null;
			while(true) {
				message = br.readLine();//接收客户端发送的信息
				//转发
				for(Socket socket:MyServer.socketList) {
					//如果是自己发的信息，不用转发回自己
					if(socket == client) {
					
						continue;
					}else {
						//拿到每个客户端的套接字做什么呢？获取每个客户端的输出流，给各自的客户端发送信息
						//后面的参数true表明启动自动刷新功能
						PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
						
						pw.println(message);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
