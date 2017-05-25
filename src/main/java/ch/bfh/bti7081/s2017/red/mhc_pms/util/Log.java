package ch.bfh.bti7081.s2017.red.mhc_pms.util;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

public class Log
{
	static
	{
		// Init log4j properties
		PropertyConfigurator.configure(FileTools.getApplicationPath()+File.separator+"log4j.properties");
	}
}
