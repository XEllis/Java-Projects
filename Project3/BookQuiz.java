import java.util.List;

/**
 * A quiz application project implements QuizMaster
 * 
 * @author xellis
 *
 */
public class BookQuiz implements QuizMaster {
	
	private QuestionReader reader;
	
	private QuestionWriter writer;
	
	private BookQuestions questions;
	
	/**
	 * Initializes BookQuiz's three data members: reader, writer, and questions.
	 * @param fileName name of file to process
	 * @throws QuestionException thrown when problem processing file
	 */
	public BookQuiz(String fileName) throws QuestionException {
		reader = new QuestionReader(fileName);
		questions = new BookQuestions(reader.getStandardQuestions(), reader.getElementaryQuestions(), reader.getAdvancedQuestions());
	}
	
	/**
	 * Are there any more questions remaining in this quiz?
	 * @return true if there are, false if there are not
	 */
	@Override
	public boolean hasMoreQuestions() {
		return questions.hasMoreQuestions();
	}

	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	@Override
	public String getCurrentQuestionText() throws EmptyQuestionListException {
		return questions.getCurrentQuestionText();
	}

	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	@Override
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		return questions.getCurrentQuestionChoices();
	}
	
	/**
	 * Process the user's answer to the current question.
	 * @param answer  the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	@Override
	public String processAnswer(String answer) throws EmptyQuestionListException {
		return questions.processAnswer(answer);
	}

	/**
	 * How many questions has the user answered correctly?
	 * @return the number of correct answers
	 */
	@Override
	public int getNumCorrectQuestions() {
		return questions.getNumCorrectQuestions();
	}

	/**
	 * How many questions has the user attempted to answer.
	 * @return the number of attempts
	 */
	@Override
	public int getNumAttemptedQuestions() {
		return questions.getNumAttemptedQuestions();
	}

	/**
	 * Adds a StandardQuestion to the QuestionLibrary.
	 * @param questionText text of question
	 * @param questionChoices array of possible answers
	 * @param correctAnswer correct answer
	 * @throws IllegalArgumentException if any parameters contain null or empty strings
	 */
	@Override
	public void addStandardQuestion(String questionText, String[] questionChoices, String correctAnswer) {
		if (questionText == null || questionText.equals("") || correctAnswer == null || correctAnswer.equals("")) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		if (questionChoices == null || questionChoices.length != 4 || correctAnswer.length() != 1) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		for (int i = 0; i < questionChoices.length; i++) {
			if (questionChoices[i] == null || questionChoices[i].equals("")) {
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		if (!correctAnswer.equalsIgnoreCase("A") && !correctAnswer.equalsIgnoreCase("B") && !correctAnswer.equalsIgnoreCase("C") && !correctAnswer.equalsIgnoreCase("D")) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		ObjectFactory of = new ObjectFactory();
		StandardQuestion s = of.createStandardQuestion();
		s.setQuestion(questionText);
		s.setChoiceA(questionChoices[0]);
		s.setChoiceB(questionChoices[1]);
		s.setChoiceC(questionChoices[2]);
		s.setChoiceD(questionChoices[3]);
		s.setAnswer(correctAnswer);
		questions.addStandardQuestion(s);
	}

	/**
	 * Adds an ElementaryQuestion to the QuestionLibrary.
	 * @param questionText text of question
	 * @param questionChoices array of possible answers
	 * @param correctAnswer correct answer
	 * @param hint a hint for the question
	 * @throws IllegalArgumentException if any parameters contain null or empty strings
	 */
	@Override
	public void addElementaryQuestion(String questionText, String[] questionChoices, String correctAnswer, String hint) {
		if (questionText == null || questionText.equals("") || correctAnswer == null || correctAnswer.equals("") || hint == null || hint.equals("")) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		if (questionChoices == null || questionChoices.length != 4 || correctAnswer.length() != 1) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		for (int i = 0; i < questionChoices.length; i++) {
			if (questionChoices[i] == null || questionChoices[i].equals("")) {
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		if (!correctAnswer.equalsIgnoreCase("A") && !correctAnswer.equalsIgnoreCase("B") && !correctAnswer.equalsIgnoreCase("C") && !correctAnswer.equalsIgnoreCase("D")) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		ObjectFactory of = new ObjectFactory();
		ElementaryQuestion s = of.createElementaryQuestion();
		s.setQuestion(questionText);
		s.setChoiceA(questionChoices[0]);
		s.setChoiceB(questionChoices[1]);
		s.setChoiceC(questionChoices[2]);
		s.setChoiceD(questionChoices[3]);
		s.setAnswer(correctAnswer);
		s.setHint(hint);
		questions.addElementaryQuestion(s);	
	}

	/**
	 * Adds an AdvancedQuestion to the QuestionLibrary.
	 * @param questionText text of question
	 * @param questionChoices array of possible answers
	 * @param correctAnswer correct answer
	 * @param comment a message for answering the question right
	 * @throws IllegalArgumentException if any parameters contain null or empty strings
	 */
	@Override
	public void addAdvancedQuestion(String questionText, String[] questionChoices, String correctAnswer, String comment) {
		if (questionText == null || questionText.equals("") || correctAnswer == null || correctAnswer.equals("") || comment == null || comment.equals("")) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		if (questionChoices == null || questionChoices.length != 4 || correctAnswer.length() != 1) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		for (int i = 0; i < questionChoices.length; i++) {
			if (questionChoices[i] == null || questionChoices[i].equals("")) {
				throw new IllegalArgumentException("Cannot create question.");
			}
		}
		if (!correctAnswer.equalsIgnoreCase("A") && !correctAnswer.equalsIgnoreCase("B") && !correctAnswer.equalsIgnoreCase("C") && !correctAnswer.equalsIgnoreCase("D")) {
			throw new IllegalArgumentException("Cannot create question.");
		}
		ObjectFactory of = new ObjectFactory();
		AdvancedQuestion s = of.createAdvancedQuestion();
		s.setQuestion(questionText);
		s.setChoiceA(questionChoices[0]);
		s.setChoiceB(questionChoices[1]);
		s.setChoiceC(questionChoices[2]);
		s.setChoiceD(questionChoices[3]);
		s.setAnswer(correctAnswer);
		s.setComment(comment);
		questions.addAdvancedQuestion(s);
	}

	/**
	 * Writes the Questions to the given file
	 * @param questionFile file name to write questions to
	 * @throws QuestionException if the questions cannot be written to the 
	 * given file
	 */
	@Override
	public void writeQuestions(String questionFile) throws QuestionException {
		writer = new QuestionWriter(questionFile);
		List<Question> stdQuestions = questions.getStandardQuestions();
		List<Question> elemQuestions = questions.getElementaryQuestions();
		List<Question> advQuestions = questions.getAdvancedQuestions();
		for (int i = 0; i < stdQuestions.size(); i++) {
			writer.addStandardQuestion((StandardQuestion) stdQuestions.get(i));
		}
		for (int j = 0; j < elemQuestions.size(); j++) {
			writer.addElementaryQuestion((ElementaryQuestion) elemQuestions.get(j));
		}
		for (int k = 0; k < advQuestions.size(); k++) {
			writer.addAdvancedQuestion((AdvancedQuestion) advQuestions.get(k));
		}
		writer.marshal();
	}

}
