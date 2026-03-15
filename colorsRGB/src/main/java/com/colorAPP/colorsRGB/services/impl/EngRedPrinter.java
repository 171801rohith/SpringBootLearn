package com.colorAPP.colorsRGB.services.impl;

import com.colorAPP.colorsRGB.services.RedPrinter;
import org.springframework.stereotype.Component;

//@Component
public class EngRedPrinter implements RedPrinter {

    @Override
    public String print() {
        return "Red Color";
    }
}
