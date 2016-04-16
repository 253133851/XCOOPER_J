package com.pabula.fw.utility;

import com.pabula.common.util.StrUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sunsai on 2015/9/1.
 */
public class FileUpload {

    public String randFileName(String type){
        return new Date().getTime()+"."+type;
    }

    public String upload(HttpServletRequest request) {
        String  newFileName = "";

        String type = request.getParameter("f");
        String subPath = FileUploadConfig.getAllConfig().get(type).getPath();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        @SuppressWarnings("deprecation")
        String path = request.getRealPath("/uploads")+"/"+subPath;
        File dir = new File(path);

        if(!dir.exists()&&!dir.isDirectory()){
            dir.mkdirs();
        }

        factory.setRepository(new File(path));
        factory.setSizeThreshold(1024*1024);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(-1);


        try {
            @SuppressWarnings("unchecked")
            List<FileItem> list = upload.parseRequest(request);
            String va = null;
            Iterator<FileItem> itr = list.iterator();

            if (itr.hasNext()){
                FileItem item = (FileItem) itr.next();
                if (!item.isFormField()){
                    String name = item.getName();
                    if(name!=null){
                        File fullFile=new File(item.getName());

                        int length = fullFile.getName().split(".").length;


                       // String type= fullFile.getName().split(".")[length-1];
                        //TODO 识别文件类型名
                        newFileName = randFileName("jpg");
                        File savedFile=new File(path, newFileName);
                        item.write(savedFile);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            newFileName = "error";
        }

        return newFileName;
    }

    public boolean deleteFile(HttpServletRequest request){

        String type = request.getParameter("type");

        String deleteFileName = request.getParameter("delFile");

        if(StrUtil.isNotNull(deleteFileName)){
            String path = request.getRealPath("/uploads")+"/"+FileUploadConfig.getAllConfig().get(type).getPath();
            File file = new File(path +"/" +deleteFileName);
            if(file.exists()){
                file.delete();
            }
            return true;
        }
        return false;
    }

}
