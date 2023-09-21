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
import com.nse.historicaldata.dto.openclose.MonthlyOpenClose;

public class MonthlyVolume implements PeriodicVolume {

	private Map<String, Double> monthlyVolumeMap = null;

	private MonthlyOpenClose monthlyOpenClose = new MonthlyOpenClose();

	public void addToMap(List<HistoricalDataDto> historicalDataDtos) {
		monthlyVolumeMap = historicalDataDtos.stream()
				.collect(Collectors.groupingBy(a -> getKey(a), Collectors.averagingInt(HistoricalDataDto::getVOLUME)));
	}

	public boolean isMapEligibleForNextLevel() {
		return null != monthlyVolumeMap && monthlyVolumeMap.size() > 5;
	}

	private String getKey(HistoricalDataDto historicalDataDto) {
		Date date = null;
		String key = "";
		try {
			date = NSEConstants.NSE_DATE_FORMATTER.parse(historicalDataDto.getDATE());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1).length() == 1
					? "0" + (cal.get(Calendar.MONTH) + 1)
					: String.valueOf(cal.get(Calendar.MONTH) + 1);
			key = cal.get(Calendar.YEAR) + "-" + month;
		} catch (ParseException e) {
		} finally {
			monthlyOpenClose.populateOpenClosePriceMap(key, historicalDataDto, date);
		}
		return key;
	}

	public Map<String, Double> getMap() {
		return monthlyVolumeMap;
	}

	public Map<String, CloseOpenDto> getOpenCloseMap() {
		return this.monthlyOpenClose.getMap();
	}

}
