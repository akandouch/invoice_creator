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

	private Item(Builder builder) {
		setId(builder.id);
		setDescription(builder.description);
		setUnit(builder.unit);
		setAmount(builder.amount);
		setProject(builder.project);
		setPeriod(builder.period);
		setNature(builder.nature);
		setDays(builder.days);
		setRate(builder.rate);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(Item copy) {
		Builder builder = new Builder();
		builder.id = copy.getId();
		builder.description = copy.getDescription();
		builder.unit = copy.getUnit();
		builder.amount = copy.getAmount();
		builder.project = copy.getProject();
		builder.period = copy.getPeriod();
		builder.nature = copy.getNature();
		builder.days = copy.getDays();
		builder.rate = copy.getRate();
		return builder;
	}

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

	public Item(){}


	public static final class Builder {
		private Long id;
		private String description;
		private Integer unit;
		private Double amount;
		private String project;
		private Period period;
		private String nature;
		private Float days;
		private Float rate;

		private Builder() {
		}

		public Builder id(Long val) {
			id = val;
			return this;
		}

		public Builder description(String val) {
			description = val;
			return this;
		}

		public Builder unit(Integer val) {
			unit = val;
			return this;
		}

		public Builder amount(Double val) {
			amount = val;
			return this;
		}

		public Builder project(String val) {
			project = val;
			return this;
		}

		public Builder period(Period val) {
			period = val;
			return this;
		}

		public Builder nature(String val) {
			nature = val;
			return this;
		}

		public Builder days(Float val) {
			days = val;
			return this;
		}

		public Builder rate(Float val) {
			rate = val;
			return this;
		}

		public Item build() {
			return new Item(this);
		}
	}
}
