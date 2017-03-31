import java.util.ArrayList;
import java.util.List;

/**
 * BookQuestions is the context part of the State Design Pattern. 
 * It maintains the questions and determines the order in which they are to be presented based on the user's answers.
 * 
 * @author xellis
 *
 */
public class BookQuestions {
	
	/**
	 * Keeps track of the number answered correctly.
	 */
	private int numCorrectAnswers = 0;
	/**
	 * Keeps track of the number of questions attempted.
	 */
	private int numAttemptQuests = 0;
	
	/**
	 * Elementary question state
	 */
	private QuestionState elemState;
	/**
	 * Standard question state
	 */
	private QuestionState stdState;
	/**
	 * Advanced question state
	 */
	private QuestionState advState;
	
	/**
	 * Current question state
	 */
	private QuestionState state;
	
	/**
	 * Report the results of submitting a correct answer.
	 */
	public static final String CORRECT = "Correct!";
	/**
	 * Report the results of submitting a incorrect answer.
	 */
	public static final String INCORRECT = "Incorrect!";
	
	/**
	 * Report the results of submitting an answer.
	 */
	public static final String SEPARATOR = " ";
	
	/**
	 * Takes three Lists to initialize its inner concrete state classes.
	 * @param stdQuestions a list of standard questions
	 * @param elemQuestions a list of elementary questions
	 * @param advQuestions a list of advanced questions
	 */
	public BookQuestions(List<StandardQuestion> stdQuestions, List<ElementaryQuestion> elemQuestions, List<AdvancedQuestion> advQuestions) {
		stdState = new StandardQuestionState(stdQuestions);
		elemState = new ElementaryQuestionState(elemQuestions);
		advState = new AdvancedQuestionState(advQuestions);
		state = stdState;
	}
	
	/**
	 * True if there are any more questions remaining in the quiz.
	 * @return true if there are any more questions remaining in the quiz
	 */
	public boolean hasMoreQuestions() {
		return state.hasMoreQuestions();
	}
	
	/**
	 * Gets the current question.
	 * Throws EmptyQuestionListExceptions if there is no valid question information to return. 
	 * This method delegates to the current state.
	 * @return the value of the question property of the current question
	 * @throws EmptyQuestionListException thrown if there is no valid question information to return
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException{
		return state.getCurrentQuestionText();
	}
	
	/**
	 * Gets the current question's choices.
	 * Throws EmptyQuestionListExceptions if there is no valid question information to return. 
	 * This method delegates to the current state.
	 * @return the value of the choices' property of the current question
	 * @throws EmptyQuestionListException thrown if there is no valid question information to return
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException{
		return state.getCurrentQuestionChoices();
	}
	
	/**
	 * Processes the answer submitted by the user. 
	 * BookQuestions relies on its current state to handle the processing. 
	 * This method may throw an EmptyQuestionListException if the answer cannot be processed.
	 * @param answer the user's answer
	 * @return String the feedback message depending on the user's answer and the type of question asked
	 */
	public String processAnswer(String answer) throws EmptyQuestionListException{
		return state.processAnswer(answer);
	}

	/**
	 * Gets the number answered correctly.
	 * @return the numCorrectAnswers the number answered correctly
	 */
	public int getNumCorrectQuestions() {
		return numCorrectAnswers;
	}

	/**
	 * Gets the number of questions attempted.
	 * @return the numAttemptQuests the number of questions attempted
	 */
	public int getNumAttemptedQuestions() {
		return numAttemptQuests;
	}
	
	/**
	 * Adds the question of the standard question type to the standard question state object through delegation.
	 * @param stdQuestion the given standard question
	 */
	public void addStandardQuestion(StandardQuestion stdQuestion) {
		stdState.addQuestion(stdQuestion);
	}
	
	/**
	 * Adds the question of the elementary question type to the elementary question state object through delegation.
	 * @param elemQuestion the given elementary question
	 */
	public void addElementaryQuestion(ElementaryQuestion elemQuestion) {
		elemState.addQuestion(elemQuestion);
	}
	
	/**
	 * Adds the question of the advanced question type to the advanced question state object through delegation.
	 * @param advQuestion the given advanced question
	 */
	public void addAdvancedQuestion(AdvancedQuestion advQuestion) {
		advState.addQuestion(advQuestion);
	}
	
