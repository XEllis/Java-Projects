import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * The user interface for the program.
 * 
 * @author xellis
 *
 */
public class BookQuizGUI extends JFrame implements ActionListener {
	
	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = 1L;

	private transient QuizMaster quizMaster;

	private static final String ERROR = "Error";
	
	private static final int FRAME_WIDTH = 500; 
	private static final int FRAME_HEIGHT = 300; 
	
	private static final String WINDOW_TITLE = "Book Quiz";
	private static final String ADD_QUESTIONS = "Add Questions";
	private static final String TAKE_BOOK_QUIZ = "Take Book Quiz";
	private static final String QUIT = "Quit";
	private static final String QUESTION_TYPE = "Question Type: ";
	private static final String QUESTION = "Question:";
	private static final String CHOICE_A = "Choice A:";
	private static final String CHOICE_B = "Choice B:";
	private static final String CHOICE_C = "Choice C:";
	private static final String CHOICE_D = "Choice D:";
	private static final String ANSWER = "Answer:";
	private static final String HINT = "Hint:";
	private static final String HINT_TITLE = "Hint";
	private static final String COMMENT = "Comment:";
	private static final String COMMENT_TITLE = "Comment";
	private static final String NONE = "None";
	private static final String[] QUESTIONS = {"Standard Question", "Elementary Question", "Advanced Question"};
	private static final String[] ANSWERS = {"A", "B", "C", "D"};
	private static final String ADD = "Add";
	private static final String WRITE_ALL = "Write all";
	private static final String DONE = "Done";
	private static final String SUBMIT_ANSWER = "Submit Answer";
	private static final String NEXT_QUESTION = "Next Question";
	private static final String MAIN = "Main";
	private static final String ADDING = "Adding";
	private static final String QUIZING = "Quizing";
	
	private JButton btnAddQuestions = new JButton(ADD_QUESTIONS);
	private JButton btnTakeBookQuiz = new JButton(TAKE_BOOK_QUIZ);
	private JButton btnQuit1 = new JButton(QUIT);
	private JButton btnQuit2 = new JButton(QUIT);
	private JButton btnQuit3 = new JButton(QUIT);
	
	private JButton btnAdd = new JButton(ADD);
	private JButton btnWriteAll = new JButton(WRITE_ALL);
	private JButton btnDone1 = new JButton(DONE);
	private JButton btnDone2 = new JButton(DONE);
	private JButton btnSubmitAnswer = new JButton(SUBMIT_ANSWER);
	private JButton btnNextQuestion =  new JButton(NEXT_QUESTION);
	
	private JLabel lblQuestionType = new JLabel(QUESTION_TYPE);
	private JLabel lblQuestion = new JLabel(QUESTION);
	private JLabel lblChoiceA = new JLabel(CHOICE_A);
	private JLabel lblChoiceB = new JLabel(CHOICE_B);
	private JLabel lblChoiceC = new JLabel(CHOICE_C);
	private JLabel lblChoiceD = new JLabel(CHOICE_D);
	private JLabel lblAnswer = new JLabel(ANSWER);
	private JLabel lblHint = new JLabel(HINT);
	private JLabel lblComment = new JLabel(COMMENT);
	private JLabel lblNone1 = new JLabel("");
	private JLabel lblNone2 = new JLabel("");
	
	private JComboBox<String> cmbxQuestionTypes = new JComboBox<String>(QUESTIONS);
	private JComboBox<String> cmbxAnswers = new JComboBox<String>(ANSWERS);
	
	private JPanel pnlMain = new JPanel(new FlowLayout());
	private JPanel pnlAddWrite = new JPanel(new FlowLayout());
	private JPanel pnlDoneQuit = new JPanel(new FlowLayout());
	private JPanel pnlSNDQ = new JPanel(new FlowLayout());
	private JPanel pnlAddQuestion = new JPanel(new GridLayout(9, 2));
	private JPanel pnlQuizing = new JPanel(new GridLayout(6, 1));
	private JPanel pnlTakeQuiz = new JPanel(new BorderLayout());
	private JPanel pnlCard = new JPanel();
	private JPanel pnlLabel = new JPanel();
	private JPanel pnlText = new JPanel();
	
