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

public class IpMapper2 extends MapReduceBase implements Mapper<LongWritable, Text,IntWritable,Text>
{
  private static final Pattern ipPattern = Pattern.compile("^([\\d\\.]+)\\s([\\d]+)"); // regex reads IP and Count
  private static final IntWritable one = new IntWritable(1);
  
  public void map(LongWritable fileOffset, Text lineContents,
      OutputCollector<IntWritable,Text> output, Reporter reporter)
      throws IOException {
		  
    Matcher matcher = ipPattern.matcher(lineContents.toString());
    if(matcher.find())
    {
      String ip = matcher.group(1);
      String n = matcher.group(2);
      int k = Integer.parseInt(n);
      k*=-1;
      IntWritable num1 = new IntWritable(k);
      output.collect(num1, new Text(ip));
    }
	
}
}