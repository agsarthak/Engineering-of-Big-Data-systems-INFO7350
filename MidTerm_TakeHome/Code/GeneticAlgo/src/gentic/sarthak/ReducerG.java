package gentic.sarthak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class ReducerG extends Reducer<Text, ArrayWritable, Text, Text> {
	
	int poolSize = 40;
	int gen=0;
	
	protected void reduce(Text key, ArrayWritable values, Context context) 
			throws IOException, InterruptedException {
		
			Writable[] writables = values.get();
			Writable chromo = writables[0];
			Writable fitnessScore = writables[1];
		
		ArrayList pool = new ArrayList(poolSize);
		ArrayList newPool = new ArrayList(pool.size());
		
	
		while(true) {
			// Clear the new pool
			newPool.clear();
			
			// Add to the generations
			gen++;
			
			// Loop until the pool has been processed
			for(int x= pool.size()-1; x>=0; x-=2) {
				// Select two members
				Chromosome n1 = selectMember(pool);
				Chromosome n2 = selectMember(pool);
				
				// Cross over and mutate
				n1.crossOver(n2);
				n1.mutate();
				n2.mutate();
				
				// Rescore the nodes
			//	n1.scoreChromo(target);
			//	n2.scoreChromo(target);
				
				// Check to see if either is the solution
				//	if (n1.total == target && n1.isValid()) { System.out.println("Generations: " + gen + "  Solution: " + n1.decodeChromo()); return; }
				//	if (n2.total == target && n2.isValid()) { System.out.println("Generations: " + gen + "  Solution: " + n2.decodeChromo()); return; }
				
				// Add to the new pool
				newPool.add(n1);
				newPool.add(n2);
			}
			
			// Add the newPool back to the old pool
			pool.addAll(newPool);
		}
		
		//context.write();
		
	}
	
	public Chromosome selectMember(ArrayList l) { 

		Random rand = new Random();
		// Get the total fitness		
		double tot=0.0;
		for (int x=((ArrayList) l).size()-1;x>=0;x--) {
			//double score = ((Chromosome)l.get(x)).score;
			//tot+=score;
		}
		double slice = tot*rand.nextDouble();
		
		// Loop to find the node
		double ttot=0.0;
		for (int x=l.size()-1;x>=0;x--) {
			Chromosome node = (Chromosome)l.get(x);
			//ttot+=node.score;
			if (ttot>=slice) { l.remove(x); return node; }
		}
		
		return (Chromosome)l.remove(l.size()-1);
	}
	
	
}