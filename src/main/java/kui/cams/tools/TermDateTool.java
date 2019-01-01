package kui.cams.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TermDateTool {

	/**
	 * 根据入学日期判断当前是第几个学期
	 * @param enrolDate入学日期
	 * @param now_date当前日期
	 * @return 学期
	 */
	public static int getTermByDate(Date enrolDate,Date now_date) {
		int dayNumber = (int) ((now_date.getTime()- enrolDate.getTime())/1000/60/60/24);
		int year = dayNumber / 365;
		int currentMonth = now_date.getMonth()+1;
		System.out.println("当前月份："+currentMonth);
		return year*2+getPlus(currentMonth);
		
	}
	
	/**
	 * 根据当前日期，获取判断本学期的日期范围
	 * @param now_date
	 * @return 本学期日期范围，为一个数组，数组长度为2，第一个元素为开始日期，第二个为结束日期
	 */
	public static String[] getUpDownDate(Date now_date) {
		
		String start_date = "";
		String end_date = "";
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(now_date));
		int currentMonth = now_date.getMonth()+1;
		System.out.println("年份："+year+",,month:"+currentMonth);
		if(currentMonth>=2 && currentMonth<9) {//
			start_date += year+"-2-1";
			end_date += year + "-8-31";
		}else {
			start_date += year+"-9-1";
			end_date += (year+1)+"-1-31";
		}
		return new String[] {start_date,end_date};
		
	}
	
	public static String[] getUpDownDate(int term,Date enrolDate) {
		String start_date = "";
		String end_date = "";
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(enrolDate));
		year += term/2;
		if(term%2==1) {
			start_date += year+"-9-1";
			end_date += (year+1)+"-1-31";
		}else {
			start_date += year+"-2-1";
			end_date += year + "-8-31";
		}
		return new String[] {start_date,end_date};
	}
	
	private static int getPlus(int currentMonth) {
		if(currentMonth >= 2 && currentMonth <9) {
			return 2;
		}else return 1;
		
	}
	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//date = sdf.parse("2017-2-30");
		System.out.println("当前是第"+getTermByDate(sdf.parse("2015-9-1"),date)+"学期");
		
		String [] str = getUpDownDate(2,sdf.parse("2015-9-1"));
		System.out.println(str[0]+",,"+str[1]);
	}
}
