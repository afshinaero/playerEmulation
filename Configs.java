import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

// Maintains the configuration of the Streaming session and global variables
public class Configs {
	public int chkLen;	//Length of each chunk
	public int noBQlts;	//Number of Base qualities
	public int noLayers;	//Number of Enhancement layers
	public int plyDly;
	public int videoLen;	//in segment
	public String server;   //server address
	public ArrayList<ArrayList<Integer>> rateDist = new ArrayList<ArrayList<Integer>>(); //holds the quality and bitrate information for available qualities
	
	
	public void readConFile()
	{
		try {
			BufferedReader reader= new BufferedReader(new FileReader("Config.txt"));
			String s;
			s=reader.readLine();
			while(s!=null)
			{
				if(s.startsWith("ChunkLen"))
				{
					chkLen = Integer.parseInt(s.split("\\s+")[1]);
				}
				else if(s.startsWith("NumOfBaseQlts"))
				{
					noBQlts = Integer.parseInt(s.split("\\s+")[1]);
				}
				else if(s.startsWith("NumOfLayers"))
				{
					noLayers = Integer.parseInt(s.split("\\s+")[1]);
				}
				else if(s.startsWith("PlayoutDelay"))
				{
					plyDly = Integer.parseInt(s.split("\\s+")[1]);
				}
				else if(s.startsWith("VideoLen"))
				{
					videoLen = Integer.parseInt(s.split("\\s+")[1]);
				}
				else if(s.startsWith("Server"))
				{
					server = s.split("\\s+")[1];
				}
				else
				{
					if(s.contains("."))
					{
						int layeridx = Integer.parseInt(s.split("\\.")[0]);
						int baseidx=Integer.parseInt(s.split("\\.")[1].split("\\s+")[0]);
						rateDist.get(baseidx).add(layeridx, Integer.parseInt(s.split("\\s+")[1]));
					}
					else
					{
						int idx=Integer.parseInt(s.split("\\s+")[0]);
						rateDist.add(idx, new ArrayList<>());
						rateDist.get(idx).add(0, Integer.parseInt(s.split("\\s+")[1]));
					}
				}
 				s=reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
