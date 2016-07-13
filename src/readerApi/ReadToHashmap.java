/**
 * 
 */
package readerApi;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author John Viegas
 *
 */
public class ReadToHashmap {
	public static   Map<String, String> map = new HashMap<String, String>();
	public static ArrayList<String> wordList = new ArrayList();

	public static Map getHasMap()
	{
		return map;
	}
	
	public ReadToHashmap() {
        

		try{
	        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\John Viegas\\workspace\\QuizGui\\src\\readerApi\\example.tab"));
	        String line = "";
	        while ((line = in.readLine()) != null) {
	            String parts[] = line.split(":");
	            map.put(parts[0], parts[1]);
	            wordList.add(parts[0]);
	        }		
	        in.close();	
		}
		catch(Exception e)
		{
			System.out.println("Erro " +e.getMessage());
		}      
		
  	
    	
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());

        }
        
/*        findMeaning word = new findMeaning();
        String inputWord;
        String outputWord;
        System.out.println("Enter rge word to be searched  " );
        inputWord = word.getTheWord();
        outputWord =word.getFromDictionary(inputWord);
        System.out.println("Thge meaning is  " +outputWord); */
    }
}