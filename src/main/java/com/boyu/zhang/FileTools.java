package com.boyu.zhang;


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileTools {
    //缓冲区打下
    private static final int BUFFER_SIZE = 2048;

    /**
     * 该方法用于从HttpServletRequest当中读取到文件数据，并返回
     *
     * @param req 请求对象
     * @return 读取到的文件数据
     */
    public static byte[] getFileDataByRequest(HttpServletRequest req) {
        //准备的用来存 读到的文件数据的容器
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //核心类
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");

            ServletRequestContext context = new ServletRequestContext(req);
            List<FileItem> fileItems = upload.parseRequest(context);

            byte[] buffer = new byte[BUFFER_SIZE];
            int length = 0;
            //fromData =》 文件、name:y、
            for (int i = 0; i < fileItems.size(); i++) {
                FileItem item = fileItems.get(i);
                if (!item.isFormField()) {
                    //如果FileItem不是formFiled类型，就表示是文件数据，读取文件
                    inputStream = item.getInputStream();
                    while ((length = inputStream.read(buffer)) != -1) {
                        //把每次读取到的数据存起来
                        outputStream.write(buffer, 0, length);
                    }
                }
                //读完一个文件了
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return outputStream.toByteArray();
    }
}