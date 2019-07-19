package chat3.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
* <p>Title: MyClient_receivemess_thread</p>  
* <p>
*	Description: 
*	收信息的线程
* </p> 
* @author xianxian 
* @date 2019年7月19日
 */
public class MyClient_receivemess_thread implements Runnable {

	private Socket client;
	
	public MyClient_receivemess_thread(Socket client) {
		super();
		this.client = client;
	}
	@Override
	public void run() {
		try(
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				){
			while (true) {
				String message = br.readLine();
				System.out.println(message);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
