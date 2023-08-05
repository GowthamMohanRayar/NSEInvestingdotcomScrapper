package com.nse.historicaldata.dto;

import com.nse.historicaldata.constants.NSEConstants;

public class HistoricalDataDto {
	private String DATE;
	private String SERIES;
	private float OPEN;
	private String HIGH;
	private String LOW;
	private String PREV_CLOSE;
	private String LTP;
	private float CLOSE;
	private String VWAP;
	private String FIFTYTWOWEEK_HIGH;
	private String FIFTYTWOWEEK_LOW;
	private int VOLUME;
	private String VALUE;
	private String NO_OF_TRADES;
	private String finalOutput;

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getSERIES() {
		return SERIES;
	}

	public void setSERIES(String sERIES) {
		SERIES = sERIES;
	}

	public float getOPEN() {
		return OPEN;
	}

	public void setOPEN(float oPEN) {
		OPEN = oPEN;
	}

	public String getHIGH() {
		return HIGH;
	}

	public void setHIGH(String hIGH) {
		HIGH = hIGH;
	}

	public String getLOW() {
		return LOW;
	}

	public void setLOW(String lOW) {
		LOW = lOW;
	}

	public String getPREV_CLOSE() {
		return PREV_CLOSE;
	}

	public void setPREV_CLOSE(String pREV_CLOSE) {
		PREV_CLOSE = pREV_CLOSE;
	}

	public String getLTP() {
		return LTP;
	}

	public void setLTP(String lTP) {
		LTP = lTP;
	}

	public float getCLOSE() {
		return CLOSE;
	}

	public void setCLOSE(float cLOSE) {
		CLOSE = cLOSE;
	}

	public String getVWAP() {
		return VWAP;
	}

	public void setVWAP(String vWAP) {
		VWAP = vWAP;
	}

	public String getFIFTYTWOWEEK_HIGH() {
		return FIFTYTWOWEEK_HIGH;
	}

	public void setFIFTYTWOWEEK_HIGH(String fIFTYTWOWEEK_HIGH) {
		FIFTYTWOWEEK_HIGH = fIFTYTWOWEEK_HIGH;
	}

	public String getFIFTYTWOWEEK_LOW() {
		return FIFTYTWOWEEK_LOW;
	}

	public void setFIFTYTWOWEEK_LOW(String fIFTYTWOWEEK_LOW) {
		FIFTYTWOWEEK_LOW = fIFTYTWOWEEK_LOW;
	}

	public int getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(int vOLUME) {
		VOLUME = vOLUME;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}

	public String getNO_OF_TRADES() {
		return NO_OF_TRADES;
	}

	public void setNO_OF_TRADES(String nO_OF_TRADES) {
		NO_OF_TRADES = nO_OF_TRADES;
	}

	public String getFinalOutput() {
		finalOutput = NSEConstants.DOUBLE_QUOTES + this.getDATE() + NSEConstants.CSV_DELIMITER + this.getVOLUME()
				+ NSEConstants.DOUBLE_QUOTES + System.lineSeparator();
		return finalOutput;
	}

	@Override
	public String toString() {
		return "HistoricalDataDto [DATE=" + DATE +
//				", SERIES=" + SERIES + 
				", OPEN=" + OPEN + ", HIGH=" + HIGH + ", LOW=" + LOW +
//				", PREV_CLOSE=" + PREV_CLOSE + 
//				", LTP=" + LTP + 
				", CLOSE=" + CLOSE +
//				", VWAP=" + VWAP + 
				", FIFTYTWOWEEK_HIGH=" + FIFTYTWOWEEK_HIGH + ", FIFTYTWOWEEK_LOW=" + FIFTYTWOWEEK_LOW + ", VOLUME="
				+ VOLUME +
//				", VALUE=" + VALUE + 
//				", NO_OF_TRADES=" + NO_OF_TRADES + 
				"]";
	}
}
