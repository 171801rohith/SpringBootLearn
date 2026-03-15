package com.colorAPP.colorsRGB.services.impl;

import com.colorAPP.colorsRGB.services.RedPrinter;
import org.springframework.stereotype.Service;

@Service
public class SpanishRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "rojo";
    }
}
