package ch.bfh.bti7081.s2017.red.mhc_pms.util;

import java.io.File;

public class FileTools
{
	public static String getApplicationPath()
	{
		return new File("").getAbsolutePath();
	}
}
