package guizapp;

public class AllQuestionsDone extends Exception {
	private int m_MaxQuestion;

	public AllQuestionsDone(int numberofuestions)
	{
		super("Quiz is over ,iy had " +numberofuestions);
		m_MaxQuestion = numberofuestions;

		
	}
}
