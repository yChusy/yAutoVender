package com.ystoreplugins.yautovender.util;

import com.ystoreplugins.yautovender.Main;

import java.text.DecimalFormat;

public class Formatter {

	public static String[] formats = new String[0];

	public static String letterFormatter(Object bigDecimal) {
		try {
			String val = new DecimalFormat("#,###").format(bigDecimal).replace(".", ",");
			Integer ii = val.indexOf(","), i = val.split(",").length;
			if (ii == -1) return val;
			return (val.substring(0, ii + 2) + formats[i]).replace(",0", "");
		} catch (ArrayIndexOutOfBoundsException e) {
			Main.getPlugin(Main.class).getLogger().severe("Há um erro no Formats, veja sua config.yml.");
			return "ERROR";
		}
	}

	public static String doubleFormatter(Object bigDecimal) {
		String val = new DecimalFormat("#,###").format(bigDecimal).replace(".", ",");
		Integer ii = val.indexOf(",");
		if (ii == -1) return val;
		return (val.substring(0, ii + 2)).replace(",0", "");
	}
}