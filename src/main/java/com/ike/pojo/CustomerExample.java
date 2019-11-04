package com.ike.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerExample() {
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

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Long value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Long value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Long value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Long value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Long value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Long> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Long> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Long value1, Long value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Long value1, Long value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdIsNull() {
            addCriterion("customer_stage_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdIsNotNull() {
            addCriterion("customer_stage_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdEqualTo(Long value) {
            addCriterion("customer_stage_id =", value, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdNotEqualTo(Long value) {
            addCriterion("customer_stage_id <>", value, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdGreaterThan(Long value) {
            addCriterion("customer_stage_id >", value, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_stage_id >=", value, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdLessThan(Long value) {
            addCriterion("customer_stage_id <", value, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_stage_id <=", value, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdIn(List<Long> values) {
            addCriterion("customer_stage_id in", values, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdNotIn(List<Long> values) {
            addCriterion("customer_stage_id not in", values, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdBetween(Long value1, Long value2) {
            addCriterion("customer_stage_id between", value1, value2, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerStageIdNotBetween(Long value1, Long value2) {
            addCriterion("customer_stage_id not between", value1, value2, "customerStageId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdIsNull() {
            addCriterion("customer_level_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdIsNotNull() {
            addCriterion("customer_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdEqualTo(Long value) {
            addCriterion("customer_level_id =", value, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdNotEqualTo(Long value) {
            addCriterion("customer_level_id <>", value, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdGreaterThan(Long value) {
            addCriterion("customer_level_id >", value, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_level_id >=", value, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdLessThan(Long value) {
            addCriterion("customer_level_id <", value, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_level_id <=", value, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdIn(List<Long> values) {
            addCriterion("customer_level_id in", values, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdNotIn(List<Long> values) {
            addCriterion("customer_level_id not in", values, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdBetween(Long value1, Long value2) {
            addCriterion("customer_level_id between", value1, value2, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIdNotBetween(Long value1, Long value2) {
            addCriterion("customer_level_id not between", value1, value2, "customerLevelId");
            return (Criteria) this;
        }

        public Criteria andCustomerStateIsNull() {
            addCriterion("customer_state is null");
            return (Criteria) this;
        }

        public Criteria andCustomerStateIsNotNull() {
            addCriterion("customer_state is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerStateEqualTo(String value) {
            addCriterion("customer_state =", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateNotEqualTo(String value) {
            addCriterion("customer_state <>", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateGreaterThan(String value) {
            addCriterion("customer_state >", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateGreaterThanOrEqualTo(String value) {
            addCriterion("customer_state >=", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateLessThan(String value) {
            addCriterion("customer_state <", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateLessThanOrEqualTo(String value) {
            addCriterion("customer_state <=", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateLike(String value) {
            addCriterion("customer_state like", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateNotLike(String value) {
            addCriterion("customer_state not like", value, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateIn(List<String> values) {
            addCriterion("customer_state in", values, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateNotIn(List<String> values) {
            addCriterion("customer_state not in", values, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateBetween(String value1, String value2) {
            addCriterion("customer_state between", value1, value2, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerStateNotBetween(String value1, String value2) {
            addCriterion("customer_state not between", value1, value2, "customerState");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdIsNull() {
            addCriterion("customer_origin_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdIsNotNull() {
            addCriterion("customer_origin_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdEqualTo(Long value) {
            addCriterion("customer_origin_id =", value, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdNotEqualTo(Long value) {
            addCriterion("customer_origin_id <>", value, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdGreaterThan(Long value) {
            addCriterion("customer_origin_id >", value, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_origin_id >=", value, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdLessThan(Long value) {
            addCriterion("customer_origin_id <", value, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_origin_id <=", value, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdIn(List<Long> values) {
            addCriterion("customer_origin_id in", values, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdNotIn(List<Long> values) {
            addCriterion("customer_origin_id not in", values, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdBetween(Long value1, Long value2) {
            addCriterion("customer_origin_id between", value1, value2, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerOriginIdNotBetween(Long value1, Long value2) {
            addCriterion("customer_origin_id not between", value1, value2, "customerOriginId");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceIsNull() {
            addCriterion("customer_introduce is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceIsNotNull() {
            addCriterion("customer_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceEqualTo(String value) {
            addCriterion("customer_introduce =", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceNotEqualTo(String value) {
            addCriterion("customer_introduce <>", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceGreaterThan(String value) {
            addCriterion("customer_introduce >", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("customer_introduce >=", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceLessThan(String value) {
            addCriterion("customer_introduce <", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceLessThanOrEqualTo(String value) {
            addCriterion("customer_introduce <=", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceLike(String value) {
            addCriterion("customer_introduce like", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceNotLike(String value) {
            addCriterion("customer_introduce not like", value, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceIn(List<String> values) {
            addCriterion("customer_introduce in", values, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceNotIn(List<String> values) {
            addCriterion("customer_introduce not in", values, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceBetween(String value1, String value2) {
            addCriterion("customer_introduce between", value1, value2, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerIntroduceNotBetween(String value1, String value2) {
            addCriterion("customer_introduce not between", value1, value2, "customerIntroduce");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNull() {
            addCriterion("customer_address is null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNotNull() {
            addCriterion("customer_address is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressEqualTo(String value) {
            addCriterion("customer_address =", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotEqualTo(String value) {
            addCriterion("customer_address <>", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThan(String value) {
            addCriterion("customer_address >", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("customer_address >=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThan(String value) {
            addCriterion("customer_address <", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThanOrEqualTo(String value) {
            addCriterion("customer_address <=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLike(String value) {
            addCriterion("customer_address like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotLike(String value) {
            addCriterion("customer_address not like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIn(List<String> values) {
            addCriterion("customer_address in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotIn(List<String> values) {
            addCriterion("customer_address not in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressBetween(String value1, String value2) {
            addCriterion("customer_address between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotBetween(String value1, String value2) {
            addCriterion("customer_address not between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Long value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Long value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Long value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Long value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Long value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Long> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Long> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Long value1, Long value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Long value1, Long value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNull() {
            addCriterion("modify_user_id is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNotNull() {
            addCriterion("modify_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdEqualTo(Long value) {
            addCriterion("modify_user_id =", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotEqualTo(Long value) {
            addCriterion("modify_user_id <>", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThan(Long value) {
            addCriterion("modify_user_id >", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("modify_user_id >=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThan(Long value) {
            addCriterion("modify_user_id <", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThanOrEqualTo(Long value) {
            addCriterion("modify_user_id <=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIn(List<Long> values) {
            addCriterion("modify_user_id in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotIn(List<Long> values) {
            addCriterion("modify_user_id not in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdBetween(Long value1, Long value2) {
            addCriterion("modify_user_id between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotBetween(Long value1, Long value2) {
            addCriterion("modify_user_id not between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(LocalDateTime value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(LocalDateTime value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(LocalDateTime value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(LocalDateTime value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<LocalDateTime> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<LocalDateTime> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
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