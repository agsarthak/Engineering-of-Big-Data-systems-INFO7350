package Cloud.ApacheLog;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class Runner {

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception
  {
        JobConf conf = new JobConf(Runner.class);
        JobConf conf1 = new JobConf(Runner.class);
        
        // first
        conf.setJobName("ip-count");
        
        conf.setMapperClass(IpMapper.class);
        
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(IntWritable.class);
        
        conf.setReducerClass(IpReducer.class);
      
        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
       

        JobClient.runJob(conf);
      
        
        // Second
        
        conf1.setJobName("ip-count-sort");
        
        conf1.setMapperClass(IpMapper2.class);
        
        conf1.setMapOutputKeyClass(IntWritable.class);
        conf1.setMapOutputValueClass(Text.class);
        
        conf1.setReducerClass(IpReducer2.class);
                
        FileInputFormat.setInputPaths(conf1, new Path(args[1]));
        FileOutputFormat.setOutputPath(conf1, new Path(args[2]));
        
        JobClient.runJob(conf1);
	}

}
