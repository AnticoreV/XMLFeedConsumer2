package com.XMLFeedConsumer2.xml;

import java.io.*;
import java.net.URL;

public class FileSaver {
    private final String path;

    //save downloaded file on path
    public FileSaver(String path) {
        this.path = path;
    }

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
