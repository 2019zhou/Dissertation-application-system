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
 * @since 2023-05-17
 */
@TableName("x_blind_review")
public class BlindReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String title;

    private String dissertationId;

    private String results;

    private String novelty;

    private String logic;

    private String readability;

    private String suggestions;

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
    public String getDissertationId() {
        return dissertationId;
    }

    public void setDissertationId(String dissertationId) {
        this.dissertationId = dissertationId;
    }
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
    public String getNovelty() {
        return novelty;
    }

    public void setNovelty(String novelty) {
        this.novelty = novelty;
    }
    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }
    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }
    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public String toString() {
        return "BlindReview{" +
            "id=" + id +
            ", userId=" + userId +
            ", title=" + title +
            ", dissertationId=" + dissertationId +
            ", results=" + results +
            ", novelty=" + novelty +
            ", logic=" + logic +
            ", readability=" + readability +
            ", suggestions=" + suggestions +
        "}";
    }
}
