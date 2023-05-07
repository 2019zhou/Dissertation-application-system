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
 * @since 2023-05-05
 */
@TableName("x_paper")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String title;

    private LocalDate commitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public LocalDate getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(LocalDate commitTime) {
        this.commitTime = commitTime;
    }

    @Override
    public String toString() {
        return "Paper{" +
            "id=" + id +
            ", username=" + username +
            ", title=" + title +
            ", commitTime=" + commitTime +
        "}";
    }
}
