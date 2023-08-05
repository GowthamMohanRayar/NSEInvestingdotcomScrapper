package com.nse.historicaldata.constants;

import java.util.function.Predicate;

import com.nse.historicaldata.dto.HistoricalDataDto;

public class PredicateConstants {

	public static final Predicate<String> SKIP_FIRST_LINE = data -> data.contains("Date")
			&& data.contains("No of trades");

	public static final Predicate<Object> NOT_NULL_CHECK = data -> null != data;

	public static final Predicate<HistoricalDataDto> OPEN_GREATER_THAN_CLOSE = dto -> (dto.getCLOSE()
			- dto.getOPEN())>0;

}
