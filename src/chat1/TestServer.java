package chat1;
import java.io.IOException;
import java.net.ServerSocket;


public class TestServer {

	public static void main(String[] args) {
		//创建服务端套接字，调用accept方法一直监听客户端的访问请求
		ServerSocket server;
		try {
			server = new ServerSocket(5791);
			server.accept();
			
			System.out.println("client connect me.....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
