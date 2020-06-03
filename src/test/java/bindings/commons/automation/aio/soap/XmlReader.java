package bindings.commons.automation.aio.soap;

import com.ipfdigital.automation.aio.soap.exceptions.XMLTransformationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;

public class XmlReader {

  private static final Logger LOGGER = LogManager.getLogger(XmlReader.class);
  private static final String REMOVE_NEWLINES = "\n|\r";
  private static final String EMPTY_STRING = "";
  private final Document document;

  private XmlReader(final String document) {
    this.document = convertStringToXml(document);
  }

  public static XmlReader withParsedXml(final String xml) {
    return new XmlReader(xml);
  }

  private Document convertStringToXml(final String document) {
    try {
      return DocumentBuilderFactory.newInstance().newDocumentBuilder()
          .parse(new InputSource(new StringReader(document)));
    } catch (final ParserConfigurationException | SAXException | IOException e) {
      LOGGER.error("PARSING XML HAS FAILED FOR DOCUMENT {}", document);
      throw new XMLTransformationException(e);
    }
  }

  public String retrieveTextFromNode(final String xpath) {
    try {
      final Node node = (Node) XPathFactory.newInstance().newXPath().compile(xpath)
          .evaluate(this.document, XPathConstants.NODE);
      return node.getTextContent().replaceAll(REMOVE_NEWLINES, EMPTY_STRING)
          .trim();
    } catch (final XPathExpressionException e) {
      LOGGER.error("PARSING XML NODE HAS FAILED WITH {} {}", e.getClass(), e.getMessage());
      throw new XMLTransformationException(e);
    }
  }

}
