package cn.lancoo.dao;

import cn.lancoo.common.ConfigInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FileInfoDAO {

    public FileInfoDAO(){}
    /**
     * 获取当前日期目录下的所有试题ID
     * @param dateDir 日期为名称的目录名
     * @return 目录列表
     */
    public List<String> listQuestionIDs() throws IOException {

        String path= ConfigInfo.getPath();

        List<String> fileList=null;
        File file = new File(path);
        String[] files = file.list();
        if(files!=null &&files.length>0) fileList= Arrays.asList(files);

        return fileList;
    }


}
