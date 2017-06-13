package ch.bfh.bti7081.s2017.red.mhc_pms.common.utils;

import java.io.File;

public class FileUtil {

    public static String getApplicationPath() {
        return new File("").getAbsolutePath();
    }
}
