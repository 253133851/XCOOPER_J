package com.pabula.fw.utility;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunsai on 2015/9/1.
 */
public class FileUploadConfig {
    public static class Config{
        private String path;
        private String uploadName;

        public void setPath(String path) {
            this.path = path;
        }

        public void setUploadName(String uploadName){
            this.uploadName = uploadName;
        }

        public String getPath() {
            return path;
        }

        public String getUploadName() {
            return uploadName;
        }

        public Config(String uploadName,String path){
            this.path = path;
            this.uploadName = uploadName;
        }

    }


    private static String configStr = "{list:[{path:'',uploadName:''},{path:'',uploadName:''}]}";

    private static Map<String,Config> allConfig = null;

    public static Map<String,Config> getAllConfig() {

        if (allConfig == null) {
            synchronized (FileUploadConfig.class) {
                if (allConfig == null) {
                    allConfig = new HashMap<String, Config>();
                    /**
                     * TODO 使用json配置文件
                     */
                    allConfig.put("imageFile",new Config("imageFile","images"));
                }
            }
        }
        return allConfig;
    }
}
