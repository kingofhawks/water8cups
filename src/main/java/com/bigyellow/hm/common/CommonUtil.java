package com.bigyellow.hm.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class CommonUtil {
	protected static Logger logger = Logger.getLogger(CommonUtil.class);

	public static int calculateStarLevel(Date date, String standardTime) {
		int result = 1;
		Calendar cal = Calendar.getInstance();

		String[] timeArray = standardTime.split(":");

		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
		cal.set(Calendar.SECOND, 0);

		System.out
				.println(DateUtil.format(cal.getTime(), DateUtil.patterns[1]));

		long diff = Math.abs(date.getTime() - cal.getTimeInMillis());
		if (diff <= 30 * 60 * 1000l) {
			result = 3;
		} else if (diff <= 45 * 60 * 1000l) {
			result = 2;
		}

		System.out.println("result : " + result);
		return result;
	}

	public static List<String> getQiezisaids() {
		ArrayList<String> qiezisaids = new ArrayList<String>();
		qiezisaids
				.add(Constants.qiezisaid1[getRandom(Constants.qiezisaid1.length)]);
		qiezisaids
				.add(Constants.qiezisaid2[getRandom(Constants.qiezisaid2.length)]);
		qiezisaids
				.add(Constants.qiezisaid3[getRandom(Constants.qiezisaid3.length)]);
		qiezisaids
				.add(Constants.qiezisaid4[getRandom(Constants.qiezisaid4.length)]);
		qiezisaids
				.add(Constants.qiezisaid5[getRandom(Constants.qiezisaid5.length)]);
		qiezisaids
				.add(Constants.qiezisaid6[getRandom(Constants.qiezisaid6.length)]);
		qiezisaids
				.add(Constants.qiezisaid7[getRandom(Constants.qiezisaid7.length)]);
		qiezisaids
				.add(Constants.qiezisaid8[getRandom(Constants.qiezisaid8.length)]);
		qiezisaids
				.add(Constants.qiezisaid9[getRandom(Constants.qiezisaid9.length)]);
		qiezisaids
				.add(Constants.qiezisaid10[getRandom(Constants.qiezisaid10.length)]);
		qiezisaids
				.add(Constants.qiezisaid11[getRandom(Constants.qiezisaid11.length)]);
		qiezisaids
				.add(Constants.qiezisaid12[getRandom(Constants.qiezisaid12.length)]);
		qiezisaids
				.add(Constants.qiezisaid13[getRandom(Constants.qiezisaid13.length)]);
		qiezisaids
				.add(Constants.qiezisaid14[getRandom(Constants.qiezisaid14.length)]);
		return qiezisaids;
	}

	public static List<String> getQiezisaidsEat() {
		ArrayList<String> qiezisaidseat = new ArrayList<String>();
		qiezisaidseat
				.add(Constants.qiezisaideat1[getRandom(Constants.qiezisaideat1.length)]);
		qiezisaidseat
				.add(Constants.qiezisaideat2[getRandom(Constants.qiezisaideat2.length)]);
		qiezisaidseat
				.add(Constants.qiezisaideat3[getRandom(Constants.qiezisaideat3.length)]);
		qiezisaidseat
				.add(Constants.qiezisaideat4[getRandom(Constants.qiezisaideat4.length)]);
		qiezisaidseat
				.add(Constants.qiezisaideat5[getRandom(Constants.qiezisaideat5.length)]);
		return qiezisaidseat;
	}

	public static int getRandom(int size) {
		Random ran = new Random();
		int result = Math.abs(ran.nextInt()) % size;
		return result;
	}

}
