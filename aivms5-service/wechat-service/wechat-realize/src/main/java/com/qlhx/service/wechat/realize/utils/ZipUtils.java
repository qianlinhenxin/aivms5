package com.qlhx.service.wechat.realize.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by rongcan on 2017/7/4.
 */
public class ZipUtils {
    /**
     * 打包文件
     *
     * @param targetFile 最终的压缩文件
     * @param files      要下载的文件
     */
    public static void zip(File targetFile, List<File> files) {
        if (files == null || files.size() == 0) {
            return;
        }
        // TODO Auto-generated method stub


        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;

        try {
            zipStream = new ZipOutputStream(new FileOutputStream(targetFile));//用这个构造最终压缩包的输出流
            zipSource = null;//将源头文件格式化为输入流


            for (File f : files) {
                zipSource = new FileInputStream(f);

                byte[] bufferArea = new byte[1024 * 10];//读写缓冲区

                //压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                ZipEntry zipEntry = new ZipEntry(f.getName());
                zipStream.putNextEntry(zipEntry);//定位到该压缩条目位置，开始写入文件到压缩包中

                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);//输入缓冲流
                int read = 0;


                //在任何情况下，b[0] 到 b[off] 的元素以及 b[off+len] 到 b[b.length-1] 的元素都不会受到影响。这个是官方API给出的read方法说明，经典！
                while ((read = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
                    zipStream.write(bufferArea, 0, read);
                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bufferStream) bufferStream.close();
                if (null != zipStream) zipStream.close();
                if (null != zipSource) zipSource.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /**
     * 打包网络文件
     *
     * @param targetFile 最终的压缩文件
     * @param files      要下载的文件
     */
    public static boolean zipUrl(File targetFile, List<String> filesUrls) {

        boolean isHavePhoto = false;
        if (filesUrls == null || filesUrls.size() == 0) {
            return isHavePhoto;
        }
        // TODO Auto-generated method stub


        ZipOutputStream zipStream = null;
        InputStream zipSource = null;
        BufferedInputStream bufferStream = null;

        try {
            zipStream = new ZipOutputStream(new FileOutputStream(targetFile));//用这个构造最终压缩包的输出流
            zipSource = null;//将源头文件格式化为输入流


            for (String f : filesUrls) {
                f = f.replaceAll("\\\\", "/");
                zipSource = getImageFromNetByUrl(f);
                if (zipSource == null)
                    continue;
                // zipSource = inputStream;
                //new FileInputStream(f);

                byte[] bufferArea = new byte[1024 * 10];//读写缓冲区

                //压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                ZipEntry zipEntry = new ZipEntry(f.substring(f.lastIndexOf("/") + 1, f.length()));
                zipStream.putNextEntry(zipEntry);//定位到该压缩条目位置，开始写入文件到压缩包中

                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);//输入缓冲流
                int read = 0;


                //在任何情况下，b[0] 到 b[off] 的元素以及 b[off+len] 到 b[b.length-1] 的元素都不会受到影响。这个是官方API给出的read方法说明，经典！
                while ((read = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
                    zipStream.write(bufferArea, 0, read);
                }
                isHavePhoto = true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bufferStream) bufferStream.close();
                if (null != zipStream) zipStream.close();
                if (null != zipSource) zipSource.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return isHavePhoto;
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static InputStream getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
//            int retCode = conn.getResponseCode();
// receive ,用recIn判断是否需要返回流形式

//            if (retCode >= 400) {
//                return conn.getErrorStream();
//            }

            return conn.getInputStream();
            //  InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            //byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            // return inStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
