package com.nse.historicaldata.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.nse.historicaldata.constants.FunctionConstants;
import com.nse.historicaldata.constants.NSEConstants;
import com.nse.historicaldata.constants.PredicateConstants;

public class NSEHistoryCollection {

	public static List<HistoricalDataDto> NSE_HISTORY_LIST = new ArrayList<>();

	private static final Comparator<HistoricalDataDto> sortByVolumeComparator = Comparator
			.comparingInt(HistoricalDataDto::getVOLUME);

	public static void sortByVolumeAndPrint() {
		NSE_HISTORY_LIST.stream().filter(PredicateConstants.OPEN_GREATER_THAN_CLOSE)
				.sorted(sortByVolumeComparator.reversed()).limit(10).forEach(System.out::println);
		System.out.println("");
		NSE_HISTORY_LIST.stream().sorted(sortByVolumeComparator.reversed()).limit(10).forEach(System.out::println);
	}

	public static HistoricalDataDto buildHistoricalDataDto(String[] rowFromFile) {
		HistoricalDataDto dto = new HistoricalDataDto();

		dto.setDATE(rowFromFile[0].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setSERIES(rowFromFile[1].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setOPEN(FunctionConstants.PARSE_INT.applyAsInt(rowFromFile[2]));
		dto.setHIGH(rowFromFile[3].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setLOW(rowFromFile[4].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setPREV_CLOSE(rowFromFile[5].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setLTP(rowFromFile[6].replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING));
		dto.setCLOSE(FunctionConstants.PARSE_INT.applyAsInt(rowFromFile[7]));
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
