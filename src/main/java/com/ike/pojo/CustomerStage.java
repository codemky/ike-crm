package com.ike.pojo;

public class CustomerStage {
    private Long id;

    private String stageName;

    private String stageExplain;

    private String stageCriteria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName == null ? null : stageName.trim();
    }

    public String getStageExplain() {
        return stageExplain;
    }

    public void setStageExplain(String stageExplain) {
        this.stageExplain = stageExplain == null ? null : stageExplain.trim();
    }

    public String getStageCriteria() {
        return stageCriteria;
    }

    public void setStageCriteria(String stageCriteria) {
        this.stageCriteria = stageCriteria == null ? null : stageCriteria.trim();
    }
}