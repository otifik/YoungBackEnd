package com.young.safedrive.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * @ Author : autumn
 * @ Date   : created in 15:22 2022/2/9
 */
public class QiNiuUploadUtil {

    public static String accessKey = "yVIxCsAz00iTOLH3gmrQrXYIg8Tu51g3WeEsXwrg";
    public static String secretKey = "S7r9u4rCh_uwpC1FTpl_l-xpVV3ngGgt8rqmNNy5";
    public static String bucket = "safedrive";

    private static final String TAG = "QiNiu";

    public static String upload(String localFilePath) throws QiniuException {

        Configuration configuration = new Configuration(Region.region0());

        UploadManager uploadManager = new UploadManager(configuration);

        Auth auth = Auth.create(accessKey, secretKey);

        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
        try {
            Response response = uploadManager.put(localFilePath, null, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            Log.d(TAG,"key:"+putRet.key+", hash:"+putRet.hash);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            ex.printStackTrace();
            Log.d(TAG,r.bodyString());
            try {
                Log.d(TAG,r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return "EMPTY";
    }
}
