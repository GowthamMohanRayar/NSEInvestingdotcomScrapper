package com.nse.historicaldata.dto.openclose;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nse.historicaldata.dto.HistoricalDataDto;

public class QuarterlyOpenClose {
	
	private Map<String, CloseOpenDto> quarterlyOpenCloseMap = null;

	public void populateOpenClosePriceMap(String key, HistoricalDataDto historicalDataDto, Date date) {
		CloseOpenDto ocd = null;
		if (quarterlyOpenCloseMap == null)
			quarterlyOpenCloseMap = new HashMap<>();
		if (quarterlyOpenCloseMap.containsKey(key)) {
			ocd = quarterlyOpenCloseMap.get(key);
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
			quarterlyOpenCloseMap.put(key, ocd);
		}
	}

	public Map<String, CloseOpenDto> getMap() {
		return quarterlyOpenCloseMap;
	}

}
