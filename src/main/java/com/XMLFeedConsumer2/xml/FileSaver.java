package com.XMLFeedConsumer2.xml;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URL;

public class FileSaver {
    //save downloaded file on path
    @Value("${path.xml}")
    private String path;

    //download file from url
    public void download(String url){
        System.out.println("Downloading is started");
        try{
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream out = new FileOutputStream(path);
            byte [] data_arr = new byte[1024];
            int count;
            while((count = in.read(data_arr, 0, 1024)) != -1){
                out.write(data_arr, 0, count);
                out.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Successful Downloaded");
    }
}
