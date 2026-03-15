package com.colorAPP.colorsRGB;

import com.colorAPP.colorsRGB.services.ColorPrinter;
import com.colorAPP.colorsRGB.services.impl.ColorPrinterImp;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class ColorsRgbApplication implements CommandLineRunner {
	private ColorPrinter colorPrinter;

	public ColorsRgbApplication(ColorPrinter colorPrinter) {
		this.colorPrinter = colorPrinter;
	}
	public static void main(String[] args) {
		SpringApplication.run(ColorsRgbApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info(colorPrinter.print());
	}
}
