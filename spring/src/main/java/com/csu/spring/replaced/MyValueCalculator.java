package com.csu.spring.replaced;

public class MyValueCalculator {

    public String computeValue(String input) {
        // some real code...
        return input + "-origin";
    }

    public String computeValue(String first, Long second) {
        return first + "-" + second + "-origin";
    }

    public String computeValue(Long first, String second) {
        return first + "-" + second + "-origin";
    }
}
