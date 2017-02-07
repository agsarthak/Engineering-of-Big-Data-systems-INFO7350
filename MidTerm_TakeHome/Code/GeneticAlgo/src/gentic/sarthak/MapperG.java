package gentic.sarthak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperG extends Mapper<Text, Text, Text, ArrayWritable> {

	public char[] ltable = {'0','1','2','3','4','5','6','7','8','9','+','-','*','/'};
	public int total;
	public double score;
	public int chromoLen = 5;
	int target = 4;
	public StringBuffer decodeChromo = new StringBuffer(chromoLen * 4);
	
	protected void map(Text key, Text value, Context context)
			throws IOException, InterruptedException {
		
		Double fitnessScore = scoreChromo(target, key);
		
		ArrayWritable arrayWritable = new ArrayWritable(Text.class);

		Text [] textValues = new Text[2];
		textValues[0] = new Text(key);
		textValues[1] = new Text(fitnessScore.toString());

		arrayWritable.set(textValues );
		context.write(key , arrayWritable );
		}
	
	public final Double scoreChromo(int target, Text chromo) 
	{
		total = addUp(chromo);
		if (total == target) 
			score = 0;
		else 
			score = (double)1 / (target - total);
		return score;
	}
	// Add up the contents of the decoded chromo
		public final int addUp(Text chromo) { 
		
			// Decode our chromo
			String decodedString = decodeChromo(chromo);
			
			// Total
			int tot = 0;
			
			// Find the first number
			int ptr = 0;
			while (ptr<decodedString.length()) { 
				char ch = decodedString.charAt(ptr);
				if (Character.isDigit(ch)) {
					tot=ch-'0';
					ptr++;
					break;
				} else {
					ptr++;
				}
			}
			
			// If no numbers found, return
			if (ptr==decodedString.length()) return 0;
			
			// Loop processing the rest
			boolean num = false;
			char oper=' ';
			while (ptr<decodedString.length()) {
				// Get the character
				char ch = decodedString.charAt(ptr);
				
				// Is it what we expect, if not - skip
				if (num && !Character.isDigit(ch)) {ptr++;continue;}
				if (!num && Character.isDigit(ch)) {ptr++;continue;}
			
				// Is it a number
				if (num) { 
					switch (oper) {
						case '+' : { tot+=(ch-'0'); break; }
						case '-' : { tot-=(ch-'0'); break; }
						case '*' : { tot*=(ch-'0'); break; }
						case '/' : { if (ch!='0') tot/=(ch-'0'); break; }
					}
				} else {
					oper = ch;
				}			
				
				// Go to next character
				ptr++;
				num=!num;
			}
			
			return tot;
		}
		
		// Decode the string
		public final String decodeChromo(Text chromo) {	

			// Create a buffer
			decodeChromo.setLength(0);
			
			// Loop throught the chromo
			for (int x=0; x<chromo.getLength(); x+=4) {
				// Get the
				int idx = Integer.parseInt(chromo.toString().substring(x,x+4), 2);
				if (idx<ltable.length) decodeChromo.append(ltable[idx]);
			}
			
			// Return the string
			return decodeChromo.toString();
		}
	
	
}
