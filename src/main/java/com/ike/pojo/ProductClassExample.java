package com.ike.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProductClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductClassExample() {
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

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdIsNull() {
            addCriterion("previous_class_id is null");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdIsNotNull() {
            addCriterion("previous_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdEqualTo(Long value) {
            addCriterion("previous_class_id =", value, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdNotEqualTo(Long value) {
            addCriterion("previous_class_id <>", value, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdGreaterThan(Long value) {
            addCriterion("previous_class_id >", value, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdGreaterThanOrEqualTo(Long value) {
            addCriterion("previous_class_id >=", value, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdLessThan(Long value) {
            addCriterion("previous_class_id <", value, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdLessThanOrEqualTo(Long value) {
            addCriterion("previous_class_id <=", value, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdIn(List<Long> values) {
            addCriterion("previous_class_id in", values, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdNotIn(List<Long> values) {
            addCriterion("previous_class_id not in", values, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdBetween(Long value1, Long value2) {
            addCriterion("previous_class_id between", value1, value2, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andPreviousClassIdNotBetween(Long value1, Long value2) {
            addCriterion("previous_class_id not between", value1, value2, "previousClassId");
            return (Criteria) this;
        }

        public Criteria andClassLevelIsNull() {
            addCriterion("class_level is null");
            return (Criteria) this;
        }

        public Criteria andClassLevelIsNotNull() {
            addCriterion("class_level is not null");
            return (Criteria) this;
        }

        public Criteria andClassLevelEqualTo(Byte value) {
            addCriterion("class_level =", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelNotEqualTo(Byte value) {
            addCriterion("class_level <>", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelGreaterThan(Byte value) {
            addCriterion("class_level >", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("class_level >=", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelLessThan(Byte value) {
            addCriterion("class_level <", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelLessThanOrEqualTo(Byte value) {
            addCriterion("class_level <=", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelIn(List<Byte> values) {
            addCriterion("class_level in", values, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelNotIn(List<Byte> values) {
            addCriterion("class_level not in", values, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelBetween(Byte value1, Byte value2) {
            addCriterion("class_level between", value1, value2, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("class_level not between", value1, value2, "classLevel");
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