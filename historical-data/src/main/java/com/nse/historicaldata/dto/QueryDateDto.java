package com.nse.historicaldata.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QueryDateDto {

	private static List<QueryDateDto> QUERY_DATES = new ArrayList<>();

	private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY");

	private String endDate;
	private String startDate;

	private QueryDateDto(String endDate, String startDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public static List<QueryDateDto> prepareQueryDates(int period, CalenderPeriod calenderGroup) {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = null;

		if (CalenderPeriod.DAYS == calenderGroup) {
			startDate = endDate.minusDays(period);
		} else if (CalenderPeriod.MONTHS == calenderGroup) {
			startDate = endDate.minusMonths(period);
		} else if (CalenderPeriod.YEARS == calenderGroup) {
			while (period > 0) {
				startDate = endDate.minusYears(1);
				QUERY_DATES.add(new QueryDateDto(DATE_FORMATTER.format(endDate), DATE_FORMATTER.format(startDate)));
				endDate = startDate.minusDays(1);
				period--;
			}
			return QUERY_DATES;
		}
		QUERY_DATES.add(new QueryDateDto(DATE_FORMATTER.format(endDate), DATE_FORMATTER.format(startDate)));
		return QUERY_DATES;

	}

	public enum CalenderPeriod {
		YEARS, MONTHS, DAYS;
	}

}
