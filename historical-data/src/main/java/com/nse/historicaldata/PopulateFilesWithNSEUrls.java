package com.nse.historicaldata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.nse.historicaldata.constants.ConsumerConstants;
import com.nse.historicaldata.constants.NSEConstants;
import com.nse.historicaldata.dto.QueryDateDto;
import com.nse.historicaldata.dto.QueryDateDto.CalenderPeriod;
import com.nse.historicaldata.utils.NSEFileUtils;

public class PopulateFilesWithNSEUrls {

	public static void main(String[] args) throws IOException {
		FileUtils.cleanDirectory(new File(
				"C:\\Users\\gowtham\\Desktop\\competitive\\historical-data\\src\\main\\resources\\nseHistoryFile\\input"));
		FileUtils.cleanDirectory(new File(
				"C:\\Users\\gowtham\\Desktop\\competitive\\historical-data\\src\\main\\resources\\nseHistoryFile\\output"));
		FileWriter fileWrite = null;
		File outputFile = new File(
				"C:\\Users\\gowtham\\Desktop\\competitive\\historical-data\\src\\main\\resources\\ipoNames\\openChrome.bat");
		Files.deleteIfExists(outputFile.toPath());
		if (outputFile.createNewFile()) {
			fileWrite = new FileWriter(outputFile);
		}
		try {

			fileWrite.write("!#bash" + System.lineSeparator());
			fileWrite.write("cd C:\\Program Files\\Google\\Chrome\\Application" + System.lineSeparator());
			fileWrite.write("chrome.exe \"https://www.nseindia.com/get-quotes/equity\"" + System.lineSeparator());
			List<QueryDateDto> queryDates = QueryDateDto.prepareQueryDates(3, CalenderPeriod.YEARS);
			for (String ipo : NSEFileUtils.readFromFilePutContentToList("", Arrays.asList("ipoNames.txt"), null,
					ConsumerConstants.GENERIC_FILE_EXIT_OPERATION)) {
				for (QueryDateDto queryDate : queryDates) {
					fileWrite.write(NSEConstants.CHROME_CMD.replace("REPLACEHERE", ipo.toUpperCase())
							.replace(NSEConstants.START_DATE, queryDate.getStartDate())
							.replace(NSEConstants.END_DATE, queryDate.getEndDate()) + System.lineSeparator());
				}
			}
			fileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
