package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("fileServiceV3")
public class FileServiceImplV3 extends FileServiceImplV1 {

	
	@Override
	public String fileUp(MultipartFile file) {

		// mkdir : 폴더를 만드는 운영체제 명령어 
		// mkdirs : rootpath하의 모든 폴더가 없을시(C:/bizwork/workspace)한번에 생성하는 것
		String rootFolder = "C:/bizwork/workspace/upload";
		File dir = new File(rootFolder);
		// 파일을 업로드 할 폴더를 검사하여 없으면 새로 생성해달라
		if(!dir.exists()) {
			// mkdir()은 제일 끝의 폴더 1개만 생성
			// mkdirs()는 모든 경로의 폴더를 한꺼번에 생성
			dir.mkdirs();
		}
		
		String fileName = file.getOriginalFilename();
		
		// 서버의 저장 폴더 + 파일이름을 합성하여 파일 저장 준비
		File saveFile = new File(rootFolder,fileName);
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	
	
}
