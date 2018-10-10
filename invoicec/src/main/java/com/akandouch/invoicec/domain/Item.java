package com.akandouch.invoicec.domain;

public class Item {
	
	private Long id;
	private String description;
	private Integer unit;
	private Double amount;
	
	
	private String project;
    private Period period;
    private String nature;
    private Float days;
    private Float rate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public Float getDays() {
		return days;
	}
	public void setDays(Float days) {
		this.days = days;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public class Period{
		Date from;
		Date to;
		
		public Period() {}
		
		public Date getFrom() {return this.from;}
		public Date getTo() {return this.to;}
		
		public void setFrom(Date from) {this.from = from;}
		public void setTo(Date to) {this.to = to;}
		@Override
		public String toString() {
			return this.from.toString() + " - " + this.to.toString();
		}
		

		public class Date{
			int day;
			int month;
			int year;
			
			public Date(){}
			
			public int getDay() {return this.day;}
			public int getMonth() {return this.month;}
			public int getYear() { return this.year; }
			
			public void setDay(int day) { this.day = day;}
			public void setMonth(int month) { this.month = month;}
			public void setYear(int year) {this.year = year;}
		}
	}
}
