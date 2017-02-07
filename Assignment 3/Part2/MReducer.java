package Cloud.ApacheLog;

import java.io.IOException;
import java.util.*;
import java.lang.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class MReducer extends MapReduceBase implements Reducer<Text, Text, Text, IntWritable> 
{

	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, IntWritable> output, Reporter reporter)
						throws IOException 
	{
		int dim = 5;
		
		int[] row = new int[dim]; // hard coding as 5 X 5 matrix
		int[] col = new int[dim];
		
		while(values.hasNext())
		{
			//if(values.get(i).equals(Text val) )
			String[] entries = values.next().toString().split(",");
			if(entries[0].matches("a"))
			{
				int index = Integer.parseInt(entries[2].trim());
				row[index] = Integer.parseInt(entries[3].trim());
			}
			if(entries[0].matches("b"))
			{
				int index = Integer.parseInt(entries[1].trim());
				col[index] = Integer.parseInt(entries[3].trim());
			}
		}
		
		// Let us do matrix multiplication now..
		int total = 0;
		for(int i = 0 ; i < 5; i++)
		{
			total += row[i]*col[i];
		}
		
		output.collect(key, new IntWritable(total));
	
	}
	
}