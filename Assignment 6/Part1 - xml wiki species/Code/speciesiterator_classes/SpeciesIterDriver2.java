// 
// Author - Jack Hebert (jhebert@cs.washington.edu) 
// Copyright 2007 
// Distributed under GPLv3 
// 
// Modified - Dino Konstantopoulos
// Distributed under the "If it works, remolded by Dino Konstantopoulos, 
// otherwise no idea who did! And by the way, you're free to do whatever 
// you want to with it" dinolicense
// 
package Iterator;

import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapred.JobClient; 
import org.apache.hadoop.mapred.JobConf; 
import org.apache.hadoop.mapred.Mapper; 
import org.apache.hadoop.mapred.Reducer; 
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;


public class SpeciesIterDriver2 { 

	@SuppressWarnings("deprecation")
	public static void main(String[] args) { 

		int iterationCount = 0;

		while(iterationCount <= 20){

			System.out.println("Running Iteration - " + iterationCount);
			JobClient client = new JobClient(); 
			JobConf conf = new JobConf(SpeciesIterDriver2.class); 
			conf.setJobName("Species Iter - " + iterationCount);
			
			// This property is set to generate 5 reducer tasks
			conf.setNumReduceTasks(5); 
			conf.setOutputKeyClass(Text.class); 
			conf.setOutputValueClass(Text.class); 
		
			//output-iterator-0 contains the input data
			FileInputFormat.setInputPaths(conf, new Path("output-iterator-" + iterationCount));
			iterationCount++;
			FileOutputFormat.setOutputPath(conf, new Path("output-iterator-" + iterationCount));

			conf.setMapperClass(SpeciesIterMapper2.class); 
			conf.setReducerClass(SpeciesIterReducer2.class); 
			conf.setCombinerClass(SpeciesIterReducer2.class); 

			client.setConf(conf); 
			try { 
				JobClient.runJob(conf); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
		}

	} 
} 
