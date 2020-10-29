package com.biz.ems.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.biz.ems.model.EmsVO;

@Service
public class NaverMailSendService {

	@Autowired
	private JavaMailSender xMail;
	
	public void sendMail(EmsVO emsVO) {
		
		String from_email = emsVO.getFrom_email();
		String to_email = emsVO.getTo_email();
		/*
		 * Mime Type
		 * 인터넷 TCP/IP를 통해서 주고받는 문서(파일) 등은
		 * 고유의 형식을 갖는다.
		 * 그러한 형식을 MimeType이라고 한다.
		 * Hyper Text markup Tag로 만들어진 문서를 HTML DOC type이라 하며
		 * 이 문서는  Web Browser를 통해서 내용을 확인 할 수 있다.
		 * 이처럼 Mime Type을 지정해둔 문서는 인터넷으로부터 받았을 때 
		 * 즉시, 내용을 확인 할 수 있다.
		 * 
		 * xMail을 통해서 지금 만드는 xMail Type은 email viewer에서 
		 * 확인 할 수 있는 문서 형식이다.
		 * 이 문서는 메모장이나 Web Browser로 보면 그 내용을 정확히 
		 * 확인이 어렵다.
		 * 대표적으로 xMail Mime type 문서를 볼 수 있는 어플리케이션에
		 * 아웃룩이 있고, 각 스마트기기의 고유 Email App이 이런 종류에 해당한다
		 */
		MimeMessage message = xMail.createMimeMessage();
		// Mime type으로 만들어진 문서를 인터넷으로 전송하는데
		// 여러가지 기능을 수행해주는 도구
		MimeMessageHelper mHelper = null;
		
		try {
			mHelper = new MimeMessageHelper(message,true,"UTF-8");
			mHelper.setFrom(from_email);
			mHelper.setTo(to_email);
			mHelper.setSubject(emsVO.getS_subject());
			mHelper.setText(emsVO.getS_content(),true);
			
			xMail.send(message);
			
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
