package com.nse.historicaldata.dto.openclose;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nse.historicaldata.dto.HistoricalDataDto;

public class FinancialYearlyOpenClose {

	private Map<String, CloseOpenDto> financialYearlyOpenCloseMap = null;

	public void populateOpenClosePriceMap(String key, HistoricalDataDto historicalDataDto, Date date) {
		CloseOpenDto ocd = null;
		if (financialYearlyOpenCloseMap == null)
			financialYearlyOpenCloseMap = new HashMap<>();
		if (financialYearlyOpenCloseMap.containsKey(key)) {
			ocd = financialYearlyOpenCloseMap.get(key);
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
			financialYearlyOpenCloseMap.put(key, ocd);
		}

	}

	public Map<String, CloseOpenDto> getMap() {
		return financialYearlyOpenCloseMap;
	}

}
