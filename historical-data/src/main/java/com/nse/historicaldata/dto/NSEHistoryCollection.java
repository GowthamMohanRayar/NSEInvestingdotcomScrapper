package com.nse.historicaldata.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.nse.historicaldata.constants.FunctionConstants;
import com.nse.historicaldata.constants.NSEConstants;
import com.nse.historicaldata.constants.PredicateConstants;
import com.nse.historicaldata.dto.volume.FinancialYearlyVolume;
import com.nse.historicaldata.dto.volume.MonthlyVolume;
import com.nse.historicaldata.dto.volume.PeriodicVolume;
import com.nse.historicaldata.dto.volume.QuarterlyVolume;
import com.nse.historicaldata.dto.volume.WeeklyVolume;

public class NSEHistoryCollection {

	public static List<HistoricalDataDto> NSE_HISTORY_LIST = new ArrayList<>();

	private static final Comparator<HistoricalDataDto> sortByVolumeComparator = Comparator
			.comparingInt(HistoricalDataDto::getVOLUME);

	public static void sortByVolumeAndPrint() {
		NSE_HISTORY_LIST.stream().filter(PredicateConstants.OPEN_GREATER_THAN_CLOSE)
				.sorted(sortByVolumeComparator.reversed()).limit(10).forEach(System.out::println);
		System.out.println("");
		NSE_HISTORY_LIST.stream().sorted(sortByVolumeComparator.reversed()).limit(10).forEach(System.out::println);

		WeeklyVolume volume = new WeeklyVolume();
		volume.addToMap(NSE_HISTORY_LIST);
		MonthlyVolume volume2 = new MonthlyVolume();
		volume2.addToMap(NSE_HISTORY_LIST);
		QuarterlyVolume volume3 = new QuarterlyVolume();
		volume3.addToMap(NSE_HISTORY_LIST);
		FinancialYearlyVolume volume4 = new FinancialYearlyVolume();
		volume4.addToMap(NSE_HISTORY_LIST);

		printOpenCloseAndVolume(volume, "WEEK", "WEEKLY_VOLUME");
		printOpenCloseAndVolume(volume2, "MONTH", "MONTHLY_VOLUME");
		printOpenCloseAndVolume(volume3, "QUARTER", "WEEKLY_VOLUME");
		printOpenCloseAndVolume(volume4, "FINANCIAL", "FINANCIAL_YEARLY_VOLUME");
	}

	private static void printOpenCloseAndVolume(PeriodicVolume volume, String periodLable, String periodVolumeLable) {
		System.out.println(System.lineSeparator() + periodLable + " " + periodVolumeLable);
		volume.getMap().entrySet().stream().sorted(Map.Entry.comparingByKey())
				.forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
		System.out.println(System.lineSeparator() + periodLable + " OPEN_CLOSE");
		volume.getOpenCloseMap().entrySet().stream().sorted(Map.Entry.comparingByKey())
				.forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
	}

	public static HistoricalDataDto buildHistoricalDataDto(String[] rowFromFile) {
		HistoricalDataDto dto = new HistoricalDataDto();

		dto.setDATE(rowFromFile[0].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setSERIES(rowFromFile[1].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setOPEN(FunctionConstants.PARSE_DOUBLE.applyAsDouble(rowFromFile[2]));
		dto.setHIGH(rowFromFile[3].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setLOW(rowFromFile[4].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setPREV_CLOSE(rowFromFile[5].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setLTP(rowFromFile[6].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setCLOSE(FunctionConstants.PARSE_DOUBLE.applyAsDouble(rowFromFile[7]));
		dto.setVWAP(rowFromFile[8].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setFIFTYTWOWEEK_HIGH(rowFromFile[9].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setFIFTYTWOWEEK_LOW(rowFromFile[10].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setVOLUME(Integer.parseInt(rowFromFile[11].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING)
				.replace(NSEConstants.COMMA, NSEConstants.EMPTY_STRING)));
		dto.setVALUE(rowFromFile[12].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setNO_OF_TRADES(rowFromFile[13].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		NSE_HISTORY_LIST.add(dto);

		return dto;
	}

}
