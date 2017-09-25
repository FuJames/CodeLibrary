package code.library.xml;

import code.library.common.exception.XmlParserException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by fuqianzhong on 17/9/12.
 * 使用javax.xml包里的组件来解析xml,
 * mybatis底层使用DOM来解析xml,参考:SqlSessionFactory.build
 */
public class DomXmlParser {

    private Document document;

    private XPath xpath;

    public DomXmlParser(String xmlPath){
        XPathFactory factory = XPathFactory.newInstance();
        this.xpath = factory.newXPath();
        this.document = createDocument(xmlPath);
    }

    public Node evalNode(String expression) {
        return evalNode(document, expression);
    }

    public Node evalNode(Object root, String expression) {
        Node node = (Node) evaluate(expression, root, XPathConstants.NODE);
        return node;
    }

    private Object evaluate(String expression, Object root, QName returnType) {
        try {
            return xpath.evaluate(expression, root, returnType);
        } catch (Exception e) {
            throw new XmlParserException("Error evaluating XPath.  Cause: " + e, e);
        }
    }

    private Document createDocument(String xmlPath){
        try {
            //1. 读取xml文件流
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(xmlPath);
            InputSource inputSource = new InputSource(inputStream);
            //2. 创建DocumentFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setValidating(true);
            factory.setNamespaceAware(false);
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(false);
            factory.setCoalescing(false);
            factory.setExpandEntityReferences(true);
            //3. 创建DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            return builder.parse(inputSource);
        } catch (Exception e) {
            throw new XmlParserException("Error creating document instance.  Cause: " + e, e);
        }
    }

    public Properties parseAttributes(Node n) {
        Properties attributes = new Properties();
        NamedNodeMap attributeNodes = n.getAttributes();
        if (attributeNodes != null) {
            for (int i = 0; i < attributeNodes.getLength(); i++) {
                Node attribute = attributeNodes.item(i);
                String value = attribute.getNodeValue();
                attributes.put(attribute.getNodeName(), value);
            }
        }
        return attributes;
    }


}
