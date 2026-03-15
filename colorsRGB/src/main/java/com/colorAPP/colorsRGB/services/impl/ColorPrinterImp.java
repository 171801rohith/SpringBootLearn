package com.colorAPP.colorsRGB.services.impl;

import ch.qos.logback.core.pattern.color.BoldBlueCompositeConverter;
import com.colorAPP.colorsRGB.services.BluePrinter;
import com.colorAPP.colorsRGB.services.ColorPrinter;
import com.colorAPP.colorsRGB.services.GreenPrinter;
import com.colorAPP.colorsRGB.services.RedPrinter;

public class ColorPrinterImp implements ColorPrinter {
    private RedPrinter redPrinter;
    private GreenPrinter greenPrinter;
    private BluePrinter bluePrinter;

    public ColorPrinterImp(RedPrinter redPrinter, GreenPrinter greenPrinter, BluePrinter bluePrinter) {
        this.redPrinter = redPrinter;
        this.greenPrinter = greenPrinter;
        this.bluePrinter = bluePrinter;
    }

    @Override
    public String print() {
        return String.join(", ", redPrinter.print(), greenPrinter.print(), bluePrinter.print());
    }
}
