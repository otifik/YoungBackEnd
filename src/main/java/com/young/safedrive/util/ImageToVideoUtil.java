package com.young.safedrive.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ Author : autumn
 * @ Date   : created in 21:23 2022/2/11
 */
public class ImageToVideoUtil {
    public static Integer convert(String imagePath){
//        C:\Users\tyurin\Desktop\2022.2.9\21.43.30\front_IR\img_%d.png
        try {
            Runtime.getRuntime().exec("ffmpeg -y -r 30 -i " + imagePath + " -vcodec libx264 -s 1600x900 -pix_fmt yuv420p  C:\\Users\\tyurin\\Desktop\\result\\result.mp4");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
