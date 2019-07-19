package chat3.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.handler.MessageContext.Scope;

public class MyServer {
	

	/**
	 * 
	 * <p>Title: main</p>  
	 * <p>
	 * 	Description: 
	 * 需求分析：1. 服务端能够响应客户端的连接请求，不管多少个都可以响应
	 * 			2.客户端发送过来的信息，服务端可以转发给其他客户端
	 * </p>  
	 * @param args
	 */
	
	//存放客户端套接字的集合,为什么给集合上锁，支持并发访问，确保数据安全
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
	
	
	
	public static void main(String[] args) {
		//开一个服务端
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(20000);
			
			while(true) {
				Socket client = server.accept();
				//拿到客户端套接字后需要做两件事：
				//1.把这个套接字放入集合中
				socketList.add(client);
				
				//2.开一个线程跟客户聊天，
				//其实不聊天，只是不停的接收客户端发
				//来的信息然后转发至其他客户端
				new Thread(new MyServer_chat_thread(client)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
