package guizapp;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import questionGenerator.QuestionProvider;
import readerApi.ReadToHashmap;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class QuizApp {

	private JFrame frame;
	private JTextField textField;
	int userScore ;
	int userSelectedOption ;
	ReadToHashmap QuestionSet ;
	QuestionProvider QAndA ;
	private JTextField txtScore;
	private JTextField textField_1;
	private JRadioButton rdbtnNewRadioButton = new JRadioButton();	
	JRadioButton rdbtnNewRadioButton_1 = new JRadioButton();
	JRadioButton rdbtnNewRadioButton_2 = new JRadioButton();
	JRadioButton rdbtnNewRadioButton_3 = new JRadioButton();	
	int correctValue ;
	private int m_score =0 ;
	JButton btnRestart ;	
	JLabel lblAccuracy ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizApp window = new QuizApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuizApp() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		 QuestionSet = new ReadToHashmap();
		 QAndA  = new QuestionProvider();
		
		String[] firstQuestion  = null;
		try
		{
			firstQuestion = QAndA.GetQuestionsAndOptions();			
		}
		catch(AllQuestionsDone e)
		{
			e.getMessage();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		correctValue = QAndA.GetCorrectValue()+1;
		frame = new JFrame();
		frame.setBounds(100, 100, 892, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setEditable(false);
		textField.setBackground(Color.YELLOW);
		textField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		textField.setBounds(12, 13, 800, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(firstQuestion[0]);
		
		rdbtnNewRadioButton.setText(firstQuestion[1]);
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				userSelectedOption = 1;	
				rdbtnNewRadioButton_1.setSelected(false);
				rdbtnNewRadioButton_2.setSelected(false);
				rdbtnNewRadioButton_3.setSelected(false);				
				
			}
		});
		rdbtnNewRadioButton.setBounds(12, 82, 800, 25);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1.setText(firstQuestion[2]);		
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userSelectedOption = 2;
				rdbtnNewRadioButton.setSelected(false);
				rdbtnNewRadioButton_2.setSelected(false);
				rdbtnNewRadioButton_3.setSelected(false);				
			}
		});
		rdbtnNewRadioButton_1.setBounds(12, 125, 788, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2.setText(firstQuestion[3]);
		rdbtnNewRadioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userSelectedOption = 3;
				rdbtnNewRadioButton.setSelected(false);
				rdbtnNewRadioButton_1.setSelected(false);
				rdbtnNewRadioButton_3.setSelected(false);				
			}
		});		
		rdbtnNewRadioButton_2.setBounds(12, 163, 800, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3.setText(firstQuestion[4]);
		rdbtnNewRadioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userSelectedOption = 4;	
				rdbtnNewRadioButton.setSelected(false);
				rdbtnNewRadioButton_1.setSelected(false);
				rdbtnNewRadioButton_2.setSelected(false);				
			}
		});	
		rdbtnNewRadioButton_3.setBounds(12, 214, 800, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_3);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ValidateANdUpdateResult();
				ClearButton();
				updatewithNextQuestion();		
				
			}
		});
		btnSubmit.setBounds(24, 287, 97, 25);
		frame.getContentPane().add(btnSubmit);
		
		txtScore = new JTextField();
		txtScore.setEditable(true);
		txtScore.setText("Score");
		txtScore.setBounds(661, 356, 116, 22);
		frame.getContentPane().add(txtScore);
		txtScore.setColumns(10);
		
		JLabel lblLiveScore = new JLabel("Live Score");
		lblLiveScore.setBounds(592, 358, 67, 19);
		frame.getContentPane().add(lblLiveScore);
		
		 lblAccuracy = new JLabel("Accuracy");
		lblAccuracy.setBounds(600, 313, 59, 19);
		frame.getContentPane().add(lblAccuracy);
		
		textField_1 = new JTextField();
		textField_1.setText("Score");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(661, 311, 116, 22);
		frame.getContentPane().add(textField_1);
		
		btnRestart = new JButton("Restart");
		btnRestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnRestart.setVisible(false);
				 QuestionSet = new ReadToHashmap();
				 QAndA  = new QuestionProvider();
				 m_score=0;
			}
		});
		btnRestart.setBounds(24, 340, 97, 25);
		frame.getContentPane().add(btnRestart);
		btnRestart.setVisible(false);
		
	}
	public void updatewithNextQuestion()
	{
		String[] firstQuestion = null;
		try
		{
			firstQuestion = QAndA.GetQuestionsAndOptions();			
		}
		catch(AllQuestionsDone e)
		{
		    JOptionPane.showMessageDialog(null, e.toString(), "Error",
                    JOptionPane.ERROR_MESSAGE);
			btnRestart.setVisible(true);

			return ;
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		correctValue = QAndA.GetCorrectValue()+1;
		textField.setText(firstQuestion[0]);
		rdbtnNewRadioButton.setText(firstQuestion[0]);	
		rdbtnNewRadioButton.setText(firstQuestion[1]);		
		rdbtnNewRadioButton_1.setText(firstQuestion[2]);		
		rdbtnNewRadioButton_2.setText(firstQuestion[3]);		
		rdbtnNewRadioButton_3.setText(firstQuestion[4]);		
		
	}

	void  ValidateANdUpdateResult()
	{
		if(correctValue == userSelectedOption)
		{
			m_score++ ;

		}	
		txtScore.setText(Integer.toString(m_score));
		if((float)QAndA.myAskedQuestions.size() !=0)
		{
			float percent = ((float)m_score/QAndA.myAskedQuestions.size())*100;
			textField_1.setText(Float.toString(percent));
		}

		
			
	}
	
	private void ClearButton()
	{
		rdbtnNewRadioButton.setSelected(false);
		rdbtnNewRadioButton_1.setSelected(false);
		rdbtnNewRadioButton_2.setSelected(false);	
		rdbtnNewRadioButton_3.setSelected(false);			
	}
}
