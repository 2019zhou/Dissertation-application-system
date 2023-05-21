package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author TangZT
 * @since 2023-05-16
 */
@TableName("x_defense_result")
public class DefenseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private Integer directorNum;

    private String presentationTime;

    private String presentationPlace;

    private Integer votePassNum;

    private Integer voteFailNum;

    private Integer voteAgreeNum;

    private Integer voteDisagreeNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getDirectorNum() {
        return directorNum;
    }

    public void setDirectorNum(Integer directorNum) {
        this.directorNum = directorNum;
    }
    public String getPresentationTime() {
        return presentationTime;
    }

    public void setPresentationTime(String presentationTime) {
        this.presentationTime = presentationTime;
    }
    public String getPresentationPlace() {
        return presentationPlace;
    }

    public void setPresentationPlace(String presentationPlace) {
        this.presentationPlace = presentationPlace;
    }
    public Integer getVotePassNum() {
        return votePassNum;
    }

    public void setVotePassNum(Integer votePassNum) {
        this.votePassNum = votePassNum;
    }
    public Integer getVoteFailNum() {
        return voteFailNum;
    }

    public void setVoteFailNum(Integer voteFailNum) {
        this.voteFailNum = voteFailNum;
    }
    public Integer getVoteAgreeNum() {
        return voteAgreeNum;
    }

    public void setVoteAgreeNum(Integer voteAgreeNum) {
        this.voteAgreeNum = voteAgreeNum;
    }
    public Integer getVoteDisagreeNum() {
        return voteDisagreeNum;
    }

    public void setVoteDisagreeNum(Integer voteDisagreeNum) {
        this.voteDisagreeNum = voteDisagreeNum;
    }

    @Override
    public String toString() {
        return "DefenseResult{" +
            "id=" + id +
            ", userId=" + userId +
            ", directorNum=" + directorNum +
            ", presentationTime=" + presentationTime +
            ", presentationPlace=" + presentationPlace +
            ", votePassNum=" + votePassNum +
            ", voteFailNum=" + voteFailNum +
            ", voteAgreeNum=" + voteAgreeNum +
            ", voteDisagreeNum=" + voteDisagreeNum +
        "}";
    }
}
