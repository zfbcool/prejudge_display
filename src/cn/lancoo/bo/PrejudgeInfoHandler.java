package cn.lancoo.bo;

import cn.lancoo.common.ConfigInfo;
import cn.lancoo.dto.PrejudgeInfo;
import cn.lancoo.dto.PrejudgeResultEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.sound.sampled.Line;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrejudgeInfoHandler {

    private String currentFolderPath;
    private String quesID;

    public PrejudgeInfoHandler(String quesID) throws IOException {
        this.quesID=quesID;
        init();
    }

    public Map<Integer, List<PrejudgeInfo>> getAllInfos() throws IOException,NumberFormatException {

        Map<Integer,List<PrejudgeInfo>> infos=null;
        List<String> files = listPrejudgeFiles();

        if(files!=null){
            infos=new TreeMap<>();
            for (String fileName : files) {
                String content=ReadFileInfo(fileName);
                List<PrejudgeInfo> prejudgeInfos = parseToResult(content);
                int index = Integer.parseInt(fileName.split("_")[1]);
                infos.put(index,prejudgeInfos);
            }
        }

        return infos;
    }

    /**
     * 初始化当前正在处理的试题路径
     *
     * @param rootPath
     * @param quesID
     * @throws IOException
     */
    private void init() throws IOException {

        currentFolderPath = ConfigInfo.getPath()+ "\\" + quesID;
    }

    private List<PrejudgeInfo> parseToResult(String content){
        List<PrejudgeInfo> prejudgeInfos=null;

        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONArray answerList = jsonObject.getJSONArray("answerList");
        if(answerList!=null && answerList.size()>0){
            prejudgeInfos=new ArrayList<>();
            for (Object answer:answerList) {
                PrejudgeInfo prejudgeInfo =new PrejudgeInfo();
                JSONObject answerJson=(JSONObject)answer;
                prejudgeInfo.setAnswer(answerJson.getString("answer"));
                Double ratio=answerJson.getDouble("ratio");
                BigDecimal bd=new BigDecimal(ratio);

                ratio = bd.setScale(4, BigDecimal.ROUND_HALF_EVEN).doubleValue();
                prejudgeInfo.setRatio(ratio);
                if(answerJson.getIntValue("prejudgeResult")== PrejudgeResultEnum.REF_ANSWER.getValue()){
                    prejudgeInfo.setPrejudgeResult(PrejudgeResultEnum.REF_ANSWER);
                }else {
                    prejudgeInfo.setPrejudgeResult(PrejudgeResultEnum.SUSPECTED_ANSWER);
                }
                prejudgeInfos.add(prejudgeInfo);
            }

        }
        return prejudgeInfos;
    }

    /**
     * 列出当前试题下所有预判小题
     *
     * @param quesID
     * @return
     */
    private List<String> listPrejudgeFiles() throws IOException {

        File file = new File(currentFolderPath);
        String[] files = file.list();
        List<String> fileList = null;
        if (files != null && files.length > 0) fileList = Arrays.asList(files);

        return fileList;
    }

    private String ReadFileInfo(String fileName) {
        File file = new File(currentFolderPath + "\\" + fileName);
        BufferedReader bReader=null;
        StringBuilder stringBuiler=new StringBuilder();
        try {
            bReader = new BufferedReader(new FileReader(file));
            String content = null;
            while ((content = bReader.readLine()) != null) {
                stringBuiler.append(content);
            }
            return stringBuiler.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(bReader!=null){
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuiler.toString();

    }
}
