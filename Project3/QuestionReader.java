import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Processes the given XML file and creates collections of
 * {@link StandardQuestion}s, {@link ElementaryQuestion}s, and {@link AdvancedQuestion}s.
 * If there is any problems when processing a questions XML file,
 * a {@link QuestionException} is thrown.
 * 
 * @author xellis
 */
public class QuestionReader {
	
	/** Name of the XML file to process */
	private String fileName;
	/** Collection of Questions */
	private QuestionList list;
	
	/**
	 * Creates a QuestionReader for the given filename and
	 * starts processing the question file.  If there is a problem
	 * processing the file, a {@link QuestionException} is thrown.
	 * @param fileName name of file to process
	 * @throws QuestionException thrown when problem processing file
	 */
	public QuestionReader(String fileName) throws QuestionException {
		this.fileName = fileName;
		
		processQuestionFile();
		
		checkQuestions();
	}
	
	/**
	 * Returns a list of {@link StandardQuestion}s.
	 * @return a list of {@link StandardQuestion}s.
	 */
	public List<StandardQuestion> getStandardQuestions() {
		return list.getStandardQuestion();
	}
	
	/**
	 * Returns a list of {@link ElementaryQuestion}s.
	 * @return a list of {@link ElementaryQuestion}s.
	 */
	public List<ElementaryQuestion> getElementaryQuestions() {
		return list.getElementaryQuestion();
	}
	
	/**
	 * Returns a list of {@link AdvancedQuestion}s.
	 * @return a list of {@link AdvancedQuestion}s.
	 */
	public List<AdvancedQuestion> getAdvancedQuestions() {
		return list.getAdvancedQuestion();
	}
	
	/**
	 * Processes the question file using the JAXB unmarshaller.  If
	 * there is a problem processing the file, a {@link QuestionException}
	 * is thrown
	 * @throws QuestionException thrown when problem processing file
	 */
	@SuppressWarnings("unchecked")
	private void processQuestionFile() throws QuestionException {
        JAXBElement<QuestionList> element = null;
        FileInputStream input = null;
        //FindBugs notification is a false positive since using try-with-resources
		try (FileInputStream intput = new FileInputStream(fileName)){
			String packageName = this.getClass().getPackage().getName();
			JAXBContext jc = JAXBContext.newInstance( packageName );
		    Unmarshaller u = jc.createUnmarshaller();
		    input = new FileInputStream(fileName);
			element = (JAXBElement<QuestionList>)u.unmarshal(input);
		} catch (FileNotFoundException e) {
			throw new QuestionException(e.getMessage());
		} catch (JAXBException e) {
			throw new QuestionException(e.getMessage());
		} catch (Exception e) {
			throw new QuestionException(e.getMessage());
		}
        this.list = element.getValue();
	}
	
	/**
	 * Checks that the questions are all valid.  This means that all questions
	 * have the required information and that the answer is 'a', 'b', 'c', 'd', 
	 * 'A', 'B', 'C', or 'D'.
	 * 
	 * @throws QuestionException if any question is invalid
	 */
	private void checkQuestions() throws QuestionException {
		List<StandardQuestion> stdQuestions = getStandardQuestions();
		
		if (stdQuestions == null) throw new QuestionException();
		
		for (int i = 0; i < stdQuestions.size(); i++) {
			checkStandardQuestion(stdQuestions.get(i));
		}
		
		List<ElementaryQuestion> elemQuestions = getElementaryQuestions();
		
		if (elemQuestions == null) throw new QuestionException();
		
		for (int i = 0; i < elemQuestions.size(); i++) {
			checkStandardQuestion(elemQuestions.get(i));
			
			if (elemQuestions.get(i).getHint() == null) throw new QuestionException();
			if (elemQuestions.get(i).getHint().length() == 0) throw new QuestionException();
		}
		
		List<AdvancedQuestion> advQuestions = getAdvancedQuestions();
		
		if (advQuestions == null) throw new QuestionException();
		
		for (int i = 0; i < advQuestions.size(); i++) {
			checkStandardQuestion(advQuestions.get(i));
			
			if (advQuestions.get(i).getComment() == null) throw new QuestionException();
			if (advQuestions.get(i).getComment().length() == 0) throw new QuestionException();
		}
	}
	
	/**
	 * Checks the parts of a {@link StandardQuestion}.  
	 * 
	 * @param q question to examine
	 * @throws QuestionException if any part of the question is invalid
	 */
	private void checkStandardQuestion(Question q) throws QuestionException {
		if (q.question == null || q.choiceA == null || q.choiceB == null || q.choiceC == null || q.choiceD == null || q.answer == null) 
			throw new QuestionException();
		if (q.question.length() == 0 || q.choiceA.length() == 0 || q.choiceB.length() == 0 || q.choiceC.length() == 0 || q.choiceD.length() == 0 || q.answer.length() == 0) 
			throw new QuestionException();
		if (q.answer.length() != 1) 
			throw new QuestionException();
		char c = q.answer.charAt(0);
		if (!(c >= 'a' && c <= 'd') && !(c >= 'A' && c <= 'D')) {
			//do nothing
			throw new QuestionException();
		}
	}

}
