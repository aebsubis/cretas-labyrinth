package utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class XmlNode {
	public static final int TEXT_NODE = 0;
	public static final int ELEMENT_NODE = 1;
 
	public int nodeType = 0;
	public String nodeName = null;
	public String nodeValue = null;
	public Vector children = null;
	public Hashtable attributes = null;
 
	public XmlNode(int nodeType) {
		this.nodeType = nodeType;
		this.children = new Vector();
		this.attributes = new Hashtable();
	}
	
	public String[] getAttributeNames() {
		String[] names = new String[attributes.size()];
		Enumeration e = attributes.keys();
 
		int i = 0;
		while(e.hasMoreElements()) {
			names[i] = (String)e.nextElement();
			i++;
		}
		return names;
	}
	
	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}
	
	public String getAttribute(String key) {
		return (String)attributes.get(key);
	}
 
	public void addChild(XmlNode child) {
		this.children.addElement(child);
	}
	
	public void dumpXML(int deep)
	{
		for(int i = 0; i < deep; i++)
		{
			System.out.print(" ");
		}
		System.out.print(this.nodeName + " - ");
	 
		if(this.nodeValue != null)
		{
			System.out.print("(" + this.nodeValue + ") - ");
		}
		String[] attributes = this.getAttributeNames();
	 
		for(int i = 0; i < attributes.length; i++)
		{
			System.out.print(attributes[i] + ": " + this.getAttribute(attributes[i]) + ", ");
		}
	 
		System.out.println();
	 
		for(int i = 0; i < this.children.size(); i++)
		{
			XmlNode n = (XmlNode)this.children.elementAt(i);
			n.dumpXML(deep + 1);
		}
	}
}