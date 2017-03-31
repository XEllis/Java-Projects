import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * <p>Library for creating a questions XML file for use in the 
 * Intelligent Tutor application.  If there is an error creating
 * the file, a {@link QuestionException} is thrown.</p>
 * 
 * <p>To use QuestionWriter, first create the object with the file
 * name that you want to write to.  Then add all of the questions
 * that you want to the {@link QuestionList} via the add*Question() 
 * and make*Question() methods.  When you are done adding questions,
 * call the marshal() method.  Calling the marshal() again will
 * delete the file and rewrite with the data in the {@link QuestionList}.
 * 
 * @author xellis
 */
public class QuestionWriter {
	
	/** Name of file to create and write to */
	private String fileName;
	/** List of questions to write to file */
	private QuestionList list;
	/** ObjectFactory for creating objects that can be written to the file */
	private ObjectFactory of;
	
	/**
	 * Creates a {@link QuestionList} and {@link ObjectFactory} for writing
	 * questions to the given file.  
	 * @param fileName to write questions to in XML
	 * @throws QuestionException thrown if problem writing to file
	 */
	public QuestionWriter(String fileName) throws QuestionException {
		this.fileName = fileName;
		
		of = new ObjectFactory();
		list = of.createQuestionList();
	}
	
	/**
	 * Adds a {@link StandardQuestion} to the {@link QuestionList}
	 * @param q question to add to the list
	 */
	public void addStandardQuestion(StandardQuestion q) {
		list.getStandardQuestion().add(q);
	}
	
	/**
	 * Adds an {@link ElementaryQuestion} to the {@link QuestionList}
	 * @param q question to add to the list
	 */
	public void addElementaryQuestion(ElementaryQuestion q) {
		list.getElementaryQuestion().add(q);
	}
	
	/**
	 * Adds an {@link AdvancedQuestion} to the {@link QuestionList}
	 * @param q question to add to the list
	 */
	public void addAdvancedQuestion(AdvancedQuestion q) {
		list.getAdvancedQuestion().add(q);
	}
	
	/**
	 * Makes a {@link StandardQuestion} with the given parameters and adds
	 * the question to the {@link QuestionList}
	 * @param question question text
	 * @param a choice a text
	 * @param b choice b text
	 * @param c choice c text
	 * @param d choice d text
	 * @param answer answer value
	 */
	public void makeStandardQuestion(String question, String a, String b, String c, String d, String answer) {
		StandardQuestion s = of.createStandardQuestion();
		s.setQuestion(question);
		s.setChoiceA(a);
		s.setChoiceB(b);
		s.setChoiceC(c);
		s.setChoiceD(d);
		s.setAnswer(answer);
		list.getStandardQuestion().add(s);
	}
	
	/**
	 * Makes a {@link ElementaryQuestion} with the given parameters and adds
	 * the question to the {@link QuestionList}
	 * @param question question text
	 * @param a choice a text
	 * @param b choice b text
	 * @param c choice c text
	 * @param d choice d text
	 * @param answer answer value
	 * @param hint question's hint
	 */
	public void makeElementaryQuestion(String question, String a, String b, String c, String d, String answer, String hint) {
		ElementaryQuestion s = of.createElementaryQuestion();
		s.setQuestion(question);
		s.setChoiceA(a);
		s.setChoiceB(b);
		s.setChoiceC(c);
		s.setChoiceD(d);
		s.setAnswer(answer);
		s.setHint(hint);
		list.getElementaryQuestion().add(s);
	}
	
	/**
	 * Makes a {@link AdvancedQuestion} with the given parameters and adds
	 * the question to the {@link QuestionList}
	 * @param question question text
	 * @param a choice a text
	 * @param b choice b text
	 * @param c choice c text
	 * @param d choice d text
	 * @param answer answer value
	 * @param comment congratulatory comment for answering the question correctly
	 */
	public void makeAdvancedQuestion(String question, String a, String b, String c, String d, String answer, String comment) {
		AdvancedQuestion s = of.createAdvancedQuestion();
		s.setQuestion(question);
		s.setChoiceA(a);
		s.setChoiceB(b);
		s.setChoiceC(c);
		s.setChoiceD(d);
		s.setAnswer(answer);
		s.setComment(comment);
		list.getAdvancedQuestion().add(s);
	}
	
	/**
	 * Creates the XML file and writes the question in the {@link QuestionList} 
	 * to the file.
	 * @throws QuestionException if the file cannot be created or written to
	 */
	public void marshal() throws QuestionException {
		try {
			JAXBElement<QuestionList> l = of.createQuestions(list);
			String packageName = this.getClass().getPackage().getName();
			JAXBContext jc = JAXBContext.newInstance(packageName);
			Marshaller m = jc.createMarshaller();
			
			PrintStream out = new PrintStream(new File(fileName));
			m.marshal(l, out);
		} catch (JAXBException jbe) {
			throw new QuestionException(jbe.getMessage());
		} catch (FileNotFoundException e) {
			throw new QuestionException(e.getMessage());
		}
	}
}
