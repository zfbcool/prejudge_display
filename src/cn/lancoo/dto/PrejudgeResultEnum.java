package cn.lancoo.dto;

import java.sql.PreparedStatement;

public enum PrejudgeResultEnum {
    REF_ANSWER(0),SUSPECTED_ANSWER(1);
    private int value;

    PrejudgeResultEnum(int value) {
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
