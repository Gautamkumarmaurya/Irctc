package com.subString.irctc.Helper;

import java.util.UUID;

public class Helper {

    public static String getFilename(String folder , String originalFilename) {
        return folder + UUID.randomUUID()+"_"+originalFilename;
    };
}
