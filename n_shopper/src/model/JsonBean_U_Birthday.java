package model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonBean_U_Birthday {

	@JsonProperty("year")
	private int year;

	@JsonProperty("month")
	private int month;

	@JsonProperty("day")
	private int day;

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
