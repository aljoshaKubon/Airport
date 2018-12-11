import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {
	private DocumentBuilderFactory _dFactory;
	private DocumentBuilder _dBuilder;	
	private TransformerFactory _tFactory;
	private Transformer _transformer;
	private DOMImplementation _domImplem;
	private DocumentType _docType;
	private DOMSource _domSource;
	private StreamResult _streamRes;
	private Document _doc;
	
	public XMLManager(String input) {
		try {
			_dFactory = DocumentBuilderFactory.newInstance();
			_dBuilder = _dFactory.newDocumentBuilder();
			_tFactory = TransformerFactory.newInstance();
			
			_doc = _dBuilder.parse(new File(input));
			
			_transformer = _tFactory.newTransformer();
			_domImplem = _doc.getImplementation();
			_docType = _domImplem.createDocumentType("doctype", "SYSTEM", "Data.dtd");
			_domSource = new DOMSource(_doc);
			_streamRes = new StreamResult(new File(input));
			
			_transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			_transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			_transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			_transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, _docType.getPublicId());
			_transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, _docType.getSystemId());
			
		} catch (ParserConfigurationException | SAXException | IOException | TransformerConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	protected void loadAirports(ArrayList<Airport> airPorts) {
		Element root = _doc.getDocumentElement();
		
		for(int n = 0; n < root.getChildNodes().getLength(); n++) {
			if(root.getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE) {
				Node tempNode = root.getChildNodes().item(n);
				
				String id = tempNode.getAttributes().getNamedItem("ID").getNodeValue();	
				double x = Double.parseDouble(tempNode.getAttributes().getNamedItem("x").getNodeValue());		
				double y = Double.parseDouble(tempNode.getAttributes().getNamedItem("y").getNodeValue());		
				double size = Double.parseDouble(tempNode.getAttributes().getNamedItem("SIZE").getNodeValue());
				
				Airport airport = new Airport(id, x, y, size, size);
				airPorts.add( airport );
			}
		}
	}

}