	/**
	 * Returns a collection of questions of the standard question type through delegation to the standard question state object.
	 * @return a list of the standard question
	 */
	public List<Question> getStandardQuestions() {
		return stdState.getQuestions();
	}
	
	/**
	 * Returns a collection of questions of the elementary question type through delegation to the elementary question state object.
	 * @return a list of the elementary question
	 */
	public List<Question> getElementaryQuestions() {
		return elemState.getQuestions();
	}
	
	/**
	 * Returns a collection of questions of the advanced question type through delegation to the advanced question state object.
	 * @return a list of the advanced question
	 */
	public List<Question> getAdvancedQuestions() {
		return advState.getQuestions();
	}
	
	/**
	 * Concrete Advanced Question State of the State Design Pattern.
	 *
	 * @author xiaohuizheng
	 *
	 */
	private class AdvancedQuestionState extends QuestionState {
		
		public AdvancedQuestionState(List<AdvancedQuestion> advQuestions) {
			super(new ArrayList<Question>(advQuestions));
		}

		@Override
		public String processAnswer(String answer) throws EmptyQuestionListException {
			AdvancedQuestion advQuestion = (AdvancedQuestion) state.getCurrentQuestion();
			numAttemptQuests += 1;
			if (answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				String s = advQuestion.getComment();
				numCorrectAnswers += 1;
				state.nextQuestion();
				return CORRECT + SEPARATOR + s;
			} else {
				state.nextQuestion();
				state = stdState;
				return INCORRECT;
			}
		}
		
	}
	
	/**
	 * Concrete Elementary Question State of the State Design Pattern.
	 * 
	 * @author xiaohuizheng
	 *
	 */
	private class ElementaryQuestionState extends QuestionState {
		
		private int attempts = 0;
		private int numCorrectInRow = 0;
		
		public ElementaryQuestionState(List<ElementaryQuestion> elemQuestions) {
			super(new ArrayList<Question>(elemQuestions));
		}

		@Override
		public String processAnswer(String answer) throws EmptyQuestionListException {
			ElementaryQuestion elemQuestion = (ElementaryQuestion) state.getCurrentQuestion();
			numAttemptQuests += 1;
			if (attempts == 0 && answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				numCorrectAnswers += 1;
				numCorrectInRow += 1;
				if (numCorrectInRow == 1) {
					state.nextQuestion();
				}
				if (numCorrectInRow == 2) {
					numCorrectInRow = 0;
					state.nextQuestion();
					state = stdState;	
				}
				return CORRECT;
			} else if (attempts == 0 && !answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				attempts += 1;
				numCorrectInRow = 0;
				String s = elemQuestion.getHint();
				return INCORRECT + SEPARATOR + s;
			} else if (attempts == 1 && answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				numCorrectAnswers += 1;
				numAttemptQuests -= 1;
				attempts = 0;
				state.nextQuestion();
				return CORRECT;
			} else if (attempts == 1 && !answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				numAttemptQuests -= 1;
				attempts = 0;
				state.nextQuestion();
				return INCORRECT;
			}
			return null;
		}
		
	}
	
	/**
	 * Concrete Standard Question State of the State Design Pattern.
	 * 
	 * @author xiaohuizheng
	 *
	 */
	private class StandardQuestionState extends QuestionState {
		
		private int numCorrectInRow = 0;
		
		public StandardQuestionState(List<StandardQuestion> stdQuestions) {
			super(new ArrayList<Question>(stdQuestions));
		}

		@Override
		public String processAnswer(String answer) throws EmptyQuestionListException {
			numAttemptQuests += 1;
			if (!answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				numCorrectInRow = 0;
				state.nextQuestion();
				state = elemState;
				return INCORRECT;
			} else if (numCorrectInRow == 0 && answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				numCorrectAnswers += 1;
				numCorrectInRow += 1;
				state.nextQuestion();
				return CORRECT;
			} else if (numCorrectInRow == 1 && answer.equalsIgnoreCase(state.getCurrentQuestionAnswer())) {
				numCorrectAnswers += 1;
				numCorrectInRow = 0;
				state.nextQuestion();
				state = advState;
				return CORRECT;
			}
			return null;
		}
	}

}
