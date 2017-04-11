package mainpack;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import objects.Dialog;
import objects.Hero;
import objects.Point;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML implements Params{
	private Vector<Point> v_point = new Vector<Point>();
	private Hashtable<String, Dialog> ht_dialog = new Hashtable<String, Dialog>();
	private Vector<Hero> v_hero = new Vector<Hero>();;
	
	public ReadXML(){};
	
	public Vector<Point> getv_point(){
		return v_point;
	}
	public Hashtable<String, Dialog> getht_dialog(){
		return ht_dialog;
	}
	public Vector<Hero> getv_hero(){
		return v_hero;
	}
	
	public void readPointXML(){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
		Document document = null;

		try { 
			//DOM parser instance 
			DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
			//parse an XML file into a DOM tree 
			document = builder.parse(new File(assetsFolder+"point.xml")); 
		} catch (ParserConfigurationException e) { 
			e.printStackTrace();  
		} catch (SAXException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		//get root element 
		Element rootElement = document.getDocumentElement(); 

//		//traverse child elements 
//		NodeList nodes = rootElement.getChildNodes(); 
//		for (int i=0; i < nodes.getLength(); i++) 
//		{
//			Node node = nodes.item(i);
//			if (node.getNodeType() == Node.ELEMENT_NODE) {   
//				Element child = (Element) node;
//				//process child element 
//			} 
//		}

		NodeList nodeList = rootElement.getElementsByTagName("point");
		if(nodeList != null)
		{
			for (int i = 0 ; i < nodeList.getLength(); i++)
			{
				Element element = (Element)nodeList.item(i);
				Point point = new Point(element.getAttribute("id"),
						element.getAttribute("map"),
						element.getAttribute("chapter"),
						element.getAttribute("point"),
						element.getAttribute("before_dialog"),
						element.getAttribute("within_dialog"),
						element.getAttribute("after_dialog")
						);
				//TODO
				if (element.getAttribute("before_dialog") != null ||
						!element.getAttribute("before_dialog").equals("") ||
						element.getAttribute("within_dialog") != null ||
						!element.getAttribute("within_dialog").equals("") ||
						element.getAttribute("after_dialog") != null ||
						!element.getAttribute("after_dialog").equals("")){
					v_point.add(point);
				}
			}
		}
	}
	
	public void readdialogXML(){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
		Document document = null;

		try { 
			//DOM parser instance 
			DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
			//parse an XML file into a DOM tree 
			document = builder.parse(new File(assetsFolder+"dialog.xml")); 
		} catch (ParserConfigurationException e) { 
			e.printStackTrace();  
		} catch (SAXException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		//get root element 
		Element rootElement = document.getDocumentElement();

		NodeList nodeList = rootElement.getElementsByTagName("dialog");
		if(nodeList != null)
		{
			for (int i = 0 ; i < nodeList.getLength(); i++)
			{
				Element element = (Element)nodeList.item(i);
				if (element.getAttribute("id")==null || 
						element.getAttribute("id").equals("")){
					
				}
				else{
					if (ht_dialog.containsKey(element.getAttribute("id"))){
						Dialog dialog = ht_dialog.get(element.getAttribute("id"));
						dialog.putText(element.getAttribute("name"),
								element.getAttribute("text"),
								element.getAttribute("sprite"));
					}
					else{
						Dialog dialog = new Dialog(element.getAttribute("id"),
							element.getAttribute("title"),
							element.getAttribute("name"),
							element.getAttribute("text"),
							element.getAttribute("sprite")
							);
						ht_dialog.put(dialog.getID(), dialog);
					}
				}
			}
		}
	}
	
	public void readheroXML(){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
		Document document = null;

		try { 
			//DOM parser instance 
			DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
			//parse an XML file into a DOM tree 
			document = builder.parse(new File(assetsFolder+"hero.xml")); 
		} catch (ParserConfigurationException e) { 
			e.printStackTrace();  
		} catch (SAXException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		//get root element 
		Element rootElement = document.getDocumentElement();

		NodeList nodeList = rootElement.getElementsByTagName("hero");
		if(nodeList != null)
		{
			for (int i = 0 ; i < nodeList.getLength(); i++)
			{
				Element element = (Element)nodeList.item(i);
				Hero hero = new Hero(element.getAttribute("name"),
						element.getAttribute("comment")
						);
				v_hero.add(hero);
			}
		}
	}
}
