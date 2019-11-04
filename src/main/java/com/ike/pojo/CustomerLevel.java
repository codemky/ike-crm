package com.ike.pojo;

public class CustomerLevel {
    private Long id;

    private String levelName;

    private String levelExplain;

    private String levelCriteria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getLevelExplain() {
        return levelExplain;
    }

    public void setLevelExplain(String levelExplain) {
        this.levelExplain = levelExplain == null ? null : levelExplain.trim();
    }

    public String getLevelCriteria() {
        return levelCriteria;
    }

    public void setLevelCriteria(String levelCriteria) {
        this.levelCriteria = levelCriteria == null ? null : levelCriteria.trim();
    }
}