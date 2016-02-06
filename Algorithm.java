
public class Algorithm extends Thread {
	
	public Player p;
	public int segCurs =0 ;	//segment cursor
	public HttpHandler httph;
	public Algorithm(Player play)
	{
		p=play;
		httph= new HttpHandler(Player.cfg.server);
	}
	
	public int download(int segid, int layerid)
	{
		String filename= Player.cfg.Filename
	}
	
	
	public void run()
	{
		int i=0;
		while(p.currSeg<Player.cfg.videoLen)	//until the player is not reached the last segment continue downloading
		{
			try {
				Chunk c = new Chunk(segCurs);
				
				int r1=httph.download("bluesky-I-1080p.seg0-L0.svc");
				
				Player.buf.buffer.add(c);
				i++;
				Player.buf.bufLen+=1;
				Player.buf.bufLenSec+=Player.cfg.chkLen;
				
				if(Player.buf.bufLen*Player.cfg.chkLen>Player.cfg.plyDly && p.playing==false)
				{
					p.sem.release();
				}
				segCurs++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
