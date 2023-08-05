package com.nse.historicaldata.constants;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

import com.nse.historicaldata.dto.HistoricalDataDto;
import com.nse.historicaldata.dto.NSEHistoryCollection;
import com.nse.historicaldata.service.OutputFileService;
import com.nse.historicaldata.utils.NSEFileUtils;

public class ConsumerConstants {

	public static final Consumer<String> BUILD_DTO_FROM_INPUT_FILE_WRITE_TO_OUTPUT_FILE = data -> {
		if (PredicateConstants.SKIP_FIRST_LINE.negate().test(data)) {
			HistoricalDataDto dto = NSEHistoryCollection.buildHistoricalDataDto(data.split(NSEConstants.CSV_DELIMITER));
			OutputFileService.writeToNextLine(dto.getFinalOutput());
		}
	};

	public static final Consumer<Scanner> FILE_EXIT_OPERATION = scanner -> {
		NSEHistoryCollection.sortByVolumeAndPrint();
		NSEHistoryCollection.NSE_HISTORY_LIST.clear();
		OutputFileService.close();
		NSEFileUtils.close(Arrays.asList(scanner));
	};

	public static final Consumer<Scanner> GENERIC_FILE_EXIT_OPERATION = scanner -> {
		NSEFileUtils.close(Arrays.asList(scanner));
	};

}
