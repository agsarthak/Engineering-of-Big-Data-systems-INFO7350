package Cloud.ApacheLog;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MRunner {

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception
  {
		JobConf conf = new JobConf(MRunner.class);
        conf.setJobName("Matrix_Multiplication");
        
        conf.setMapperClass(MMapper.class);
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(Text.class);
        
        conf.setReducerClass(MReducer.class);
		
        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
		///////////////////////////////////////////////////////////////////////////
		/**
		Configuration conf = new Configuration();
		conf.set("dimension", "5"); // set the matrix dimension here.
		Job job = Job.getInstance(conf);
		JobConf conf = new JobConf(Runner.class);
		
		FileSystem fs = FileSystem.get(conf);
			
		
		job.setJarByClass(MRunner.class);
		
		// Need to set this since, map out is different from reduce out
		job.setMapOutputKeyClass(Text.class); 
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
				
		job.setMapperClass(MMapper.class);
		job.setReducerClass(MReducer.class);
				
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
				
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		
		// Set the dimension of matrix 
		
		
				
		FileInputFormat.addInputPath(job, input);
		
		FileOutputFormat.setOutputPath(job, output);
				
		job.waitForCompletion(true);
				
		System.out.println("Matrix Multiplication Completed !");
		**/
		
	}

}