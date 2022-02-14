package com.young.safedrive.serviceImpl;

import com.young.safedrive.response.BaseResponse;
import com.young.safedrive.service.IdentifyService;
import com.young.safedrive.util.ImageToVideoUtil;
import com.young.safedrive.util.Log;
import com.young.safedrive.util.QiNiuUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author : autumn
 * @ Date   : created in 14:12 2022/2/12
 */
@Service
public class IdentifyServiceImpl implements IdentifyService {

    private static final String TAG = "IDENTIFY";

    @Override
    public BaseResponse<String> identifyDriver(MultipartFile[] video) throws IOException {

        BaseResponse<String> response = new BaseResponse<>();

        for (MultipartFile file: video){
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
            String fileName = simpleDateFormat.format(date);
            String filePath = "C:\\Users\\tyurin\\Desktop\\video\\"+fileName+".mp4";
            file.transferTo(new File(filePath));
            //TODO:识别

        }
        String imagePath = "C:\\Users\\tyurin\\Desktop\\2022.2.9\\21.43.30\\front_IR\\img_%d.png";
        Integer i = ImageToVideoUtil.convert(imagePath);
        String resultPath = "C:\\Users\\tyurin\\Desktop\\result\\result.mp4";
        if (i == 1){
            String key = QiNiuUploadUtil.upload(resultPath);
            if (!key.equals("EMPTY")){
                Log.d(TAG,"identify success");
                String videoAddress = "http://r70serzdg.hd-bkt.clouddn.com/" + key;
                response.setStatus(200);
                response.setMsg("success");
                response.setData(videoAddress);
            }else {
                Log.d(TAG,"identify failure");
                response.setStatus(0);
                response.setMsg("failure");
                response.setData("NULL");
            }
            return response;
        }else {
            Log.d(TAG,"ERROR");
            response.setStatus(0);
            response.setMsg("failure");
            response.setData("NULL");
            return response;
        }
    }
}
