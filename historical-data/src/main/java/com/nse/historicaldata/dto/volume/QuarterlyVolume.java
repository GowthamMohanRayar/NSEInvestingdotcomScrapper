package com.nse.historicaldata.dto.volume;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nse.historicaldata.constants.NSEConstants;
import com.nse.historicaldata.dto.HistoricalDataDto;
import com.nse.historicaldata.dto.openclose.CloseOpenDto;
import com.nse.historicaldata.dto.openclose.QuarterlyOpenClose;

public class QuarterlyVolume implements PeriodicVolume {

	private Map<String, Double> financialYearlyVolumeMap = null;

	private QuarterlyOpenClose quarterlyOpenClose = new QuarterlyOpenClose();

	public void addToMap(List<HistoricalDataDto> historicalDataDtos) {
		financialYearlyVolumeMap = historicalDataDtos.stream()
				.collect(Collectors.groupingBy(a -> getKey(a), Collectors.averagingInt(HistoricalDataDto::getVOLUME)));
	}

	public boolean isMapEligibleForNextLevel() {
		return null != financialYearlyVolumeMap && financialYearlyVolumeMap.size() > 5;
	}

	private String getKey(HistoricalDataDto historicalDataDto) {
		Date date = null;
		String key = "";
		try {
			date = NSEConstants.NSE_DATE_FORMATTER.parse(historicalDataDto.getDATE());

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String yearString = String.valueOf(cal.get(Calendar.YEAR));
			String quarter = "1";
			if (cal.get(Calendar.MONTH) + 1 >= 4 && cal.get(Calendar.MONTH) + 1 <= 12) {
				yearString = String.valueOf(cal.get(Calendar.YEAR) + 1);
			}

			if (cal.get(Calendar.MONTH) + 1 >= 7 && cal.get(Calendar.MONTH) + 1 <= 9) {
				quarter = "2";
			}
			if (cal.get(Calendar.MONTH) + 1 >= 10 && cal.get(Calendar.MONTH) + 1 <= 12) {
				quarter = "3";
			}
			if (cal.get(Calendar.MONTH) + 1 >= 1 && cal.get(Calendar.MONTH) + 1 <= 3) {
				quarter = "4";
			}
			key = "F" + yearString.substring(2, 4) + "Q" + quarter;
		} catch (ParseException e) {
		} finally {
			quarterlyOpenClose.populateOpenClosePriceMap(key, historicalDataDto, date);
		}
		return key;
	}

	public Map<String, Double> getMap() {
		return financialYearlyVolumeMap;
	}
	
	public Map<String, CloseOpenDto> getOpenCloseMap() {
		return this.quarterlyOpenClose.getMap();
	}


}
