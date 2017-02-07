package gentic.sarthak;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class DriverG {

	public static void main(String[] args) throws IOException,
	InterruptedException, ClassNotFoundException {

		Configuration conf = new Configuration();
		GenericOptionsParser parser = new GenericOptionsParser(conf, args);
		args = parser.getRemainingArgs();
		Job job = new Job(conf, "genetic");
		job.setJarByClass(DriverG.class);
		
		job.setMapOutputValueClass(ArrayWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		int poolSize = 40;
		int target = 4;
		int chromoLen = 5;
		StringBuffer chromo = new StringBuffer(chromoLen * 4);
		File file = new File("/D:/GRAD_SCHOOL/Fall2016/Midterm_TakeHome/data.txt");
		file.delete();
		// Create the pool
		//ArrayList pool = new ArrayList(poolSize);
		//ArrayList newPool = new ArrayList(pool.size());
		
		// Generate unique chromosomes in the pool
		for (int x=0; x<poolSize; x++) {
			new Chromosome(target, chromo);
			if (!file.exists()) {
				file.createNewFile();
			}		
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(chromo + ",one");
			bw.append('\n');
			bw.close();
			System.out.println("All the chromosomes are written in the file.");
		}
		
		String input_file = "/D:/GRAD_SCHOOL/Fall2016/Midterm_TakeHome/data.txt";
		
		FileInputFormat.setInputPaths(job, new Path(input_file));
		FileOutputFormat.setOutputPath(job, new Path(args[0]));
		
		job.setMapperClass(MapperG.class);
		job.setReducerClass(ReducerG.class);

		System.out.println(job.waitForCompletion(true));
	}
}