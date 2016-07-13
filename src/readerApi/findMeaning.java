package readerApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class findMeaning {
	public String inputWord;
	public String description ;
	findMeaning()
	{
		inputWord = "";
	}
	public String getTheWord()
	{
		
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			inputWord = br.readLine();
			
		}
		catch(IOException e)
		{
			System.out.println("Error while reading " +e.getMessage() );	
			return "";
		}
		return this.inputWord;
	}
	
	public String getFromDictionary(String key){
		
		if(inputWord.isEmpty())
		{
			return "No lattest input from user ";
			
		}
		description = (String) ReadToHashmap.getHasMap().get(inputWord);
		if(description == null)	
		{
			return "Word Doesnot exsist";
		}
		return description;
		
	}

}
