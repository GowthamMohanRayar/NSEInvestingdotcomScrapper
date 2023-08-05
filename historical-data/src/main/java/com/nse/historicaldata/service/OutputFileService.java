package com.nse.historicaldata.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import com.nse.historicaldata.constants.NSEConstants;
import com.nse.historicaldata.dto.QueryDateDto;
import com.nse.historicaldata.utils.NSEFileUtils;

public class OutputFileService {

	private static FileWriter FILE_WRITER = null;

	public static void outputFileInitialize(String ipo, String header, List<QueryDateDto> queryDates) {
		File outputFile = new File(NSEConstants.getOutputFileName(ipo, queryDates));
		createFileDeleteIfExist(outputFile, header);
	}

	public static void createFileDeleteIfExist(File outputFile, String header) {
		try {
			Files.deleteIfExists(outputFile.toPath());
			if (outputFile.createNewFile()) {
				FILE_WRITER = new FileWriter(outputFile);
				FILE_WRITER.write(header);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToNextLine(String finalOutput) {
		try {
			FILE_WRITER.write(finalOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		NSEFileUtils.close(Arrays.asList(FILE_WRITER));
	}

}
