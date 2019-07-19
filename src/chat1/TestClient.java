package chat1;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class TestClient {

	public static void main(String[] args) {
		// 创建客户端套接字
		try {
			Socket client=new Socket("127.0.0.1", 5791);
			System.out.println("hello");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
