package com.XMLFeedConsumer2.xml;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//Read data from file
@Component
@Scope("singleton")
public class INIReader {
    private List <StringBuilder> data = new ArrayList<>();
    private List <String> list_data = new ArrayList<>();

    @Value("${path.config}")
    private String path;

    //Get data from config file
    private void readIni(String path){
        try(FileReader reader = new FileReader(path)) {
            int c = reader.read();
            int iter = 0;
            data.add(new StringBuilder());
            do{
                data.get(iter).append((char)c);
                if(c==10){
                    list_data.add(data.get(iter)
                            .substring(0, data.get(iter).length() - 1));
                    iter++;
                    data.add(new StringBuilder());
                }
            }while ((c=reader.read())!=-1);
            //last element adding
            list_data.add(data.get(iter)
                    .toString());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    //Get method
    public List <String> getIniData() {
        this.readIni(path);
        return list_data;
    }
}
