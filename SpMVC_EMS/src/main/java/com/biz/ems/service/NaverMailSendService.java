package com.biz.ems.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.biz.ems.model.EmsVO;

@Service
public class NaverMailSendService {

	@Autowired
	private JavaMailSender xMail;
	
	@Autowired
	private String winPath;
	
	public void sendMail(EmsVO emsVO) {
		
		String uploadFoler = winPath;
		
		String from_email = emsVO.getFrom_email();
		String to_email = emsVO.getTo_email();
		String s_file1 = emsVO.getS_file1();
		String s_file2 = emsVO.getS_file2();
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
			// 첫번째 값을 true : HTML적용을 한 메세지를 보낼 수 있다.
			// 두번째 값을 true : 메시지에 이미지 파일을 적용하여 보낼 수 있다.
			mHelper = new MimeMessageHelper(message,true,"UTF-8");
			mHelper.setFrom(from_email);
			mHelper.setTo(to_email);
			mHelper.setSubject(emsVO.getS_subject());
			
			// 두번째 값을 true : HTML 적용을 한 메세지를 보낼 수 있다.
			mHelper.setText(emsVO.getS_content(),true);
			
			FileSystemResource rs = null;
			if(s_file1 != null && !s_file1.isEmpty()) {
				rs = new FileSystemResource(new File(uploadFoler, s_file1));
				mHelper.addAttachment(s_file1, rs);
			}
			if(s_file2 != null && !s_file2.isEmpty()) {
				rs = new FileSystemResource(new File(uploadFoler, s_file2));
				mHelper.addAttachment(s_file2, rs);
			}

			
			
			
			xMail.send(message);
			
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
