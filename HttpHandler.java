
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 

public class HttpHandler extends Thread {

	public String server;
	public int BUFFER_SIZE;
	
	public HttpHandler(String s)
	{
		server=s;
		BUFFER_SIZE=4096;
	}
	
	public int download(String f)
	{
		//TODO I need to make sure that only HTTP connection is used
		try {
			String address= server+f;
			URL url = new URL (address);
			HttpURLConnection httpcon= (HttpURLConnection) url.openConnection();
			int rescode=httpcon.getResponseCode();
			if(rescode==HttpURLConnection.HTTP_OK)
			{
				InputStream inputStream = httpcon.getInputStream();
				int bytesRead = -1;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            FileOutputStream outputStream = new FileOutputStream("temp\\"+f);
	            long t= System.currentTimeMillis();
	            int totbyte = 0;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	                totbyte+=bytesRead;
	            }
	            t=System.currentTimeMillis()-t;
	            outputStream.close();
	            inputStream.close();
	            int rate= (int)((totbyte*8)/(t/1000));
	        	return rate;
			}
			
			else
			{
				System.out.println("Unsuccessful conneciton to server");
				return -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
