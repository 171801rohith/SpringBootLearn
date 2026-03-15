package com.colorAPP.colorsRGB.services.impl;

import com.colorAPP.colorsRGB.services.BluePrinter;
import com.colorAPP.colorsRGB.services.ColorPrinter;
import com.colorAPP.colorsRGB.services.GreenPrinter;
import com.colorAPP.colorsRGB.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
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
