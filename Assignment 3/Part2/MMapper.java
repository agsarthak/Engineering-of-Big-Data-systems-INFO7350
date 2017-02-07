package Cloud.ApacheLog;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>
{
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
						throws IOException
	{
		String line = value.toString();
		String[] entry = line.split(",");
		String sKey = "";
		String mat = entry[0].trim();
		
		String row, col;
		
		int dim = 5;
		
		//while(mat.matches())

		if(mat.matches("a"))
		{
			for (int i =0; i < dim ; i++) // hard coding matrix size 5 
			{
				row = entry[1].trim(); // rowid
				sKey = row+i;
				System.out.println(sKey + "-" + value.toString());
				output.collect(new Text(sKey), value);
			}
		}

		if(mat.matches("b"))
		{
			for (int i =0; i < dim ; i++)
			{
				col = entry[2].trim(); // colid
				sKey = i+col;
				System.out.println(sKey + "-" + value.toString());
				output.collect(new Text(sKey), value);
			}
		}
		
	}
	

}