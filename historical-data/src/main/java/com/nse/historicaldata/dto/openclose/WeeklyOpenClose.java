package com.nse.historicaldata.dto.openclose;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nse.historicaldata.dto.HistoricalDataDto;

public class WeeklyOpenClose {

	private Map<String, CloseOpenDto> weeklyOpenCloseMap = null;

	public void populateOpenClosePriceMap(String key, HistoricalDataDto historicalDataDto, Date date) {
		CloseOpenDto ocd = null;
		if (weeklyOpenCloseMap == null)
			weeklyOpenCloseMap = new HashMap<>();
		if (weeklyOpenCloseMap.containsKey(key)) {
			ocd = weeklyOpenCloseMap.get(key);
			if (date.before(ocd.getOpenDate())) {
				ocd.setOpenDate(date);
				ocd.setOpen(historicalDataDto.getOPEN());
			}
			if (date.after(ocd.getCloseDate())) {
				ocd.setCloseDate(date);
				ocd.setClose(historicalDataDto.getCLOSE());
			}
		} else {
			ocd = new CloseOpenDto(historicalDataDto.getOPEN(), date, historicalDataDto.getCLOSE(), date);
			weeklyOpenCloseMap.put(key, ocd);
		}
	}

	public Map<String, CloseOpenDto> getMap() {
		return weeklyOpenCloseMap;
	}

}
