/**
 * @author : Nithin Bharathi 22-Jun-2023
 */
import java.util.*;
public class NgramGenerator {
	
	private static final ArrayList<String> text = readData();
	private static final int order = 3,count = 10;
	private static HashMap<String,ArrayList<String>>possibilities = new HashMap<>();
	private static ArrayList<String> beginningPossibilities = new ArrayList<>();
	public static void main(String[] args) {
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

		System.out.println(possibilities);
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
	
	private static ArrayList<String> readData(){
		String str[] = "abcd the their of the world on more and themost of allthere is to make sureof the animotronicthe".split(" ");
		ArrayList<String> li = new ArrayList<>();
		for(String it:str)li.add(it);
		return li;
	}

}
