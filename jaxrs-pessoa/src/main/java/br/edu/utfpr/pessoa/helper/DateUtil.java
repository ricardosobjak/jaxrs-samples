package br.edu.utfpr.pessoa.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	public static SimpleDateFormat format_YMD = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	public static SimpleDateFormat format_DMY = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
	
	public static Calendar parse(String str) throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.setTime(getFormatter(str).parse(str));

		return cal;
	}
	
	/**
	 * Este método retorna um formatador de datas. É passado o @param data. 
	 * O método avalia as características da data e @return um dos SimpleDateFormmat.
	 */
	public static SimpleDateFormat getFormatter(String data) {
		if (data.matches("\\d{4}-\\d{2}-\\d{2}")) 
		    return format_YMD;
		else if (data.matches("\\d{2}/\\d{2}/\\d{4}")) 
			return format_DMY;
		return null;
	}
}
