package com.nse.historicaldata.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.nse.historicaldata.constants.NSEConstants;
import com.nse.historicaldata.constants.PredicateConstants;
import com.nse.historicaldata.dto.QueryDateDto;
import com.nse.historicaldata.dto.QueryDateDto.CalenderPeriod;
import com.nse.historicaldata.service.OutputFileService;

public class NSEFileUtils {

	public static List<String> readFromFilePutContentToList(String ipo, List<String> inputFileNames,
			Consumer<String> fileRowOperation, Consumer<Scanner> exitOperation) {

		Scanner fileReader = null;
		List<String> outputAsList = null;
		try {
			for (String fileName : inputFileNames) {

				File inputFile = new File(NSEConstants.getIPOListFileName(ipo, fileName));

				fileReader = new Scanner(inputFile);
				while (fileReader.hasNextLine()) {
					String data = fileReader.nextLine();
					if (PredicateConstants.NOT_NULL_CHECK.negate().test(outputAsList))
						outputAsList = new ArrayList<>();
					outputAsList.add(data);
					if (PredicateConstants.NOT_NULL_CHECK.test(fileRowOperation))
						fileRowOperation.accept(data);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (PredicateConstants.NOT_NULL_CHECK.test(exitOperation))
				exitOperation.accept(fileReader);
		}

		return outputAsList;
	}

	public static void readFromAndWriteToFile(String ipo, Consumer<String> fileRowOperation,
			Consumer<Scanner> exitOperation, int period, CalenderPeriod calenderPeriod) {
		Scanner fileReader = null;
		try {

			List<QueryDateDto> queryDates = QueryDateDto.prepareQueryDates(period, CalenderPeriod.YEARS);
			OutputFileService.outputFileInitialize(ipo, NSEConstants.CSV_HEADER, queryDates);
			for (QueryDateDto queryDate : queryDates) {

				File inputFile = new File(NSEConstants.getNSEHistoryInputFileName(ipo,
						NSEConstants.INPUT_FILE_NAMES.replace(NSEConstants.START_DATE, queryDate.getStartDate())
								.replace(NSEConstants.END_DATE, queryDate.getEndDate())));

				fileReader = new Scanner(inputFile);
				while (fileReader.hasNextLine()) {
					String data = fileReader.nextLine();
					if (PredicateConstants.NOT_NULL_CHECK.test(fileRowOperation))
						fileRowOperation.accept(data);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (PredicateConstants.NOT_NULL_CHECK.test(exitOperation))
				exitOperation.accept(fileReader);
		}
	}

	public static void close(List<Object> files) {

		files.stream().forEach(file -> {
			try {
				if (file instanceof FileWriter) {
					FileWriter writer = (FileWriter) file;
					writer.close();
				} else if (file instanceof Scanner) {
					Scanner writer = (Scanner) file;
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

}
