package com.Project_Flight;

public enum Shift {
    MORNING("06:00", "12:00"),
    AFTERNOON("13:00", "19:00"),
    EVENING("19:01", "01:00"), // example
    NIGHT("01:01", "05:59");  // example

    private final String start;
    private final String end;

    Shift(String start, String end) {
        this.start = start;
        this.end = end;
    }
    public String getStart() { return start; }
    public String getEnd() { return end; }
}
