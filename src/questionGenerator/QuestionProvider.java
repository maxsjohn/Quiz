package questionGenerator;

import java.util.ArrayList;
import java.util.Random;

import readerApi.ReadToHashmap;

public class QuestionProvider {

	public static int correctOption ;
	private int m_correctValue;
	private ArrayList<Integer>  myAskedQuestions;
	
	public QuestionProvider()
	{
		myAskedQuestions = new ArrayList();
	}
	
	int generateIndex()
	{
		Random randomNum = new Random();
		return randomNum.nextInt(ReadToHashmap.map.size());
	}
	
	//Add exception here to tell questions are Over
	public String [] GetQuestionsAndOptions()
	{
		String [] QuestionsAndOptions = new String[5] ;
		int[] options = new int[4];
		
		if(myAskedQuestions.size() == ReadToHashmap.wordList.size())
			return null;
	
		int wordNumber;
		do
		{
			wordNumber = generateIndex();
		}while(myAskedQuestions.contains(wordNumber));
		
		
		myAskedQuestions.add(wordNumber);
		
		QuestionsAndOptions[0] = ReadToHashmap.wordList.get(wordNumber);
		
		for(int i =0 ; i<4; i++)
			options[i] =-1;
			
		Random randomCorrect = new Random();
	correctOption = randomCorrect.nextInt(4);
	options[correctOption] = wordNumber ;
	m_correctValue = correctOption ;
	for(int i =0 ; i<4; i++)
	{
		if(options[i] != -1)
			continue;
		int optionNumber ;
		do{
			optionNumber =generateIndex();
		}while(optionNumber == options[0] || optionNumber ==options[1] || optionNumber ==options[2] || optionNumber == options[3]);
		options[i] = optionNumber;
	}
	
	for(int i =0 ; i<4; i++)
	{
		String wordRandomized =ReadToHashmap.wordList.get(options[i]) ;
		QuestionsAndOptions[i+1] 	= ReadToHashmap.map.get(wordRandomized);	
	}
		
	
		return QuestionsAndOptions;
		
	}

	private int generateRandomNumberExceptOne(int correctOption2) {
		// TODO Auto-generated method stub
		int i;
		do
		{
			i = generateIndex();
		}while(i == correctOption2);
		return i;
	}
	
	public int GetCorrectValue()
	{
		return m_correctValue;
	}

}
