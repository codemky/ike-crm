package com.ike.pojo;

import java.util.ArrayList;
import java.util.List;

public class CustomerLevelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerLevelExample() {
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

        public Criteria andLevelNameIsNull() {
            addCriterion("level_name is null");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNotNull() {
            addCriterion("level_name is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNameEqualTo(String value) {
            addCriterion("level_name =", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotEqualTo(String value) {
            addCriterion("level_name <>", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThan(String value) {
            addCriterion("level_name >", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("level_name >=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThan(String value) {
            addCriterion("level_name <", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThanOrEqualTo(String value) {
            addCriterion("level_name <=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLike(String value) {
            addCriterion("level_name like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotLike(String value) {
            addCriterion("level_name not like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameIn(List<String> values) {
            addCriterion("level_name in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotIn(List<String> values) {
            addCriterion("level_name not in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameBetween(String value1, String value2) {
            addCriterion("level_name between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotBetween(String value1, String value2) {
            addCriterion("level_name not between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelExplainIsNull() {
            addCriterion("level_explain is null");
            return (Criteria) this;
        }

        public Criteria andLevelExplainIsNotNull() {
            addCriterion("level_explain is not null");
            return (Criteria) this;
        }

        public Criteria andLevelExplainEqualTo(String value) {
            addCriterion("level_explain =", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainNotEqualTo(String value) {
            addCriterion("level_explain <>", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainGreaterThan(String value) {
            addCriterion("level_explain >", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainGreaterThanOrEqualTo(String value) {
            addCriterion("level_explain >=", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainLessThan(String value) {
            addCriterion("level_explain <", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainLessThanOrEqualTo(String value) {
            addCriterion("level_explain <=", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainLike(String value) {
            addCriterion("level_explain like", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainNotLike(String value) {
            addCriterion("level_explain not like", value, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainIn(List<String> values) {
            addCriterion("level_explain in", values, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainNotIn(List<String> values) {
            addCriterion("level_explain not in", values, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainBetween(String value1, String value2) {
            addCriterion("level_explain between", value1, value2, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelExplainNotBetween(String value1, String value2) {
            addCriterion("level_explain not between", value1, value2, "levelExplain");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaIsNull() {
            addCriterion("level_criteria is null");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaIsNotNull() {
            addCriterion("level_criteria is not null");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaEqualTo(String value) {
            addCriterion("level_criteria =", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaNotEqualTo(String value) {
            addCriterion("level_criteria <>", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaGreaterThan(String value) {
            addCriterion("level_criteria >", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaGreaterThanOrEqualTo(String value) {
            addCriterion("level_criteria >=", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaLessThan(String value) {
            addCriterion("level_criteria <", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaLessThanOrEqualTo(String value) {
            addCriterion("level_criteria <=", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaLike(String value) {
            addCriterion("level_criteria like", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaNotLike(String value) {
            addCriterion("level_criteria not like", value, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaIn(List<String> values) {
            addCriterion("level_criteria in", values, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaNotIn(List<String> values) {
            addCriterion("level_criteria not in", values, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaBetween(String value1, String value2) {
            addCriterion("level_criteria between", value1, value2, "levelCriteria");
            return (Criteria) this;
        }

        public Criteria andLevelCriteriaNotBetween(String value1, String value2) {
            addCriterion("level_criteria not between", value1, value2, "levelCriteria");
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