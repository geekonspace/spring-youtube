/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.youtube;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public class TestSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String stringUrl = "https://www.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&id=uWKM4F2RAtI%2CJ2NIttHwZBA%2C6UliPT1LIc4%2CSaLWwDyHMvo&maxResults=3&key=AIzaSyBB8x12DXzrzXKhkum5f_Nv3Yl7-0GSwCg";
		String data = Util.httpGet(stringUrl);
		System.out.println(data);*/
		
		PeriodFormatter formatter = ISOPeriodFormat.standard();
		
		Period period = formatter.parsePeriod("PT1H5M7S");
		System.out.println(period.getHours()+":"+period.getMinutes()+":"+period.getSeconds());
	}

}
