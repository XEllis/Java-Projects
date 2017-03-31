import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.TestCase;

/**
 * Tests for the Question Writer class of the Question Library.
 * 
 * We are testing that the correct XML is generated when giving
 * the Writer a *Question object of when the writer makes the 
 * object.
 * 
 * @author xellis
 */
public class QuestionWriterTest extends TestCase {
	
	/** Contains all of the files that will be created by the tests */
	private String [] fileNames = {"standard1.xml", "standard2.xml", "elementary1.xml", "elementary2.xml", "advanced1.xml", "advanced2.xml", "all.xml"};
	/** Contains all of the files containing the expected result for testing */
	private String [] expectedFileNames = {"exp_standard1.xml", "exp_standard2.xml", "exp_elementary1.xml", "exp_elementary2.xml", "exp_advanced1.xml", "exp_advanced2.xml", "exp_all.xml"};
	
	/** A standard question */
	private static final String STD_QUESTION = "How many licks to reach the center of a Tootsie Pop?";
	/** A standard question choice a */
	private static final String STD_CHOICE_A = "1";
	/** A standard question choice b */
	private static final String STD_CHOICE_B = "2";
	/** A standard question choice c */
	private static final String STD_CHOICE_C = "3";
	/** A standard question choice d */
	private static final String STD_CHOICE_D = "the world may never know";
	/** A standard question answer */
	private static final String STD_ANSWER = "d";
	
	/** An elementary question */
	private static final String ELEM_QUESTION = "How much wood can a woodchuck chuck?";
	/** An elementary question choice a */
	private static final String ELEM_CHOICE_A = "1 bushel";
	/** An elementary question choice b */
	private static final String ELEM_CHOICE_B = "2 bushels";
	/** An elementary question choice c */
	private static final String ELEM_CHOICE_C = "3 bushels";
	/** An elementary question choice d */
	private static final String ELEM_CHOICE_D = "4 bushels";
	/** An elementary question answer */
	private static final String ELEM_ANSWER = "b";
	/** An elementary question hint */
	private static final String ELEM_HINT = "Greater than 1 bushel and less than 3 bushels.";
	
	/** An advanced question */
	private static final String ADV_QUESTION = "What is the answer to life, the universe, and everything?";
	/** An advanced question choice a */
	private static final String ADV_CHOICE_A = "42";
	/** An advanced question choice b */
	private static final String ADV_CHOICE_B = "nothing";
	/** An advanced question choice c */
	private static final String ADV_CHOICE_C = "infinity";
	/** An advanced question choice d */
	private static final String ADV_CHOICE_D = "7";
	/** An advanced question answer */
	private static final String ADV_ANSWER = "a";
	/** An advanced question comment */
	private static final String ADV_COMMENT = "Now, what is the question?";
	
