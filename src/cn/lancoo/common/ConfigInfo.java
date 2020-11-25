package cn.lancoo.common;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class ConfigInfo {

    public static String getPath() throws IOException {

        InputStream is = ConfigInfo.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties=new Properties();
        properties.load(is);
        String rootPath=properties.getProperty("rootPath");
        String datePath=properties.getProperty("datePath");
        if(datePath!=null && !datePath.isEmpty()){
            return rootPath + "\\" + datePath;
        }
        Calendar calendar =Calendar.getInstance();
        Date date=new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String defaultDatePath = dateFormat.format(calendar.getTime());

        return rootPath + "\\" + defaultDatePath;
    }


}
