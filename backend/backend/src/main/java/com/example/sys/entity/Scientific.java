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
@TableName("x_scientific")
public class Scientific implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String username;

    private String title;

    private String journal;

    private LocalDate commitTime;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
    public LocalDate getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(LocalDate commitTime) {
        this.commitTime = commitTime;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Scientific{" +
            "id=" + id +
            ", userId=" + userId +
            ", username=" + username +
            ", title=" + title +
            ", journal=" + journal +
            ", commitTime=" + commitTime +
            ", type=" + type +
        "}";
    }
}
