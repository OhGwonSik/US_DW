package com.logistics.util.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class FileUploadVO {
	//파일 리스트 1개라도 리스트로 받음
	private List<MultipartFile> files;
	//업로드 경로
	private String uploadPath;
}
