package com.XMLFeedConsumer2.util;

import com.XMLFeedConsumer2.xml.INIReader;

public interface JDBCConnectionPropereties {
    INIReader iniReader = INIReader.getINIReader();
    String driver = iniReader.getIniData().get(3);
    String url = iniReader.getIniData().get(4);
    String pass = iniReader.getIniData().get(2);
    String user = iniReader.getIniData().get(1);
    String dial = iniReader.getIniData().get(5);
}
