package com.akandouch.invoicec.domain;

public class DateDto {
    int day;
    int month;
    int year;

    public DateDto(){}

    private DateDto(Builder builder) {
        setDay(builder.day);
        setMonth(builder.month);
        setYear(builder.year);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(DateDto copy) {
        Builder builder = new Builder();
        builder.day = copy.getDay();
        builder.month = copy.getMonth();
        builder.year = copy.getYear();
        return builder;
    }

    public int getDay() {return this.day;}
    public int getMonth() {return this.month;}
    public int getYear() { return this.year; }

    public void setDay(int day) { this.day = day;}
    public void setMonth(int month) { this.month = month;}
    public void setYear(int year) {this.year = year;}
    @Override
    public String toString() {
        return this.day + "-" + this.month + "-" + this.year;
    }


    public static final class Builder {
        private int day;
        private int month;
        private int year;

        private Builder() {
        }

        public Builder day(int val) {
            day = val;
            return this;
        }

        public Builder month(int val) {
            month = val;
            return this;
        }

        public Builder year(int val) {
            year = val;
            return this;
        }

        public DateDto build() {
            return new DateDto(this);
        }
    }
}
