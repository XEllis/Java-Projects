import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BookQuiz.  Tests that the object is constructed correctly 
 * and that the state of the object is manipulated properly.
 * 
 * @author xellis
 *
 */
public class BookQuizTest {
	
	private BookQuiz bookQuiz0;
	private BookQuiz bookQuiz1;
	private BookQuiz bookQuiz2;
	private BookQuiz bookQuiz3;
	private BookQuiz bookQuiz = null;
	
	/** Min number of invalid questions file */
	private static final int MIN_NUMBER_FILE = 2;
	/** Max number of invalid questions file */
	private static final int MAX_NUMBER_FILE = 12;

	/**
	 * Sets up the BookQuizTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bookQuiz0 = new BookQuiz("all.xml");
		bookQuiz1 = new BookQuiz("elementary1.xml");
		bookQuiz2 = new BookQuiz("advanced1.xml");
		bookQuiz3 = new BookQuiz("questions1.xml");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#BookQuiz(java.lang.String)}.
	 */
	@Test
	public void testBookQuiz() {
		String fileName = null;
		try {
			bookQuiz = new BookQuiz("question0.xml");
			fail("QuestionException - error processing intelligent tutoring question file" + "--" + "question0.xml");
		} catch (QuestionException e) {
			assertNull("QuestionException - error processing intelligent tutoring question file" + "--" + "question0.xml", bookQuiz);
		}
		
		for (int i = MIN_NUMBER_FILE; i <= MAX_NUMBER_FILE; i++) {
			fileName = "question" + i + ".xml";
			try {
				bookQuiz = new BookQuiz(fileName);
				fail("QuestionException - error processing intelligent tutoring question file" + "--" + fileName);
			} catch (QuestionException e) {
				assertNull("QuestionException - error processing intelligent tutoring question file" + "--" + fileName, bookQuiz);
			}
		}	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#hasMoreQuestions()}.
	 */
	@Test
	public void testHasMoreQuestions() {
		assertEquals(true, bookQuiz0.hasMoreQuestions());
		try {
			String s = bookQuiz0.processAnswer("D");
			assertEquals("Correct!", s);
		} catch (EmptyQuestionListException e) {
			fail();
		}
		assertEquals(false, bookQuiz0.hasMoreQuestions());
		assertEquals(false, bookQuiz1.hasMoreQuestions());
		assertEquals(false, bookQuiz2.hasMoreQuestions());
		assertEquals(true, bookQuiz3.hasMoreQuestions());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getCurrentQuestionText()}.
	 */
	@Test
	public void testGetCurrentQuestionText() {
		String text;
		try {
			text = bookQuiz0.getCurrentQuestionText();
			assertEquals("How many licks to reach the center of a Tootsie Pop?", text);
			String s = bookQuiz0.processAnswer("A");
			assertEquals("Incorrect!", s);
			text = bookQuiz0.getCurrentQuestionText();
			assertEquals("How much wood can a woodchuck chuck?", text);
			text = bookQuiz3.getCurrentQuestionText();
			assertEquals("Standard Question 1", text);
		} catch (EmptyQuestionListException e) {
			fail();
		}
		try {
			text = bookQuiz1.getCurrentQuestionText();
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
		try {
			text = bookQuiz2.getCurrentQuestionText();
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getCurrentQuestionChoices()}.
	 */
	@Test
	public void testGetCurrentQuestionChoices() {
		String[] choices;
		String[] expected0 = {"1", "2", "3", "the world may never know"};
		String[] expected1 = {"1 bushel", "2 bushels", "3 bushels", "4 bushels"};
		String[] expected2 = {"Choice a", "Choice b", "Choice c", "Choice d"};
		try {
			choices = bookQuiz0.getCurrentQuestionChoices();
			for (int i = 0; i < expected0.length; i++) {
				assertEquals(expected0[i], choices[i]);
			}
			String s = bookQuiz0.processAnswer("B");
			assertEquals("Incorrect!", s);
			choices = bookQuiz0.getCurrentQuestionChoices();
			for (int i = 0; i < expected1.length; i++) {
				assertEquals(expected1[i], choices[i]);
			}
			choices = bookQuiz3.getCurrentQuestionChoices();
			for (int i = 0; i < expected2.length; i++) {
				assertEquals(expected2[i], choices[i]);
			}
		} catch (EmptyQuestionListException e) {
			fail();
		}
		try {
			choices = bookQuiz1.getCurrentQuestionChoices();
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
		try {
			choices = bookQuiz2.getCurrentQuestionChoices();
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#processAnswer(java.lang.String)}.
	 */
	@Test
	public void testProcessAnswer() {
		String feedback;
		try {
			feedback = bookQuiz0.processAnswer("C");
			assertEquals("Incorrect!", feedback);
			feedback = bookQuiz0.processAnswer("A");
			assertEquals("Incorrect! Greater than 1 bushel and less than 3 bushels.", feedback);
			feedback = bookQuiz0.processAnswer("B");
			assertEquals("Correct!", feedback);
			feedback = bookQuiz3.processAnswer("D");
			assertEquals("Correct!", feedback);
			feedback = bookQuiz3.processAnswer("C");
			assertEquals("Correct!", feedback);
			feedback = bookQuiz3.processAnswer("D");
			assertEquals("Correct! Great work!", feedback);
		} catch (EmptyQuestionListException e) {
			fail();
		}
		try {
			feedback = bookQuiz0.processAnswer("A");
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
		try {
			feedback = bookQuiz1.processAnswer("B");
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
		try {
			feedback = bookQuiz2.processAnswer("C");
			fail();
		} catch (EmptyQuestionListException e) {
			assertEquals("EmptyQuestionListException.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getNumCorrectQuestions()}.
	 */
	@Test
	public void testGetNumCorrectQuestions() {
		try {
			assertEquals(0, bookQuiz0.getNumCorrectQuestions());
			bookQuiz0.processAnswer("D");
			assertEquals(1, bookQuiz0.getNumCorrectQuestions());
			assertEquals(0, bookQuiz3.getNumCorrectQuestions());
			bookQuiz3.processAnswer("D");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("B");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("D");
			bookQuiz3.processAnswer("A");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("A");
			bookQuiz3.processAnswer("B");
			assertEquals(6, bookQuiz3.getNumCorrectQuestions());
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#getNumAttemptedQuestions()}.
	 */
	@Test
	public void testGetNumAttemptedQuestions() {
		try {
			assertEquals(0, bookQuiz0.getNumAttemptedQuestions());
			bookQuiz0.processAnswer("D");
			assertEquals(1, bookQuiz0.getNumAttemptedQuestions());
			assertEquals(0, bookQuiz3.getNumAttemptedQuestions());
			bookQuiz3.processAnswer("D");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("B");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("D");
			bookQuiz3.processAnswer("A");
			bookQuiz3.processAnswer("C");
			bookQuiz3.processAnswer("A");
			bookQuiz3.processAnswer("B");
			assertEquals(8, bookQuiz3.getNumAttemptedQuestions());
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#addStandardQuestion(java.lang.String, java.lang.String[], java.lang.String)}.
	 */
	@Test
	public void testAddStandardQuestion() {
		String[] a = {"A"};
		String[] b = {"A", "B", "C", "D"};
		String[] c = {"A", null, "C", "D"};
		String[] d = {"A", "B", "C", ""};
		try {
			bookQuiz0.addStandardQuestion(null, b, "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addStandardQuestion("A", null, "B");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addStandardQuestion("A", b, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}

		try {
			bookQuiz0.addStandardQuestion("", b, "C");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addStandardQuestion("A", a, "D");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addStandardQuestion("A", b, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		
		try {
			bookQuiz0.addStandardQuestion("A", b, "AB");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addStandardQuestion("A", c, "a");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addStandardQuestion("A", d, "b");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz3.addStandardQuestion("A", b, " ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		
		bookQuiz1.addStandardQuestion("S1", b, "a");
		bookQuiz1.addStandardQuestion("S2", b, "C");
		bookQuiz1.addStandardQuestion("S3", b, "d");
		bookQuiz1.addStandardQuestion("S4", b, "B");
		try {
			assertEquals("S1", bookQuiz1.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz1.processAnswer("A"));
			assertEquals("S2", bookQuiz1.getCurrentQuestionText());
			assertEquals("Incorrect!", bookQuiz1.processAnswer("b"));
			assertEquals("How much wood can a woodchuck chuck?", bookQuiz1.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz1.processAnswer("B"));
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#addElementaryQuestion(java.lang.String, java.lang.String[], java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddElementaryQuestion() {
		String[] a = {"A"};
		String[] b = {"A", "B", "C", "D"};
		String[] c = {"A", null, "C", "D"};
		String[] d = {"A", "B", "C", ""};
		try {
			bookQuiz0.addElementaryQuestion(null, b, "A", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addElementaryQuestion("A", null, "B", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addElementaryQuestion("A", b, null, "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz3.addElementaryQuestion("A", b, "C", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}

		try {
			bookQuiz0.addElementaryQuestion("", b, "D", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addElementaryQuestion("A", a, "a", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addElementaryQuestion("A", b, "", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz3.addElementaryQuestion("A", b, "b", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		
		try {
			bookQuiz0.addElementaryQuestion("A", b, "AB", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addElementaryQuestion("A", c, "c", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addElementaryQuestion("A", d, "d", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz3.addElementaryQuestion("A", b, " ", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		
		bookQuiz0.addElementaryQuestion("E1", b, "a", "a");
		bookQuiz0.addElementaryQuestion("E2", b, "C", "c");
		bookQuiz0.addElementaryQuestion("E3", b, "d", "d");
		bookQuiz0.addElementaryQuestion("E4", b, "B", "b");
		try {
			assertEquals("Incorrect!", bookQuiz0.processAnswer("b"));
			assertEquals("Correct!", bookQuiz0.processAnswer("B"));
			assertEquals("E1", bookQuiz0.getCurrentQuestionText());
			assertEquals("Incorrect! a", bookQuiz0.processAnswer("c"));
			assertEquals("E1", bookQuiz0.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz0.processAnswer("A"));
			assertEquals("E2", bookQuiz0.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz0.processAnswer("c"));
			assertEquals("Incorrect! d", bookQuiz0.processAnswer("a"));
			assertEquals("Correct!", bookQuiz0.processAnswer("D"));
			assertEquals("Correct!", bookQuiz0.processAnswer("b"));
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#addAdvancedQuestion(java.lang.String, java.lang.String[], java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddAdvancedQuestion() {
		String[] a = {"A"};
		String[] b = {"A", "B", "C", "D"};
		String[] c = {"A", null, "C", "D"};
		String[] d = {"A", "B", "C", ""};
		try {
			bookQuiz0.addAdvancedQuestion(null, b, "A", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addAdvancedQuestion("A", null, "B", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addAdvancedQuestion("A", b, null, "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz3.addAdvancedQuestion("A", b, "C", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}

		try {
			bookQuiz0.addAdvancedQuestion("", b, "D", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addAdvancedQuestion("A", a, "a", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addAdvancedQuestion("A", b, "", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz3.addAdvancedQuestion("A", b, "b", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		
		try {
			bookQuiz0.addAdvancedQuestion("A", b, "AB", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz1.addAdvancedQuestion("A", c, "c", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz2.addAdvancedQuestion("A", d, "d", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		try {
			bookQuiz3.addAdvancedQuestion("A", b, " ", "A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot create question.", e.getMessage());
		}
		
		bookQuiz2.addStandardQuestion("S3", b, "d");
		bookQuiz2.addStandardQuestion("S4", b, "B");
		bookQuiz2.addAdvancedQuestion("A1", b, "a", "a");
		bookQuiz2.addAdvancedQuestion("A2", b, "C", "c");
		bookQuiz2.addAdvancedQuestion("A3", b, "d", "d");
		bookQuiz2.addAdvancedQuestion("A4", b, "B", "b");
		try {
			assertEquals("S3", bookQuiz2.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz2.processAnswer("D"));
			assertEquals("S4", bookQuiz2.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz2.processAnswer("b"));
			assertEquals("What is the answer to life, the universe, and everything?", bookQuiz2.getCurrentQuestionText());
			assertEquals("Correct! Now, what is the question?", bookQuiz2.processAnswer("A"));
			assertEquals("A1", bookQuiz2.getCurrentQuestionText());
			assertEquals("Correct! a", bookQuiz2.processAnswer("A"));
			assertEquals("A2", bookQuiz2.getCurrentQuestionText());
			assertEquals("Correct! c", bookQuiz2.processAnswer("C"));
			assertEquals("A3", bookQuiz2.getCurrentQuestionText());
			assertEquals("Correct! d", bookQuiz2.processAnswer("d"));
			assertEquals("A4", bookQuiz2.getCurrentQuestionText());
			assertEquals("Correct! b", bookQuiz2.processAnswer("B"));
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.book_quiz.quiz.BookQuiz#writeQuestions(java.lang.String)}.
	 */
	@Test
	public void testWriteQuestions() {
		try {
			bookQuiz3.writeQuestions("ForWriting.xml");
			bookQuiz = new BookQuiz("ForWriting.xml");
			assertEquals("Standard Question 1", bookQuiz.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz.processAnswer("d"));
			assertEquals("Standard Question 2", bookQuiz.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz.processAnswer("C"));
			assertEquals("Advanced Question 1", bookQuiz.getCurrentQuestionText());
			assertEquals("Incorrect!", bookQuiz.processAnswer("c"));
			assertEquals("Standard Question 3", bookQuiz.getCurrentQuestionText());
			assertEquals("Incorrect!", bookQuiz.processAnswer("B"));
			assertEquals("Elementary Question 1", bookQuiz.getCurrentQuestionText());
			assertEquals("Correct!", bookQuiz.processAnswer("d"));
		} catch (QuestionException e) {
			fail();
		} catch (EmptyQuestionListException e) {
			fail();
		}
	}

}
