package com.ike.pojo;

public class ProductClass {
    private Long id;

    private String className;

    private Long previousClassId;

    private Byte classLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Long getPreviousClassId() {
        return previousClassId;
    }

    public void setPreviousClassId(Long previousClassId) {
        this.previousClassId = previousClassId;
    }

    public Byte getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(Byte classLevel) {
        this.classLevel = classLevel;
    }
}