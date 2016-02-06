import java.util.ArrayList;


// This class stores the information for each chunk

public class Chunk {

	public int id;			//identification of the chunk in time
	public long playTS;		//time stamp begin played
	public boolean lock;
	public ArrayList<Layer> layers = new ArrayList<>();
	
	public Chunk(int seq)
	{
		id=seq;
		lock=false;
	}
	
	//has the information for each chunk
	public class Layer {
		public int id;
		public int vol;
		public int qlt;
		public long dlTS;
		
		public Layer(int i, int v, int q)
		{
			id=i; v=vol; qlt=q;
		}
	}
	
	public int qualityAssess()
	{
		int q=0;
		for(int i=0;i!=layers.size();i++)
		{
			if(layers.get(i)!=null)
			{
				q=layers.get(i).qlt;
			}
		}
		return q;
	}
	
	public int volAssess()
	{
		int vols=0;
		for(Layer e : this.layers)
		{
			vols=e.vol;
		}
		return vols;
	}
}
