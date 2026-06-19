package com.logistics.util.file;

 

public class FileUploadFileNotFoundException extends FileUploadException {

	private static final long serialVersionUID = 1L;

	public FileUploadFileNotFoundException(String message) {
		super(message);
	}

	public FileUploadFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}