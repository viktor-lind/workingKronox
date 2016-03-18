package kronoxtest;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class Forklaringstexter {

	public static void main(String[] args)
    {
		ArrayList<String> aList = new ArrayList<String>();
		ArrayList<String> time = new ArrayList<String>();
		String startTid = null;
		String slutTid = null;
		String sign = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("http://schema.mah.se/setup/jsp/SchemaXML.jsp?startDatum=idag&intervallTyp=m&intervallAntal=6&forklaringar=true&sokMedAND=false&sprak=SV&resurser=l.OR%3AB532%2Cl.OR%3AB533%2Cl.OR%3AB534%2Cl.OR%3AB535%2Cl.OR%3AB536%2Cl.OR%3AB537%2Cl.OR%3AE531%2Cl.OR%3AE532%2Cl.OR%3AE533%2Cl.OR%3AE534%2Cl.OR%3AE535%2Cl.OR%3AE536%2Cl.OR%3AE537%2Cl.OR%3AE538%2Cl.OR%3AE539%2Cl.OR%3AE540%2Cl.OR%3AE541%2C");
			NodeList bookings = doc.getElementsByTagName("schemaPost");
			for(int i = 0; i < bookings.getLength(); i++){
				Node p = bookings.item(i);
				if(p.getNodeType() == Node.ELEMENT_NODE){
					Element doc1 = (Element) p;
				NodeList items2 = doc1.getElementsByTagName("bokadeDatum");	            
	            for (int t = 0; t < items2.getLength(); t++)
	            {
	                Node n1 = items2.item(t);
	                if (n1.getNodeType() == Node.ELEMENT_NODE) {

	        			Element eElement = (Element) n1;
	        			Node node3 = eElement.getFirstChild();
	        			Node node4 = node3.getAttributes().getNamedItem("startTid");
	        			Node node5 = node3.getAttributes().getNamedItem("slutTid");
	        			/**System.out.println(node4.getNodeValue() + " -  " + node5.getNodeValue());*/
	        			System.out.println(node4.getNodeValue() + node5.getNodeValue());
	        			startTid = node4.getNodeValue();
	        			slutTid = node5.getNodeValue();
	        			
	        			
	                }
	            }
				}
				if(p.getNodeType() == Node.ELEMENT_NODE){
					Element booking = (Element) p;
					NodeList resource = booking.getElementsByTagName("resursNod");
					for(int j = 0; j < resource.getLength(); j++){
						Node n = resource.item(j);
						if(n.getNodeType() == Node.ELEMENT_NODE){
							Element classType = (Element) n;
							String id = classType.getAttribute("resursTypId");
							NodeList classResource = classType.getElementsByTagName("resursId");
							for(int k = 0; k < classResource.getLength(); k++){
								Node specifiedClass = classResource.item(k);
								if(specifiedClass.getNodeType() == Node.ELEMENT_NODE){
									Element name = (Element) specifiedClass;
									
									if(id.contains("RESURSER_LOKALER")){
										name.getTextContent();
										
									}
									if(id.contains("RESURSER_SIGNATURER")){
										sign = name.getTextContent();
										
									}
									
								}
								
							}
						}
					}
					}
				aList.add(startTid + "-" + slutTid + " : " + sign);
			
			}
		
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
		for(int i = 0; i < aList.size(); i++){
			System.out.println(aList.get(i));
		}
    }
	

}
