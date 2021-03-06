//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.25 at 03:18:58 PM EDT 
//

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the edu.ncsu.csc216.question_library package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * @author JDOM Library
 */
@XmlRegistry
public class ObjectFactory {

	/** Name of the Question List*/
	private final static QName QUESTIONS_QNAME = new QName("", "Questions");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: edu.ncsu.csc216.question_library
	 * 
	 */
	public ObjectFactory() {
		//Empty constructor created by JDOM
	}

	/**
	 * Create an instance of {@link QuestionList }
	 * @return a new QuestionList
	 */
	public QuestionList createQuestionList() {
		return new QuestionList();
	}

	/**
	 * Create an instance of {@link ElementaryQuestion }
	 * @return a new Elementary Questions
	 */
	public ElementaryQuestion createElementaryQuestion() {
		return new ElementaryQuestion();
	}

	/**
	 * Create an instance of {@link AdvancedQuestion }
	 * @return a new AdvancedQuestion
	 */
	public AdvancedQuestion createAdvancedQuestion() {
		return new AdvancedQuestion();
	}

	/**
	 * Create an instance of {@link StandardQuestion }
	 * @return a new StandardQuestion
	 */
	public StandardQuestion createStandardQuestion() {
		return new StandardQuestion();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link QuestionList }
	 * {@code >}
	 * @param value creates a new list of questions with the given value
	 * @return a list of Questions in a JAXBElement collection
	 */
	@XmlElementDecl(namespace = "", name = "Questions")
	public JAXBElement<QuestionList> createQuestions(QuestionList value) {
		return new JAXBElement<QuestionList>(QUESTIONS_QNAME,
				QuestionList.class, null, value);
	}

}
