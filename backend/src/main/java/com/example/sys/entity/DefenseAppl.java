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
@TableName("x_defense_appl")
public class DefenseAppl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String title;

    private String keyWord;

    private String presentationTime;

    private String directorWorkplace;

    private String directorDirection;

    private String director1Name;

    private String director1Workplace;

    private String director1Title;

    private String director2Name;

    private String director2Workplace;

    private String director2Title;

    private String director3Name;

    private String director3Workplace;

    private String director3Title;

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
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public String getPresentationTime() {
        return presentationTime;
    }

    public void setPresentationTime(String presentationTime) {
        this.presentationTime = presentationTime;
    }
    public String getDirectorWorkplace() {
        return directorWorkplace;
    }

    public void setDirectorWorkplace(String directorWorkplace) {
        this.directorWorkplace = directorWorkplace;
    }
    public String getDirectorDirection() {
        return directorDirection;
    }

    public void setDirectorDirection(String directorDirection) {
        this.directorDirection = directorDirection;
    }
    public String getDirector1Name() {
        return director1Name;
    }

    public void setDirector1Name(String director1Name) {
        this.director1Name = director1Name;
    }
    public String getDirector1Workplace() {
        return director1Workplace;
    }

    public void setDirector1Workplace(String director1Workplace) {
        this.director1Workplace = director1Workplace;
    }
    public String getDirector1Title() {
        return director1Title;
    }

    public void setDirector1Title(String director1Title) {
        this.director1Title = director1Title;
    }
    public String getDirector2Name() {
        return director2Name;
    }

    public void setDirector2Name(String director2Name) {
        this.director2Name = director2Name;
    }
    public String getDirector2Workplace() {
        return director2Workplace;
    }

    public void setDirector2Workplace(String director2Workplace) {
        this.director2Workplace = director2Workplace;
    }
    public String getDirector2Title() {
        return director2Title;
    }

    public void setDirector2Title(String director2Title) {
        this.director2Title = director2Title;
    }
    public String getDirector3Name() {
        return director3Name;
    }

    public void setDirector3Name(String director3Name) {
        this.director3Name = director3Name;
    }
    public String getDirector3Workplace() {
        return director3Workplace;
    }

    public void setDirector3Workplace(String director3Workplace) {
        this.director3Workplace = director3Workplace;
    }
    public String getDirector3Title() {
        return director3Title;
    }

    public void setDirector3Title(String director3Title) {
        this.director3Title = director3Title;
    }

    @Override
    public String toString() {
        return "DefenseAppl{" +
            "id=" + id +
            ", userId=" + userId +
            ", title=" + title +
            ", keyWord=" + keyWord +
            ", presentationTime=" + presentationTime +
            ", directorWorkplace=" + directorWorkplace +
            ", directorDirection=" + directorDirection +
            ", director1Name=" + director1Name +
            ", director1Workplace=" + director1Workplace +
            ", director1Title=" + director1Title +
            ", director2Name=" + director2Name +
            ", director2Workplace=" + director2Workplace +
            ", director2Title=" + director2Title +
            ", director3Name=" + director3Name +
            ", director3Workplace=" + director3Workplace +
            ", director3Title=" + director3Title +
        "}";
    }
}