	/**
	 * Sets up the project for each test case by deleting any of the 
	 * test files that were generated from earlier runs of the testing
	 * program.  Not deleting these files will cause test failures.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		//Delete all files created by the tests
		//The files may not exist depending on which test was run last
		
		for (int i = 0; i < fileNames.length; i++) {
			File f = new File(fileNames[i]);
			if (f.exists()) {
				f.delete();
			}
		}
	}
	
	/**
	 * Compares the contents of the two files.  Returns true if the contents
	 * are exactly the same.  Returns false if the contents are not the
	 * same or if there are any errors while processing.
	 * 
	 * @param expectedFile the file with the expected results
	 * @param actualFile the file with the actual results
	 * @return true if the files are exactly the same and false otherwise
	 */
	private boolean compareFiles(File expectedFile, File actualFile) {
		try {
			Scanner expectedIn = new Scanner(expectedFile);
			String expected = "";
			while (expectedIn.hasNextLine()) {
				expected += expectedIn.nextLine();
			}
			expectedIn.close();
			
			Scanner actualIn = new Scanner(actualFile);
			String actual = "";
			while (actualIn.hasNextLine()) {
				actual += actualIn.nextLine();
			}
			actualIn.close();
			return expected.equals(actual);
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	/**
	 * Tests that a {@link StandardQuestion} created by the user
	 * and added to the collection via the addStandardQuestion() method
	 * is written successfully to the XML file.
	 */
	public void testQuestionWriterAddStandardQuestion() {
		StandardQuestion q = new StandardQuestion();
		q.setQuestion(STD_QUESTION);
		q.setChoiceA(STD_CHOICE_A);
		q.setChoiceB(STD_CHOICE_B);
		q.setChoiceC(STD_CHOICE_C);
		q.setChoiceD(STD_CHOICE_D);
		q.setAnswer(STD_ANSWER);
		
		String actualFileName = fileNames[0];
		String expectedFileName = expectedFileNames[0];
		
		//Check that the file doesn't exist
		File actualFile = new File(actualFileName);
		File expectedFile = new File(expectedFileName);
		assertFalse("QuestionWriter.testQuestionWriterAddStandardQuestion() - file exists, can't continue test", actualFile.exists());
		assertTrue("QuestionWriter.testQuestionWriterAddStandardQuestion() - expected file doesn't exist, can't continue test", expectedFile.exists());
		
		QuestionWriter writer = null;
		try {			
			writer = new QuestionWriter(actualFileName);
			writer.addStandardQuestion(q);
			writer.marshal();
			
			assertTrue("QuestionWriter.testQuestionWriterAddStandardQuestion() - file doesn't exist, can't continue test", actualFile.exists());
			
			assertTrue("QuestionWriter.testQuestionWriterAddStandardQuestion() - file doesn't match expected.", compareFiles(expectedFile, actualFile));
			
		} catch (QuestionException e) {
			fail("QuestionWriterTest.testQuestionWriterAddStandardQuestion() - unexpected exception");
		}
	}
	
	/**
	 * Tests that a {@link ElementaryQuestion} created by the user
	 * and added to the collection via the addElementaryQuestion() method
	 * is written successfully to the XML file.
	 */
	public void testQuestionWriterAddElementaryQuestion() {
		ElementaryQuestion q = new ElementaryQuestion();
		q.setQuestion(ELEM_QUESTION);
		q.setChoiceA(ELEM_CHOICE_A);
		q.setChoiceB(ELEM_CHOICE_B);
		q.setChoiceC(ELEM_CHOICE_C);
		q.setChoiceD(ELEM_CHOICE_D);
		q.setAnswer(ELEM_ANSWER);
		q.setHint(ELEM_HINT);
		
		String actualFileName = fileNames[2];
		String expectedFileName = expectedFileNames[2];
		
		//Check that the file doesn't exist
		File actualFile = new File(actualFileName);
		File expectedFile = new File(expectedFileName);
		assertFalse("QuestionWriter.testQuestionWriterAddElementaryQuestion() - file exists, can't continue test", actualFile.exists());
		assertTrue("QuestionWriter.testQuestionWriterAddElementaryQuestion() - expected file doesn't exist, can't continue test", expectedFile.exists());
		
		QuestionWriter writer = null;
		try {			
			writer = new QuestionWriter(actualFileName);
			writer.addElementaryQuestion(q);
			writer.marshal();
			
			assertTrue("QuestionWriter.testQuestionWriterAddElementaryQuestion() - file doesn't exist, can't continue test", actualFile.exists());
			
			assertTrue("QuestionWriter.testQuestionWriterAddElementaryQuestion() - file doesn't match expected.", compareFiles(expectedFile, actualFile));
			
		} catch (QuestionException e) {
			fail("QuestionWriterTest.testQuestionWriterAddElementaryQuestion() - unexpected exception");
		}
	}
	
	/**
	 * Tests that a {@link AdvancedQuestion} created by the user
	 * and added to the collection via the addAdvancedQuestion() method
	 * is written successfully to the XML file.
	 */
	public void testQuestionWriterAddAdvancedQuestion() {
		AdvancedQuestion q = new AdvancedQuestion();
		q.setQuestion(ADV_QUESTION);
		q.setChoiceA(ADV_CHOICE_A);
		q.setChoiceB(ADV_CHOICE_B);
		q.setChoiceC(ADV_CHOICE_C);
		q.setChoiceD(ADV_CHOICE_D);
		q.setAnswer(ADV_ANSWER);
		q.setComment(ADV_COMMENT);
		
		String actualFileName = fileNames[4];
		String expectedFileName = expectedFileNames[4];
		
		//Check that the file doesn't exist
		File actualFile = new File(actualFileName);
		File expectedFile = new File(expectedFileName);
		assertFalse("QuestionWriter.testQuestionWriterAddAdvancedQuestion() - file exists, can't continue test", actualFile.exists());
		assertTrue("QuestionWriter.testQuestionWriterAddAdvancedQuestion() - expected file doesn't exist, can't continue test", expectedFile.exists());
		
		QuestionWriter writer = null;
		try {			
			writer = new QuestionWriter(actualFileName);
			writer.addAdvancedQuestion(q);
			writer.marshal();
			
			assertTrue("QuestionWriter.testQuestionWriterAddAdvancedQuestion() - file doesn't exist, can't continue test", actualFile.exists());
			
			assertTrue("QuestionWriter.testQuestionWriterAddAdvancedQuestion() - file doesn't match expected.", compareFiles(expectedFile, actualFile));
			
		} catch (QuestionException e) {
			fail("QuestionWriterTest.testQuestionWriterAddAdvancedQuestion() - unexpected exception");
		}
	}
	
	/**
	 * Tests that a {@link StandardQuestion} is successfully created
	 * when the question information is passed to the makeStandardQuestion() method
	 * is written successfully to the XML file.
	 */
	public void testQuestionWriterMakeStandardQuestion() {		
		String actualFileName = fileNames[1];
		String expectedFileName = expectedFileNames[1];
		
		//Check that the file doesn't exist
		File actualFile = new File(actualFileName);
		File expectedFile = new File(expectedFileName);
		assertFalse("QuestionWriter.testQuestionWriterMakeStandardQuestion() - file exists, can't continue test", actualFile.exists());
		assertTrue("QuestionWriter.testQuestionWriterMakeStandardQuestion() - expected file doesn't exist, can't continue test", expectedFile.exists());
		
		QuestionWriter writer = null;
		try {			
			writer = new QuestionWriter(actualFileName);
			
			writer.makeStandardQuestion(STD_QUESTION, STD_CHOICE_A, STD_CHOICE_B, STD_CHOICE_C, STD_CHOICE_D, STD_ANSWER);

			writer.marshal();
			
			assertTrue("QuestionWriter.testQuestionWriterMakeStandardQuestion() - file doesn't exist, can't continue test", actualFile.exists());
			
			assertTrue("QuestionWriter.testQuestionWriterMakeStandardQuestion() - file doesn't match expected.", compareFiles(expectedFile, actualFile));
			
		} catch (QuestionException e) {
			fail("QuestionWriterTest.testQuestionWriterMakeStandardQuestion() - unexpected exception");
		}
	}
	/**
	 * Tests that a {@link ElementaryQuestion} is successfully created
	 * when the question information is passed to the makeElementaryQuestion() method
	 * is written successfully to the XML file.
	 */
	public void testQuestionWriterMakeElementaryQuestion() {
		String actualFileName = fileNames[3];
		String expectedFileName = expectedFileNames[3];
		
		//Check that the file doesn't exist
		File actualFile = new File(actualFileName);
		File expectedFile = new File(expectedFileName);
		assertFalse("QuestionWriter.testQuestionWriterMakeElementaryQuestion() - file exists, can't continue test", actualFile.exists());
		assertTrue("QuestionWriter.testQuestionWriterMakeElementaryQuestion() - expected file doesn't exist, can't continue test", expectedFile.exists());
		
		QuestionWriter writer = null;
		try {			
			writer = new QuestionWriter(actualFileName);
			
			writer.makeElementaryQuestion(ELEM_QUESTION, ELEM_CHOICE_A, ELEM_CHOICE_B, ELEM_CHOICE_C, ELEM_CHOICE_D, ELEM_ANSWER, ELEM_HINT);

			writer.marshal();
			
			assertTrue("QuestionWriter.testQuestionWriterMakeElementaryQuestion() - file doesn't exist, can't continue test", actualFile.exists());
			
			assertTrue("QuestionWriter.testQuestionWriterMakeElementaryQuestion() - file doesn't match expected.", compareFiles(expectedFile, actualFile));
			
		} catch (QuestionException e) {
			fail("QuestionWriterTest.testQuestionWriterMakeElementaryQuestion() - unexpected exception");
		}
	}
	
	/**
	 * Tests that a {@link AdvancedQuestion} is successfully created
	 * when the question information is passed to the makeAdvancedQuestion() method
	 * is written successfully to the XML file.
	 */
	public void testQuestionWriterMakeAdvancedQuestion() {
		String actualFileName = fileNames[5];
		String expectedFileName = expectedFileNames[5];
		
		//Check that the file doesn't exist
		File actualFile = new File(actualFileName);
		File expectedFile = new File(expectedFileName);
		assertFalse("QuestionWriter.testQuestionWriterMakeAdvancedQuestion() - file exists, can't continue test", actualFile.exists());
		assertTrue("QuestionWriter.testQuestionWriterMakeAdvancedQuestion() - expected file doesn't exist, can't continue test", expectedFile.exists());
		
		QuestionWriter writer = null;
		try {			
			writer = new QuestionWriter(actualFileName);
			
			writer.makeAdvancedQuestion(ADV_QUESTION, ADV_CHOICE_A, ADV_CHOICE_B, ADV_CHOICE_C, ADV_CHOICE_D, ADV_ANSWER, ADV_COMMENT);

			writer.marshal();
			
			assertTrue("QuestionWriter.testQuestionWriterMakeAdvancedQuestion() - file doesn't exist, can't continue test", actualFile.exists());
			
			assertTrue("QuestionWriter.testQuestionWriterMakeAdvancedQuestion() - file doesn't match expected.", compareFiles(expectedFile, actualFile));
			
		} catch (QuestionException e) {
			fail("QuestionWriterTest.testQuestionWriterMakeAdvancedQuestion() - unexpected exception");
		}
	}

	/**
	 * Tests that a QuestionWriter writes when multiple questions are created
	 */
	public void testQuestionWriterAllQuestions() {
		StandardQuestion sq = new StandardQuestion();
		sq.setQuestion(STD_QUESTION);
		sq.setChoiceA(STD_CHOICE_A);
		sq.setChoiceB(STD_CHOICE_B);
		sq.setChoiceC(STD_CHOICE_C);
		sq.setChoiceD(STD_CHOICE_D);
		sq.setAnswer(STD_ANSWER);
		
		ElementaryQuestion eq = new ElementaryQuestion();
		eq.setQuestion(ELEM_QUESTION);
		eq.setChoiceA(ELEM_CHOICE_A);
		eq.setChoiceB(ELEM_CHOICE_B);
		eq.setChoiceC(ELEM_CHOICE_C);
		eq.setChoiceD(ELEM_CHOICE_D);
		eq.setAnswer(ELEM_ANSWER);
		eq.setHint(ELEM_HINT);
		
		String actualFileName = fileNames[6];
		String expectedFileName = expectedFileNames[6];
		
		//Check that the file doesn't exist
		File actualFile = new File(actualFileName);
		File expectedFile = new File(expectedFileName);
		assertFalse("QuestionWriter.testQuestionWriterAllQuestions() - file exists, can't continue test", actualFile.exists());
		assertTrue("QuestionWriter.testQuestionWriterAllQuestions() - expected file doesn't exist, can't continue test", expectedFile.exists());
		
		QuestionWriter writer = null;
		try {			
			writer = new QuestionWriter(actualFileName);
			
			writer.addElementaryQuestion(eq);
			writer.addStandardQuestion(sq);
			writer.makeAdvancedQuestion(ADV_QUESTION, ADV_CHOICE_A, ADV_CHOICE_B, ADV_CHOICE_C, ADV_CHOICE_D, ADV_ANSWER, ADV_COMMENT);

			writer.marshal();
			
			assertTrue("QuestionWriter.testQuestionWriterAllQuestions() - file doesn't exist, can't continue test", actualFile.exists());
			
			assertTrue("QuestionWriter.testQuestionWriterAllQuestion() - file doesn't match expected.", compareFiles(expectedFile, actualFile));
			
		} catch (QuestionException e) {
			fail("QuestionWriterTest.testQuestionWriterAllQuestion() - unexpected exception");
		}
	}

}