	private JTextField txtQuestion = new JTextField();
	private JTextField txtChoiceA = new JTextField();
	private JTextField txtChoiceB = new JTextField();
	private JTextField txtChoiceC = new JTextField();
	private JTextField txtChoiceD = new JTextField();
	private JTextField txtHint = new JTextField();
	private JTextField txtComment = new JTextField();
	
	private Container mainWindow = getContentPane();
	private CardLayout mainCardLayout = new CardLayout();
	private CardLayout lblCardLayout = new CardLayout();
	private CardLayout txtCardLayout = new CardLayout();
	
	private JLabel lblQuestionText;
	private JRadioButton btnChocieA;
	private JRadioButton btnChocieB;
	private JRadioButton btnChocieC;
	private JRadioButton btnChocieD;
	private JLabel lblFeedback;
	private ButtonGroup grpAnswer = new ButtonGroup();
	
	/**
	 * Constructor for BookQuizGUI.  Creates the QuizMaster model.
	 * The GUI is initialized and set visible.
	 * Handles QuestionException if there is an error reading the file.
	 * @param filename name of file that initializes the question database
	 */
	public BookQuizGUI(String filename) {
		try {
			if (filename == null) {
				String userPickFilename = null;
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					userPickFilename = fc.getSelectedFile().getName();
				}
				quizMaster = new BookQuiz(userPickFilename);
			} else {
				quizMaster = new BookQuiz(filename);
			}
			initializeUI();
			setVisible(true);
		} catch (QuestionException er) {
			JOptionPane.showMessageDialog(new JFrame(), "Invalid File.", ERROR, JOptionPane.ERROR_MESSAGE);
			stopExecution();
		}
	}
	
	private void initializeUI() {
		setTitle(WINDOW_TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setCard();
		setMain();
		setAdding();
		setQuizing();
		addListens();
		mainWindow.add(pnlCard);
		mainCardLayout.show(pnlCard, MAIN);
	}

	private void addListens() {
		btnAdd.addActionListener(this);
		btnAddQuestions.addActionListener(this);
		btnDone1.addActionListener(this);
		btnDone2.addActionListener(this);
		btnNextQuestion.addActionListener(this);
		btnQuit1.addActionListener(this);
		btnQuit2.addActionListener(this);
		btnQuit3.addActionListener(this);
		btnSubmitAnswer.addActionListener(this);
		btnTakeBookQuiz.addActionListener(this);
		btnWriteAll.addActionListener(this);
		cmbxQuestionTypes.addActionListener(this);
	}
	
	private void setMain() {
		pnlMain.add(btnAddQuestions);
		pnlMain.add(btnTakeBookQuiz);
		pnlMain.add(btnQuit1);
	}
	
	/**
	 * Private Method - sets the add user interface.
	 */
	private void setAdding() {
		pnlAddWrite.add(btnAdd);
		pnlAddWrite.add(btnWriteAll);
		pnlDoneQuit.add(btnDone1);
		pnlDoneQuit.add(btnQuit2);
		pnlAddQuestion.add(lblQuestionType);
		pnlAddQuestion.add(cmbxQuestionTypes);
		pnlAddQuestion.add(lblQuestion);
		pnlAddQuestion.add(txtQuestion);
		pnlAddQuestion.add(lblChoiceA);
		pnlAddQuestion.add(txtChoiceA);
		pnlAddQuestion.add(lblChoiceB);
		pnlAddQuestion.add(txtChoiceB);
		pnlAddQuestion.add(lblChoiceC);
		pnlAddQuestion.add(txtChoiceC);
		pnlAddQuestion.add(lblChoiceD);
		pnlAddQuestion.add(txtChoiceD);
		pnlAddQuestion.add(lblAnswer);
		pnlAddQuestion.add(cmbxAnswers);
		pnlAddQuestion.add(pnlLabel);
		pnlAddQuestion.add(pnlText);
		pnlAddQuestion.add(pnlAddWrite);
		pnlAddQuestion.add(pnlDoneQuit);
		lblCardLayout.show(pnlLabel, NONE);
		txtCardLayout.show(pnlText, NONE);
	}
	
	/**
	 * Private Method - sets the quiz user interface.
	 */
	private void setQuizing() {
		lblQuestionText = new JLabel();
		btnChocieA = new JRadioButton();
		btnChocieB = new JRadioButton();
		btnChocieC = new JRadioButton();
		btnChocieD = new JRadioButton();
		grpAnswer.add(btnChocieA);
		grpAnswer.add(btnChocieB);
		grpAnswer.add(btnChocieC);
		grpAnswer.add(btnChocieD);
		lblFeedback = new JLabel("");
		pnlQuizing.add(lblQuestionText);
		pnlQuizing.add(btnChocieA);
		pnlQuizing.add(btnChocieB);
		pnlQuizing.add(btnChocieC);
		pnlQuizing.add(btnChocieD);
		pnlQuizing.add(lblFeedback);
		pnlSNDQ.add(btnSubmitAnswer);
		pnlSNDQ.add(btnNextQuestion);
		pnlSNDQ.add(btnDone2);
		pnlSNDQ.add(btnQuit3);
		pnlTakeQuiz.add(pnlQuizing, BorderLayout.CENTER);
		pnlTakeQuiz.add(pnlSNDQ, BorderLayout.SOUTH);
	}
	
	private void refreshQuizing() {
		try {
			lblQuestionText.setText(quizMaster.getCurrentQuestionText());
			String[] answers = quizMaster.getCurrentQuestionChoices();
			btnChocieA.setText(answers[0]);
			btnChocieB.setText(answers[1]);
			btnChocieC.setText(answers[2]);
			btnChocieD.setText(answers[3]);
			grpAnswer.clearSelection();
			lblFeedback.setText("");
			btnSubmitAnswer.setEnabled(true);
			mainCardLayout.show(pnlCard, QUIZING);
		} catch(EmptyQuestionListException er) {
			JOptionPane.showMessageDialog(new JFrame(), er.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
			String message = "You answered ";
			message += quizMaster.getNumCorrectQuestions();
			message += " questions correctly out of ";
			message += quizMaster.getNumAttemptedQuestions();
			message += " attempts.";
			JOptionPane.showMessageDialog(new JFrame(), message);
			mainCardLayout.show(pnlCard, MAIN);
		}
	}
	
	/**
	 * Private Method - sets the CardLayout.
	 */
	private void setCard() {
		pnlCard.setLayout(mainCardLayout);
		pnlCard.add(pnlMain, MAIN);
		pnlCard.add(pnlAddQuestion, ADDING);
		pnlCard.add(pnlTakeQuiz, QUIZING);
		
		pnlLabel.setLayout(lblCardLayout);
		pnlLabel.add(lblNone1, NONE);
		pnlLabel.add(lblHint, HINT_TITLE);
		pnlLabel.add(lblComment, COMMENT_TITLE);
		
		pnlText.setLayout(txtCardLayout);
		pnlText.add(lblNone2, NONE);
		pnlText.add(txtHint, HINT_TITLE);
		pnlText.add(txtComment, COMMENT_TITLE);
	}
	
	/**
	 * Private Method - exits the program.
	 */
	private static void stopExecution() {
		System.exit(0);
	}
	
	/**
	 * Starts the program.  
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		try {
			if (args.length > 0)
				new BookQuizGUI(args[0]);
			else
				new BookQuizGUI(null);
		} catch (IllegalArgumentException er) {
			JOptionPane.showMessageDialog(new JFrame(), "Invalid File.", ERROR, JOptionPane.ERROR_MESSAGE);
			stopExecution();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAddQuestions)) {
			mainCardLayout.show(pnlCard, ADDING);
		}
		if (e.getSource().equals(btnTakeBookQuiz)) {
			refreshQuizing();
		}
		if (e.getSource().equals(btnQuit1)) {
			stopExecution();
		}
		if (e.getSource().equals(cmbxQuestionTypes)) {
			int x = cmbxQuestionTypes.getSelectedIndex();
			if (x == 0) {
				lblCardLayout.show(pnlLabel, NONE);
				txtCardLayout.show(pnlText, NONE);
			} else if (x == 1) {
				lblCardLayout.show(pnlLabel, HINT_TITLE);
				txtCardLayout.show(pnlText, HINT_TITLE);
			} else {
				lblCardLayout.show(pnlLabel, COMMENT_TITLE);
				txtCardLayout.show(pnlText, COMMENT_TITLE);
			}
		}
		if (e.getSource().equals(btnAdd)) {
			try {
				int x = cmbxQuestionTypes.getSelectedIndex();
				String questionText = txtQuestion.getText();
				String textChoiceA = txtChoiceA.getText();
				String textChoiceB = txtChoiceB.getText();
				String textChoiceC = txtChoiceC.getText();
				String textChoiceD = txtChoiceD.getText();
				String[] questionChoices = {textChoiceA, textChoiceB, textChoiceC, textChoiceD};
				int y = cmbxAnswers.getSelectedIndex();
				String correctAnswer = ANSWERS[y];
				if (x == 0) {
					quizMaster.addStandardQuestion(questionText, questionChoices, correctAnswer);
				} else if (x == 1) {
					String hint = txtHint.getText();
					quizMaster.addElementaryQuestion(questionText, questionChoices, correctAnswer, hint);
					txtHint.setText("");
				} else {
					String comment = txtComment.getText();
					quizMaster.addAdvancedQuestion(questionText, questionChoices, correctAnswer, comment);
					txtComment.setText("");
				}
				cmbxQuestionTypes.setSelectedIndex(0);
				txtQuestion.setText("");
				txtChoiceA.setText("");
				txtChoiceB.setText("");
				txtChoiceC.setText("");
				txtChoiceD.setText("");
				cmbxAnswers.setSelectedIndex(0);
			} catch (IllegalArgumentException er) {
				JOptionPane.showMessageDialog(new JFrame(), er.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(btnWriteAll)) {
			try {
				String userPickFilename = null;
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					userPickFilename = fc.getSelectedFile().getName();
				}
				quizMaster.writeQuestions(userPickFilename);
			} catch (Exception er) {
				JOptionPane.showMessageDialog(new JFrame(), "Unable to write to file.", ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(btnDone1)) {
			try {
				String userPickFilename = null;
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					userPickFilename = fc.getSelectedFile().getName();
				}
				quizMaster.writeQuestions(userPickFilename);
				mainCardLayout.show(pnlCard, MAIN);
			} catch (Exception er) {
				JOptionPane.showMessageDialog(new JFrame(), "Unable to write to file.", ERROR, JOptionPane.ERROR_MESSAGE);
				mainCardLayout.show(pnlCard, MAIN);
			}
		}
		if (e.getSource().equals(btnQuit2)) {
			try {
				String userPickFilename = null;
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					userPickFilename = fc.getSelectedFile().getName();
				}
				quizMaster.writeQuestions(userPickFilename);
			} catch (Exception er) {
				JOptionPane.showMessageDialog(new JFrame(), "Unable to write to file.", ERROR, JOptionPane.ERROR_MESSAGE);
			}
			stopExecution();
		}
		if (e.getSource().equals(btnSubmitAnswer)) {
			try {
				String userSelction = "";
				if (btnChocieA.isSelected()) {
					userSelction = ANSWERS[0];
				} else if (btnChocieB.isSelected()) {
					userSelction = ANSWERS[1];
				} else if(btnChocieC.isSelected()) {
					userSelction = ANSWERS[2];
				} else if (btnChocieD.isSelected()) {
					userSelction = ANSWERS[3];
				}
				String feedback = quizMaster.processAnswer(userSelction);
				lblFeedback.setText(feedback);
				btnSubmitAnswer.setEnabled(false);
			} catch (EmptyQuestionListException er) {
				JOptionPane.showMessageDialog(new JFrame(), er.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(btnNextQuestion)) {
			refreshQuizing();
		}
		if (e.getSource().equals(btnDone2)) {
			String message = "You answered ";
			message += quizMaster.getNumCorrectQuestions();
			message += " questions correctly out of ";
			message += quizMaster.getNumAttemptedQuestions();
			message += " attempts.";
			JOptionPane.showMessageDialog(new JFrame(), message);
			mainCardLayout.show(pnlCard, MAIN);
		}
		if (e.getSource().equals(btnQuit3)) {
			String message = "You answered ";
			message += quizMaster.getNumCorrectQuestions();
			message += " questions correctly out of ";
			message += quizMaster.getNumAttemptedQuestions();
			message += " attempts.";
			JOptionPane.showMessageDialog(new JFrame(), message);
			stopExecution();
		}
	}
	
}
