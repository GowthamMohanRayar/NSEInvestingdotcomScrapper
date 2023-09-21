package com.nse.historicaldata.dto.volume;

import java.util.List;
import java.util.Map;

import com.nse.historicaldata.dto.HistoricalDataDto;
import com.nse.historicaldata.dto.openclose.CloseOpenDto;

public interface PeriodicVolume {
	
	public void addToMap(List<HistoricalDataDto> historicalDataDtos);

	public boolean isMapEligibleForNextLevel();

	public Map<String, Double> getMap();
	
	public Map<String, CloseOpenDto> getOpenCloseMap();


}
