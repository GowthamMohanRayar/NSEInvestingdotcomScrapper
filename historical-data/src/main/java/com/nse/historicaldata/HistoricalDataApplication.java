package com.nse.historicaldata;

import java.util.Arrays;

import com.nse.historicaldata.constants.ConsumerConstants;
import com.nse.historicaldata.dto.QueryDateDto.CalenderPeriod;
import com.nse.historicaldata.utils.NSEFileUtils;

public class HistoricalDataApplication {

	public static void main(String[] args) {
		for (String ipo : NSEFileUtils.readFromFilePutContentToList("", Arrays.asList("ipoNames.txt"), null,
				ConsumerConstants.GENERIC_FILE_EXIT_OPERATION)) {
			System.out.println(ipo.toUpperCase());
			NSEFileUtils.readFromAndWriteToFile(ipo.toUpperCase(),
					ConsumerConstants.BUILD_DTO_FROM_INPUT_FILE_WRITE_TO_OUTPUT_FILE,
					ConsumerConstants.FILE_EXIT_OPERATION, 3, CalenderPeriod.YEARS);
		}

	}

}