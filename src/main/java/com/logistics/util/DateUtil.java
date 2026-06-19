package com.logistics.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private DateUtil() {}

	public static String getDate(String... format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(format!=null) {
			sdf = new SimpleDateFormat(format[0]);
		}
		return sdf.format(date);
	}
}
