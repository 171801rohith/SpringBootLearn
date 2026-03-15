package com.colorAPP.colorsRGB.services.impl;

import com.colorAPP.colorsRGB.services.BluePrinter;
import org.springframework.stereotype.Component;

@Component
public class EngBluePrinter implements BluePrinter {
    @Override
    public String print() {
        return "Blue Color";
    }
}
