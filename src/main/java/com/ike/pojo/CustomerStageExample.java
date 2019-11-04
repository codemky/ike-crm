package com.ike.pojo;

import java.util.ArrayList;
import java.util.List;

public class CustomerStageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerStageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStageNameIsNull() {
            addCriterion("stage_name is null");
            return (Criteria) this;
        }

        public Criteria andStageNameIsNotNull() {
            addCriterion("stage_name is not null");
            return (Criteria) this;
        }

        public Criteria andStageNameEqualTo(String value) {
            addCriterion("stage_name =", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotEqualTo(String value) {
            addCriterion("stage_name <>", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThan(String value) {
            addCriterion("stage_name >", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("stage_name >=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThan(String value) {
            addCriterion("stage_name <", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThanOrEqualTo(String value) {
            addCriterion("stage_name <=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLike(String value) {
            addCriterion("stage_name like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotLike(String value) {
            addCriterion("stage_name not like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameIn(List<String> values) {
            addCriterion("stage_name in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotIn(List<String> values) {
            addCriterion("stage_name not in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameBetween(String value1, String value2) {
            addCriterion("stage_name between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotBetween(String value1, String value2) {
            addCriterion("stage_name not between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageExplainIsNull() {
            addCriterion("stage_explain is null");
            return (Criteria) this;
        }

        public Criteria andStageExplainIsNotNull() {
            addCriterion("stage_explain is not null");
            return (Criteria) this;
        }

        public Criteria andStageExplainEqualTo(String value) {
            addCriterion("stage_explain =", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainNotEqualTo(String value) {
            addCriterion("stage_explain <>", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainGreaterThan(String value) {
            addCriterion("stage_explain >", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainGreaterThanOrEqualTo(String value) {
            addCriterion("stage_explain >=", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainLessThan(String value) {
            addCriterion("stage_explain <", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainLessThanOrEqualTo(String value) {
            addCriterion("stage_explain <=", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainLike(String value) {
            addCriterion("stage_explain like", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainNotLike(String value) {
            addCriterion("stage_explain not like", value, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainIn(List<String> values) {
            addCriterion("stage_explain in", values, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainNotIn(List<String> values) {
            addCriterion("stage_explain not in", values, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainBetween(String value1, String value2) {
            addCriterion("stage_explain between", value1, value2, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageExplainNotBetween(String value1, String value2) {
            addCriterion("stage_explain not between", value1, value2, "stageExplain");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaIsNull() {
            addCriterion("stage_criteria is null");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaIsNotNull() {
            addCriterion("stage_criteria is not null");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaEqualTo(String value) {
            addCriterion("stage_criteria =", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaNotEqualTo(String value) {
            addCriterion("stage_criteria <>", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaGreaterThan(String value) {
            addCriterion("stage_criteria >", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaGreaterThanOrEqualTo(String value) {
            addCriterion("stage_criteria >=", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaLessThan(String value) {
            addCriterion("stage_criteria <", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaLessThanOrEqualTo(String value) {
            addCriterion("stage_criteria <=", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaLike(String value) {
            addCriterion("stage_criteria like", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaNotLike(String value) {
            addCriterion("stage_criteria not like", value, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaIn(List<String> values) {
            addCriterion("stage_criteria in", values, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaNotIn(List<String> values) {
            addCriterion("stage_criteria not in", values, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaBetween(String value1, String value2) {
            addCriterion("stage_criteria between", value1, value2, "stageCriteria");
            return (Criteria) this;
        }

        public Criteria andStageCriteriaNotBetween(String value1, String value2) {
            addCriterion("stage_criteria not between", value1, value2, "stageCriteria");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}