import java.util.List;

import junit.framework.TestCase;

/**
 * Tests for the Question Reader class of the Question Library.
 * 
 * We are testing that the correct questions are returned for a
 * valid XML file and that an exception is thrown in cases
 * where the file is invalid.
 * 
 * @author xellis
 */
public class QuestionReaderTest extends TestCase {

	/** Constant for the number of standard questions in questions1.xml */
	private static final int Q1_NUM_STD_QUESTIONS = 3;
	/** Constant for the number of elementary questions in questions1.xml */
	private static final int Q1_NUM_ELEM_QUESTIONS = 4;
	/** Constant for the number of advanced questions in questions1.xml */
	private static final int Q1_NUM_ADV_QUESTIONS = 2;
	/** Min number of questions file */
	private static final int MIN_QUESTIONS_FILE = 2;
	/** Max number of questions file */
	private static final int MAX_QUESTIONS_FILE = 12;
	
	/**
	 * Tests that an exception is thrown when a non-existant file is
	 * passed to the QuestionReader.
	 */
	public void testMissingFile() {
		QuestionReader reader = null;
		try {
			reader = new QuestionReader("question0.xml");
			fail("QuestionReaderTest.testMissingFile() - a missing file is processed");
		} catch (QuestionException e) {
			assertNull("QuestionReaderTest.testMissingFile() - the QuestionReader object is not null (and was therefore succesfully constructed, which shouldn't happen with a missing file).", reader);
		}
	}
	
	/**
	 * Tests that a valid file is read correctly by the QuestionReader
	 */
	public void testValidQuestionFile() {
		QuestionReader reader = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			
			//Check that the lengths of the collections are correct
			assertEquals("QuestionReaderTest.testValidQuestionFile - incorrect number of standard questions - expected 3", Q1_NUM_STD_QUESTIONS, stdQuestions.size());
			assertEquals("QuestionReaderTest.testValidQuestionFile - incorrect number of elementary questions - expected 4", Q1_NUM_ELEM_QUESTIONS, elemQuestions.size());
			assertEquals("QuestionReaderTest.testValidQuestionFile - incorrect number of advanced questions - expected 2", Q1_NUM_ADV_QUESTIONS, advQuestions.size());
			
			//Check that the information in one of the elements (standard question 2) is correct
			StandardQuestion sq = stdQuestions.get(1);
			assertEquals("QuestionReaderTest.testValidQuestionFile - contents of question don't match expected contents", "Standard Question 2", sq.getQuestion());
			assertEquals("QuestionReaderTest.testValidQuestionFile - contents of question don't match expected contents", "Choice a", sq.getChoiceA());
			assertEquals("QuestionReaderTest.testValidQuestionFile - contents of question don't match expected contents", "Choice b", sq.getChoiceB());
			assertEquals("QuestionReaderTest.testValidQuestionFile - contents of question don't match expected contents", "Choice c", sq.getChoiceC());
			assertEquals("QuestionReaderTest.testValidQuestionFile - contents of question don't match expected contents", "Choice d", sq.getChoiceD());
			assertEquals("QuestionReaderTest.testValidQuestionFile - contents of question don't match expected contents", "c", sq.getAnswer());
			
		} catch (QuestionException e) {
			fail("QuestionReaderTest.testValidQuestionFile() - an exception is thrown for a valid file");
		}
	}
	
	/**
	 * Tests that a valid file is read correctly by the QuestionReader
	 * when answers are uppercase.
	 */
	public void testValidQuestionFileUppercase() {
		QuestionReader reader = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions13.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			
			//Check that the lengths of the collections are correct
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - incorrect number of standard questions - expected 3", Q1_NUM_STD_QUESTIONS, stdQuestions.size());
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - incorrect number of elementary questions - expected 4", Q1_NUM_ELEM_QUESTIONS, elemQuestions.size());
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - incorrect number of advanced questions - expected 2", Q1_NUM_ADV_QUESTIONS, advQuestions.size());
			
			//Check that the information in one of the elements (standard question 2) is correct
			StandardQuestion sq = stdQuestions.get(1);
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - contents of question don't match expected contents", "Standard Question 2", sq.getQuestion());
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - contents of question don't match expected contents", "Choice a", sq.getChoiceA());
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - contents of question don't match expected contents", "Choice b", sq.getChoiceB());
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - contents of question don't match expected contents", "Choice c", sq.getChoiceC());
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - contents of question don't match expected contents", "Choice d", sq.getChoiceD());
			assertEquals("QuestionReaderTest.testValidQuestionFileUppercase - contents of question don't match expected contents", "C", sq.getAnswer());
			
		} catch (QuestionException e) {
			fail("QuestionReaderTest.testValidQuestionFileUppercase() - an exception is thrown for a valid file");
		}
	}
	
	/**
	 * Tests that an invalid file throws the proper exception.  This test is
	 * done in a loop, which means that earlier failures may mask later failures.
	 */
	public void testInvalidQuestionFiles() {
		for (int i = MIN_QUESTIONS_FILE; i <= MAX_QUESTIONS_FILE; i++) {
			QuestionReader reader = null;
			String fileName = "questions" + i + ".xml";
			try {
				//Create the QuestionReader
				reader = new QuestionReader(fileName);
				
				fail("QuestionReaderTest.testInvalidQuestionFile() - " + fileName + " did not lead to a failure");			
			} catch (QuestionException e) {
				assertNull("QuestionReaderTest.testInvalidQuestionFile() - " + fileName + " did not lead to a failure", reader);	
			}
		}
	}

}
