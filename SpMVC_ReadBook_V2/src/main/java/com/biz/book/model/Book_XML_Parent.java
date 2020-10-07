package com.biz.book.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// XML파일중 가장 바깥쪽에 싸고 있는 것이 rss이다.
@XmlRootElement(name="rss")
public class Book_XML_Parent {

	@XmlElement(name="channel")
	public Book_XML_Channel channel;
}
