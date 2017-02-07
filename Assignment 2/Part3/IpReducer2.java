package Cloud.ApacheLog;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class IpReducer2 extends MapReduceBase implements Reducer<IntWritable, Text, Text, IntWritable> 
{
	public void reduce(IntWritable counts, Iterator<Text> ip, OutputCollector<Text, IntWritable> output, Reporter reporter)
      throws IOException 
	  {
		String ipadd = null;
		Text txt = new Text();
    
		while (ip.hasNext())    
			{
				ipadd =  ip.next().toString();
				break;
			}
        txt.set(ipadd);
        String c = counts.toString();
        int cccc = (Integer.parseInt(c));
        cccc*=-1; // negative count
        counts.set(cccc);
        output.collect(txt, counts);	
	  }
}