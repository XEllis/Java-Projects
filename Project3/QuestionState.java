import java.util.ArrayList;
import java.util.List;

/**
 * QuestionState is the abstract state part of the State Design Pattern.
 * 
 * @author xellis
 *
 */
public abstract class QuestionState {
	
	/**
	 * The front index of the waitingQuestions.
	 */
	private final static int FRONT = 0;
	/**
	 * Contains all questions that are unasked. 
	 * Any new questions the user creates are added to the end of this collection.
	 */
	private List<Question> waitingQuestions;
	/**
	 * Contains all questions that have been asked. 
	 * The last question in this list is the currentQuestion.
	 */
	private List<Question> askedQuestions;
	/**
	 * A reference to the current question 
	 * that would be asked of the user if the quiz is in the given state.
	 */
	private Question currentQuestion;
	
	/**
	 * Construct QuestionState.
	 * @param waitingQuestions a list of all questions that are unasked
	 */
	public QuestionState(List<Question> waitingQuestions) {
		this.waitingQuestions = waitingQuestions;
		askedQuestions = new ArrayList<Question>();
		if (waitingQuestions.size() != FRONT) {
			currentQuestion = waitingQuestions.get(FRONT);
		}
	}
	
	/**
	 * Abstract method to process the user's answer; 
	 * throws an EmptyQuestionListExceptions if currentQuestion is null. 
	 * This method corresponds to transitions in the FSM. 
	 * Each concrete state (nested classes inside BookQuestions) defines this method 
	 * according to the transitions that go from that state.
	 * @param answer the user's answer 
	 * @throws EmptyQuestionListException thrown if currentQuestion is null
	 * @return String the feedback message depending on the user's answer and the type of question asked
	 */
	public abstract String processAnswer(String answer) throws EmptyQuestionListException;
	
	/**
	 * True if currentQuestion is not null or waitingQuestions is not empty.
	 * @return true if currentQuestion is not null or waitingQuestions is not empty
	 */
	public boolean hasMoreQuestions() {
		return currentQuestion != null || waitingQuestions.size() != FRONT;
	}
	
	/**
	 * Throws EmptyQuestionListExceptions if currentQuestion is null.
	 * @return the value of the question property of the current question
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException {
		if (currentQuestion == null) {
			throw new EmptyQuestionListException();
		}
		return currentQuestion.getQuestion();
	}
	
	/**
	 * Throws EmptyQuestionListExceptions if currentQuestion is null.
	 * @return the value of the choices' property of the current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		if (currentQuestion == null) {
			throw new EmptyQuestionListException();
		}
		String[] choices = new String[4];
		choices[0] = currentQuestion.getChoiceA();
		choices[1] = currentQuestion.getChoiceB();
		choices[2] = currentQuestion.getChoiceC();
		choices[3] = currentQuestion.getChoiceD();
		return choices;
	}
	
	/**
	 * Throws EmptyQuestionListExceptions if currentQuestion is null.
	 * @return the value of the answer property of the current question
	 */
	public String getCurrentQuestionAnswer() throws EmptyQuestionListException {
		if (currentQuestion == null) {
			throw new EmptyQuestionListException();
		}
		return currentQuestion.getAnswer();
	}
	
	/**
	 * Throws EmptyQuestionListExceptions if currentQuestion is null.
	 * @return the current question
	 */
	public Question getCurrentQuestion() throws EmptyQuestionListException {
		if (currentQuestion == null) {
			throw new EmptyQuestionListException();
		}
		return currentQuestion;
	}
	
	/**
	 * Sets currentQuestion to the next item in the waitingQuestions list, 
	 * or null if there are no more questions in the list. 
	 * The currentQuestion is added to the end of the askedQuestions list.
	 */
	public void nextQuestion() {
		askedQuestions.add(waitingQuestions.remove(FRONT));
		if (hasMoreQuestions()) {
			if (currentQuestion != null) {
				if (waitingQuestions.size() != FRONT) {
					currentQuestion = waitingQuestions.get(FRONT);
				} else {
					currentQuestion = null;
				}
			} else {
				currentQuestion = waitingQuestions.get(FRONT);
			}	
		}
	}
	
	/**
	 * Adds the given question to the end of the waitingQuestions list. 
	 * If currentQuestion is null, implying there are no more questions of that type to ask, 
	 * nextQuestion() is executed because there is now a question to ask.
	 * @param question the given question
	 */
	public void addQuestion(Question question) {
		waitingQuestions.add(question);
		if (currentQuestion == null) {
			currentQuestion = waitingQuestions.get(FRONT);
		}
	}
	
	/**
	 * Returns a list of questions. 
	 * The list of questions combines the askedQuestions with the waitingQuestions. 
	 * The askedQuestions are added to the joint list first.
	 * @return a list of questions
	 */
	public List<Question> getQuestions() {
		List<Question> questions = new ArrayList<Question>();
		questions.addAll(askedQuestions);
		questions.addAll(waitingQuestions);
		return questions;
	}

}
