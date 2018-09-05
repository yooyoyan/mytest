package cn.itcast.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String formatDateToStr(Date date) {
		try {
		//创建SimpleDateFormat 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	//将字符串转成日期
	public static Date parseStrToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
		}
	}

}
