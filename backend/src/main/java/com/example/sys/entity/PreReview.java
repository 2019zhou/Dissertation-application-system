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
@TableName("x_pre_review")
public class PreReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String title;

    private String abstractText;

    private String paperDirection;

    private Integer paperPdf;

    private Integer degreePdf;

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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getAbstract() {
        return abstractText;
    }

    public void setAbstract(String abstractText) {
        this.abstractText = abstractText;
    }
    public String getPaperDirection() {
        return paperDirection;
    }

    public void setPaperDirection(String paperDirection) {
        this.paperDirection = paperDirection;
    }
    public Integer getPaperPdf() {
        return paperPdf;
    }

    public void setPaperPdf(Integer paperPdf) {
        this.paperPdf = paperPdf;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public Integer getDegreePdf() {
        return degreePdf;
    }

    public void setDegreePdf(Integer degreePdf) {
        this.degreePdf = degreePdf;
    }

    @Override
    public String toString() {
        return "PreReview{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", abstractText='" + abstractText + '\'' +
                ", paperDirection='" + paperDirection + '\'' +
                ", paperPdf=" + paperPdf +
                ", degreePdf=" + degreePdf +
                '}';
    }
}
