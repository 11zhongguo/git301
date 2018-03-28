package cn.itcast.core.coversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 自定义转换日期类
 * S :页面传递过来的数据类型
 * T : 转换后的数据类型
 * @author lx
 *
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {//2015-02-06 13:23:02
		// TODO Auto-generated method stub
		if(null != source){
			DateFormat df = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return df.parse(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

}
