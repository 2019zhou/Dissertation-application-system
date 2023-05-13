package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author TangZT
 * @since 2023-05-13
 */
@TableName("x_paper")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String title;

    private String userId;

    private String abstractCn;

    private String researchDir;

    private LocalDate commitTime;

    private Integer submit;

    private Integer preTrialReport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAbstractCn() {
        return abstractCn;
    }

    public void setAbstractCn(String abstractCn) {
        this.abstractCn = abstractCn;
    }
    public String getResearchDir() {
        return researchDir;
    }

    public void setResearchDir(String researchDir) {
        this.researchDir = researchDir;
    }
    public LocalDate getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(LocalDate commitTime) {
        this.commitTime = commitTime;
    }
    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }
    public Integer getPreTrialReport() {
        return preTrialReport;
    }

    public void setPreTrialReport(Integer preTrialReport) {
        this.preTrialReport = preTrialReport;
    }

    @Override
    public String toString() {
        return "Paper{" +
            "id=" + id +
            ", username=" + username +
            ", title=" + title +
            ", userId=" + userId +
            ", abstractCn=" + abstractCn +
            ", researchDir=" + researchDir +
            ", commitTime=" + commitTime +
            ", submit=" + submit +
            ", preTrialReport=" + preTrialReport +
        "}";
    }
}
