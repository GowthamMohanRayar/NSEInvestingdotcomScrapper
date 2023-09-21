package com.nse.historicaldata.dto.openclose;

import java.util.Date;

public class CloseOpenDto {

	private double open;
	private Date openDate;
	private double close;
	private Date closeDate;
	private double openCloseDiff;

	@Override
	public String toString() {
		return "OpenCloseDto [open=" + open + ", close=" + close + ", openCloseDiff=" + openCloseDiff + "]";
	}

	public CloseOpenDto(double open, Date openDate, double close, Date closeDate) {
		super();
		this.open = open;
		this.openDate = openDate;
		this.close = close;
		this.closeDate = closeDate;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
		setOpenCloseDiff();
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
		setOpenCloseDiff();
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	private void setOpenCloseDiff() {
		this.openCloseDiff = this.close - this.open;
	}

}
