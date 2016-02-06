import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Player extends Thread {
	
	public int currSeg;
	public static Buffer buf = new Buffer();
	public static Configs cfg = new Configs();
	public static Algorithm alg; 
	public boolean playing= false;	//shows the current state of player
	public Semaphore sem = new Semaphore(0);	//this sempahore holds until the available buffer length is adequate
	
	public Player()
	{
		currSeg=0;
	}
	
	//emulates the playback
	public void play()
	{
		while(buf.bufLen>0 && currSeg<cfg.videoLen)
		{
			playing = true;
			try {
				buf.buffer.get(currSeg).lock=true;
				buf.buffer.get(currSeg).playTS=System.currentTimeMillis();
				currSeg++;
				for(int i=0;i!=cfg.chkLen;i++)
				{
					buf.bufLenSec-=1;
					sleep(1000);
				}
				buf.bufLen-=1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(currSeg<cfg.videoLen)
			System.out.println("Rebuffer");
	}
	
	//main thread
	public void run()
	{
		cfg.readConFile();
		alg= new Algorithm(this);
		alg.start();
		
		while(currSeg<cfg.videoLen)	//until end of chunks continue playback
		{
			playing = false;
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Start the playing");
			play();
		}
		System.out.println("Finished Playing");
	}
}
