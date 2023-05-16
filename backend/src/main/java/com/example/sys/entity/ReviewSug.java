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
 * @since 2023-05-15
 */
@TableName("x_review_sug")
public class ReviewSug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String reviewer;

    private String indicator1;

    private String indicator2;

    private String indicator3;

    private String indicator4;

    private String generalComment;

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
    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    public String getIndicator1() {
        return indicator1;
    }

    public void setIndicator1(String indicator1) {
        this.indicator1 = indicator1;
    }
    public String getIndicator2() {
        return indicator2;
    }

    public void setIndicator2(String indicator2) {
        this.indicator2 = indicator2;
    }
    public String getIndicator3() {
        return indicator3;
    }

    public void setIndicator3(String indicator3) {
        this.indicator3 = indicator3;
    }
    public String getIndicator4() {
        return indicator4;
    }

    public void setIndicator4(String indicator4) {
        this.indicator4 = indicator4;
    }
    public String getGeneralComment() {
        return generalComment;
    }

    public void setGeneralComment(String generalComment) {
        this.generalComment = generalComment;
    }

    @Override
    public String toString() {
        return "ReviewSug{" +
            "id=" + id +
            ", userId=" + userId +
            ", reviewer=" + reviewer +
            ", indicator1=" + indicator1 +
            ", indicator2=" + indicator2 +
            ", indicator3=" + indicator3 +
            ", indicator4=" + indicator4 +
            ", generalComment=" + generalComment +
        "}";
    }
}
