//package com.colorAPP.colorsRGB.config;
//
//import com.colorAPP.colorsRGB.services.BluePrinter;
//import com.colorAPP.colorsRGB.services.ColorPrinter;
//import com.colorAPP.colorsRGB.services.GreenPrinter;
//import com.colorAPP.colorsRGB.services.RedPrinter;
//import com.colorAPP.colorsRGB.services.impl.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PrinterConfig {
//    @Bean
//    public RedPrinter redPrinter() {
//        return new SpanishRedPrinter();
//    }
//
//    @Bean
//    public GreenPrinter greenPrinter() {
//        return new EngGreenPrinter();
//    }
//
//    @Bean
//    public BluePrinter bluePrinter() {
//        return new EngBluePrinter();
//    }
//
//    @Bean
//    public ColorPrinter colorPrinter(RedPrinter redPrinter, GreenPrinter greenPrinter, BluePrinter bluePrinter) {
//        return new ColorPrinterImp(redPrinter, greenPrinter, bluePrinter);
//    }
//}
