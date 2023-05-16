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
 * @since 2023-05-13
 */
@TableName("x_blind_review")
public class BlindReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String paperId;

    private String innovative;

    private String logicalStructure;

    private String readability;

    private String comprehensiveReview;

    private String review;

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
    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
    public String getInnovative() {
        return innovative;
    }

    public void setInnovative(String innovative) {
        this.innovative = innovative;
    }
    public String getLogicalStructure() {
        return logicalStructure;
    }

    public void setLogicalStructure(String logicalStructure) {
        this.logicalStructure = logicalStructure;
    }
    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }
    public String getComprehensiveReview() {
        return comprehensiveReview;
    }

    public void setComprehensiveReview(String comprehensiveReview) {
        this.comprehensiveReview = comprehensiveReview;
    }
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "BlindReview{" +
            "id=" + id +
            ", userId=" + userId +
            ", paperId=" + paperId +
            ", innovative=" + innovative +
            ", logicalStructure=" + logicalStructure +
            ", readability=" + readability +
            ", comprehensiveReview=" + comprehensiveReview +
            ", review=" + review +
        "}";
    }
}
