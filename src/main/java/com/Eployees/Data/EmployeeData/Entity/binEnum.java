package com.Eployees.Data.EmployeeData.Entity;

public enum binEnum {
    East1("Rim East 1"),
    East2("Rim East 2"),
    East3("Rim East 3"),
    West1("Rim West 1"),
    West2("Rim West 2"),
    West3("Rim West 3"),
    South1("Rim South 1"),
    South2("Rim South 2"),
    South3("Rim South 3"),
    North1("Rim South 1"),
    North2("Rim South 2"),
    North3("Rim South 3");

    private final String label;

    binEnum (String label)
    {
        this.label = label;
    }

    public String getLabel ()
    {
        return label;
    }
}
