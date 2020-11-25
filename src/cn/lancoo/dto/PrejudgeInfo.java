package cn.lancoo.dto;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PrejudgeInfo {
    private String answer;
    private double ratio;
    private PrejudgeResultEnum prejudgeResult;

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public void setPrejudgeResult(PrejudgeResultEnum prejudgeResult) {
        this.prejudgeResult = prejudgeResult;
    }

    public String getAnswer() {
        return answer;
    }

    public Double getRatio() {
        return ratio;
    }

    public PrejudgeResultEnum getPrejudgeResult() {
        return prejudgeResult;
    }
}
