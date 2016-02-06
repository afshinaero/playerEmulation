import java.util.ArrayList;

public class Buffer {
	
	public ArrayList<Chunk> buffer= new ArrayList<>();
	public int bufLen;	//in chunks
	public int bufLenSec;	//in seconds
	
	public Buffer()
	{
		bufLen=0;	//in chunks
		bufLenSec=0;	
	}
}
