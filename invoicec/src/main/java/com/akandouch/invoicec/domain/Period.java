package com.akandouch.invoicec.domain;

public class Period{
    DateDto from;
    DateDto to;

    public Period() {}

    private Period(Builder builder) {
        setFrom(builder.from);
        setTo(builder.to);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Period copy) {
        Builder builder = new Builder();
        builder.from = copy.getFrom();
        builder.to = copy.getTo();
        return builder;
    }

    public DateDto getFrom() {return this.from;}
    public DateDto getTo() {return this.to;}

    public void setFrom(DateDto from) {this.from = from;}
    public void setTo(DateDto to) {this.to = to;}
    @Override
    public String toString() {
        return this.from.toString() + " to " + this.to.toString();
    }


    public static final class Builder {
        private DateDto from;
        private DateDto to;

        private Builder() {
        }

        public Builder from(DateDto val) {
            from = val;
            return this;
        }

        public Builder to(DateDto val) {
            to = val;
            return this;
        }

        public Period build() {
            return new Period(this);
        }
    }
}