package cn.com.uwoa.system.tools;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonTools {
	
	
	public static String GetCurrDate(){
		
		return GetCurrDate("yyyy-MM-dd HH:mm:ss");
	}
	public final static String GetCurrDate(String dateFormat) {
        Date da = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String currDate = formatter.format(da);

        return currDate;

    }
	

}
