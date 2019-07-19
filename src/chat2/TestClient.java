package chat2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class TestClient {

	public static void main(String[] args) {
		// 创建客户端套接字
		Socket client=null;
		try {
			client=new Socket("localhost", 50000);
			
			SocketAddress address=client.getRemoteSocketAddress();
			
			System.out.println(address.toString());
			
			//客户端先打招呼
			try(PrintWriter pw=new PrintWriter(client.getOutputStream(),true);
					BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));)
			{
				System.out.println("client:"+"在吗？");
				/*pw.write("在吗？");
				pw.flush();*/
				pw.println("在吗？");
				
			String message=br.readLine();
				System.out.println("server:"+message);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			//client.getOutputStream();
		} catch (Exception e) {
e.printStackTrace();
		}
	}

}
