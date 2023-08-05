package com.nse.historicaldata.constants;

import java.util.List;

import com.nse.historicaldata.dto.QueryDateDto;

public class NSEConstants {

	public static final String CSV_DELIMITER = "\",\"";

	public static final String EMPTY_STRING = "";

	public static final String DOUBLE_QUOTES = "\"";

	public static final String DOT = "\\.";

	public static final String COMMA = ",";

	public static final String COMMON_STRING_TO_REPLACE = "REPLACEWITHIPO";

	public static final String CSV_HEADER = "\"Date \",\"VOLUME \"" + System.lineSeparator();

	public static final String IPO_FILENAME = "ipoNames.txt";

	public static final String NSE_HISTORY_FOLDER_LOCATION = "C:\\Users\\gowtham\\Desktop\\competitive\\historical-data\\src\\main\\resources";

	public static final String NSE_HISTORY_INPUT_FOLDER_LOCATION = NSE_HISTORY_FOLDER_LOCATION
			+ "\\nseHistoryFile\\input\\";

	public static final String NSE_HISTORY_OUTPUT_FOLDER_LOCATION = NSE_HISTORY_FOLDER_LOCATION
			+ "\\nseHistoryFile\\output\\";

	public static final String IPO_LIST_FOLDER_LOCATION = "C:\\Users\\gowtham\\Desktop\\competitive\\historical-data\\src\\main\\resources\\ipoNames\\";

	public static final String START_DATE = "START_DATE";

	public static final String END_DATE = "END_DATE";

	public static final String CHROME_CMD = "chrome.exe \"https://www.nseindia.com/api/historical/cm/equity?symbol=REPLACEHERE&series=[\"\"EQ\"\"\"]^&from="
			+ START_DATE + "^&to=" + END_DATE + "^&csv=true\"";

	public static final String INPUT_FILE_NAMES = "Quote-Equity-REPLACEWITHIPO-EQ-" + START_DATE + "-to-" + END_DATE
			+ ".csv";

	public static String getNSEHistoryInputFileName(String ipo, String fileName) {
		return NSE_HISTORY_INPUT_FOLDER_LOCATION + fileName.replaceFirst(COMMON_STRING_TO_REPLACE, ipo);
	}

	public static String getIPOListFileName(String ipo, String fileName) {
		return IPO_LIST_FOLDER_LOCATION + fileName.replaceFirst(COMMON_STRING_TO_REPLACE, ipo);
	}

	public static String getOutputFileName(String ipo, List<QueryDateDto> queryDate) {
		return NSE_HISTORY_OUTPUT_FOLDER_LOCATION + INPUT_FILE_NAMES.replaceFirst(COMMON_STRING_TO_REPLACE, ipo)
				.replaceFirst(START_DATE, queryDate.get(queryDate.size() - 1).getStartDate())
				.replaceFirst(END_DATE, queryDate.get(0).getEndDate());
	}
}
