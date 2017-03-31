import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BookQuestions.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * 
 * @author xellis
 *
 */
public class BookQuestionsTest {
	
	private QuestionReader reader;
	private BookQuestions bookQuestions;

	/**
	 * Sets up the BookQuestionsTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reader = new QuestionReader("exp_all.xml");
		bookQuestions = new BookQuestions(reader.getStandardQuestions(), reader.getElementaryQuestions(), reader.getAdvancedQuestions());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#hasMoreQuesions()}.
	 */
	@Test
	public void testHasMoreQuesions() {
		assertEquals(true, bookQuestions.hasMoreQuestions());
		try {
			assertEquals("Correct!", bookQuestions.processAnswer("d"));
			assertEquals(false, bookQuestions.hasMoreQuestions());
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getCurrentQuestionText()}.
	 */
	@Test
	public void testGetCurrentQuestionText() {
		try {
			assertEquals("How many licks to reach the center of a Tootsie Pop?", bookQuestions.getCurrentQuestionText());
			assertEquals("Correct!", bookQuestions.processAnswer("D"));
		} catch (EmptyQuestionListException e) {
			fail();
		}
		try {
			bookQuestions.getCurrentQuestionText();
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getCurrentQuestionChoices()}.
	 */
	@Test
	public void testGetCurrentQuestionChoices() {
		String[] expected = {"1", "2", "3", "the world may never know"};
		try {
			for (int i = 0; i < expected.length; i++) {
				assertEquals(expected[i], bookQuestions.getCurrentQuestionChoices()[i]);
			}
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#processAnswer(java.lang.String)}.
	 */
	@Test
	public void testProcessAnswer() {
		try {
			assertEquals("Incorrect!", bookQuestions.processAnswer("B"));
			assertEquals("Incorrect! Greater than 1 bushel and less than 3 bushels.", bookQuestions.processAnswer("c"));
			assertEquals("Incorrect!", bookQuestions.processAnswer("A"));
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getNumCorrectAnswers()}.
	 */
	@Test
	public void testGetNumCorrectAnswers() {
		try {
			assertEquals(0, bookQuestions.getNumCorrectQuestions());
			assertEquals(0, bookQuestions.getNumAttemptedQuestions());
			bookQuestions.processAnswer("d");
			assertEquals(1, bookQuestions.getNumCorrectQuestions());
			assertEquals(1, bookQuestions.getNumAttemptedQuestions());
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getNumAttemptQuestions()}.
	 */
	@Test
	public void testGetNumAttemptQuestions() {
		try {
			assertEquals(0, bookQuestions.getNumCorrectQuestions());
			assertEquals(0, bookQuestions.getNumAttemptedQuestions());
			bookQuestions.processAnswer("c");
			bookQuestions.processAnswer("a");
			bookQuestions.processAnswer("c");
			assertEquals(0, bookQuestions.getNumCorrectQuestions());
			assertEquals(2, bookQuestions.getNumAttemptedQuestions());
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#addStandardQuestion(edu.ncsu.csc216.question_library.StandardQuestion)}.
	 */
	@Test
	public void testAddStandardQuestion() {
		ObjectFactory of = new ObjectFactory();
		StandardQuestion s = of.createStandardQuestion();
		s.setQuestion("questionText");
		s.setChoiceA("questionChoices[0]");
		s.setChoiceB("questionChoices[1]");
		s.setChoiceC("questionChoices[2]");
		s.setChoiceD("questionChoices[3]");
		s.setAnswer("C");
		bookQuestions.addStandardQuestion(s);
		try {
			bookQuestions.processAnswer("d");
			assertEquals("questionText", bookQuestions.getCurrentQuestionText());
			assertEquals("Incorrect!", bookQuestions.processAnswer("d"));
			assertEquals("How much wood can a woodchuck chuck?", bookQuestions.getCurrentQuestionText());
			assertEquals("Correct!", bookQuestions.processAnswer("B"));
			assertEquals(false, bookQuestions.hasMoreQuestions());
			ElementaryQuestion s1 = of.createElementaryQuestion();
			s1.setQuestion("ElementaryQuestionText");
			s1.setChoiceA("questionChoices[0]");
			s1.setChoiceB("questionChoices[1]");
			s1.setChoiceC("questionChoices[2]");
			s1.setChoiceD("questionChoices[3]");
			s1.setAnswer("a");
			s1.setHint("Hint");
			bookQuestions.addElementaryQuestion(s1);
			assertEquals(true, bookQuestions.hasMoreQuestions());
			assertEquals("ElementaryQuestionText", bookQuestions.getCurrentQuestionText());
			assertEquals("Incorrect! Hint", bookQuestions.processAnswer("B"));
			assertEquals("Incorrect!", bookQuestions.processAnswer("c"));
			assertEquals(false, bookQuestions.hasMoreQuestions());
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#addElementaryQuestion(edu.ncsu.csc216.question_library.ElementaryQuestion)}.
	 */
	@Test
	public void testAddElementaryQuestion() {
		ObjectFactory of = new ObjectFactory();
		ElementaryQuestion s = of.createElementaryQuestion();
		s.setQuestion("questionText");
		s.setChoiceA("questionChoices[0]");
		s.setChoiceB("questionChoices[1]");
		s.setChoiceC("questionChoices[2]");
		s.setChoiceD("questionChoices[3]");
		s.setAnswer("a");
		s.setHint("Hint");
		bookQuestions.addElementaryQuestion(s);
		try {
			bookQuestions.processAnswer("B");
			bookQuestions.processAnswer("b");
			assertEquals("questionText", bookQuestions.getCurrentQuestionText());
			assertEquals("Incorrect! Hint", bookQuestions.processAnswer("b"));
			assertEquals("Correct!", bookQuestions.processAnswer("A"));
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#addAdvancedQuestion(edu.ncsu.csc216.question_library.AdvancedQuestion)}.
	 */
	@Test
	public void testAddAdvancedQuestion() {
		ObjectFactory of = new ObjectFactory();
		AdvancedQuestion s = of.createAdvancedQuestion();
		s.setQuestion("AdvancedQuestionText");
		s.setChoiceA("questionChoices[0]");
		s.setChoiceB("questionChoices[1]");
		s.setChoiceC("questionChoices[2]");
		s.setChoiceD("questionChoices[3]");
		s.setAnswer("d");
		s.setComment("Comment");
		bookQuestions.addAdvancedQuestion(s);
		StandardQuestion s1 = of.createStandardQuestion();
		s1.setQuestion("StandardQuestionText");
		s1.setChoiceA("questionChoices[0]");
		s1.setChoiceB("questionChoices[1]");
		s1.setChoiceC("questionChoices[2]");
		s1.setChoiceD("questionChoices[3]");
		s1.setAnswer("C");
		bookQuestions.addStandardQuestion(s1);
		try {
			bookQuestions.processAnswer("D");
			assertEquals("StandardQuestionText", bookQuestions.getCurrentQuestionText());
			bookQuestions.processAnswer("c");
			bookQuestions.processAnswer("A");
			assertEquals("AdvancedQuestionText", bookQuestions.getCurrentQuestionText());
		} catch (EmptyQuestionListException e) {
			fail();
		}
	} 

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getStandardQuestions()}.
	 */
	@Test
	public void testGetStandardQuestions() {
		List<Question> q = bookQuestions.getStandardQuestions();
		assertEquals(1, q.size());
		assertEquals("How many licks to reach the center of a Tootsie Pop?", q.get(0).getQuestion());
		assertEquals("1", q.get(0).getChoiceA());
		assertEquals("2", q.get(0).getChoiceB());
		assertEquals("3", q.get(0).getChoiceC());
		assertEquals("the world may never know", q.get(0).getChoiceD());
		assertEquals("d", q.get(0).getAnswer());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getElementaryQuestions()}.
	 */
	@Test
	public void testGetElementaryQuestions() {
		List<Question> q = bookQuestions.getElementaryQuestions();
		assertEquals(1, q.size());
		assertEquals("How much wood can a woodchuck chuck?", q.get(0).getQuestion());
		assertEquals("1 bushel", q.get(0).getChoiceA());
		assertEquals("2 bushels", q.get(0).getChoiceB());
		assertEquals("3 bushels", q.get(0).getChoiceC());
		assertEquals("4 bushels", q.get(0).getChoiceD());
		assertEquals("b", q.get(0).getAnswer());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.question.BookQuestions#getAdvancedQuestions()}.
	 */
	@Test
	public void testGetAdvancedQuestions() {
		List<Question> q = bookQuestions.getAdvancedQuestions();
		assertEquals(1, q.size());
		assertEquals("What is the answer to life, the universe, and everything?", q.get(0).getQuestion());
		assertEquals("42", q.get(0).getChoiceA());
		assertEquals("nothing", q.get(0).getChoiceB());
		assertEquals("infinity", q.get(0).getChoiceC());
		assertEquals("7", q.get(0).getChoiceD());
		assertEquals("a", q.get(0).getAnswer());
	}

}
