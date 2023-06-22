/**
 * @author : Nithin Bharathi 22-Jun-2023
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
public class NgramGenerator{
	
	private static ArrayList<String> text;
	private static final int order = 3,count = 10;
	private static HashMap<String,ArrayList<String>>possibilities = new HashMap<>();
	private static ArrayList<String> beginningPossibilities = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		text = readData();
		for(String line:text){
			int len = line.length();
			for(int i=0;i+order<len;i++){
				String ngram = line.substring(i,i+order);
				if(i == 0){
					beginningPossibilities.add(ngram);
				}
				ArrayList<String>possibility = possibilities.getOrDefault(ngram, new ArrayList<String>());
				possibility.add(line.charAt(i+order)+"");
				possibilities.put(ngram,possibility);
			}
		}
		
		predict();
	}
	
	private static void predict(){
		String curGram = getRandomEntry(beginningPossibilities);
		String prediction = curGram;
		for (int itr = 0;itr<count;itr++){
			ArrayList<String>possibility = possibilities.getOrDefault(curGram,null);
			if(possibility == null)break;
			String random = getRandomEntry(possibility);
			prediction += random;
			int predictionSize = prediction.length();		
			curGram = prediction.substring(predictionSize-order,predictionSize);
			
		}
		System.out.println(prediction);

	}
	
	private static String getRandomEntry(ArrayList<String>possibility){
		int size = possibility.size();
		return possibility.stream().skip((int)(size*Math.random())).findFirst().map(Object::toString).orElse(null);
	}
	
	private static ArrayList<String> readData() throws Exception{
		File file = new File("/Users/nithinbharathi/Desktop/Names.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		ArrayList<String> lines = new ArrayList<>();
		while((line = reader.readLine()) != null){
			lines.add(line);
		}
		
		return lines;
	}

}
