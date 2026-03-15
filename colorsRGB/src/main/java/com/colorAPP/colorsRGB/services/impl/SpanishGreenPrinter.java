package com.colorAPP.colorsRGB.services.impl;

import com.colorAPP.colorsRGB.services.GreenPrinter;
import org.springframework.stereotype.Component;

@Component
public class SpanishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "verde";
    }
}
