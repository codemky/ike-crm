package com.ike.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComplaintExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ComplaintExample() {
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

        public Criteria andRelationIdIsNull() {
            addCriterion("relation_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationIdIsNotNull() {
            addCriterion("relation_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationIdEqualTo(Long value) {
            addCriterion("relation_id =", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotEqualTo(Long value) {
            addCriterion("relation_id <>", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThan(Long value) {
            addCriterion("relation_id >", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("relation_id >=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThan(Long value) {
            addCriterion("relation_id <", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThanOrEqualTo(Long value) {
            addCriterion("relation_id <=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdIn(List<Long> values) {
            addCriterion("relation_id in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotIn(List<Long> values) {
            addCriterion("relation_id not in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdBetween(Long value1, Long value2) {
            addCriterion("relation_id between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotBetween(Long value1, Long value2) {
            addCriterion("relation_id not between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeIsNull() {
            addCriterion("complaint_theme is null");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeIsNotNull() {
            addCriterion("complaint_theme is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeEqualTo(String value) {
            addCriterion("complaint_theme =", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeNotEqualTo(String value) {
            addCriterion("complaint_theme <>", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeGreaterThan(String value) {
            addCriterion("complaint_theme >", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeGreaterThanOrEqualTo(String value) {
            addCriterion("complaint_theme >=", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeLessThan(String value) {
            addCriterion("complaint_theme <", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeLessThanOrEqualTo(String value) {
            addCriterion("complaint_theme <=", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeLike(String value) {
            addCriterion("complaint_theme like", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeNotLike(String value) {
            addCriterion("complaint_theme not like", value, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeIn(List<String> values) {
            addCriterion("complaint_theme in", values, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeNotIn(List<String> values) {
            addCriterion("complaint_theme not in", values, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeBetween(String value1, String value2) {
            addCriterion("complaint_theme between", value1, value2, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintThemeNotBetween(String value1, String value2) {
            addCriterion("complaint_theme not between", value1, value2, "complaintTheme");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeIsNull() {
            addCriterion("complaint_time is null");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeIsNotNull() {
            addCriterion("complaint_time is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeEqualTo(LocalDateTime value) {
            addCriterion("complaint_time =", value, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeNotEqualTo(LocalDateTime value) {
            addCriterion("complaint_time <>", value, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeGreaterThan(LocalDateTime value) {
            addCriterion("complaint_time >", value, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("complaint_time >=", value, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeLessThan(LocalDateTime value) {
            addCriterion("complaint_time <", value, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("complaint_time <=", value, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeIn(List<LocalDateTime> values) {
            addCriterion("complaint_time in", values, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeNotIn(List<LocalDateTime> values) {
            addCriterion("complaint_time not in", values, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("complaint_time between", value1, value2, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("complaint_time not between", value1, value2, "complaintTime");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIsNull() {
            addCriterion("complaint_type is null");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIsNotNull() {
            addCriterion("complaint_type is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeEqualTo(String value) {
            addCriterion("complaint_type =", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotEqualTo(String value) {
            addCriterion("complaint_type <>", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeGreaterThan(String value) {
            addCriterion("complaint_type >", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeGreaterThanOrEqualTo(String value) {
            addCriterion("complaint_type >=", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLessThan(String value) {
            addCriterion("complaint_type <", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLessThanOrEqualTo(String value) {
            addCriterion("complaint_type <=", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLike(String value) {
            addCriterion("complaint_type like", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotLike(String value) {
            addCriterion("complaint_type not like", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIn(List<String> values) {
            addCriterion("complaint_type in", values, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotIn(List<String> values) {
            addCriterion("complaint_type not in", values, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeBetween(String value1, String value2) {
            addCriterion("complaint_type between", value1, value2, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotBetween(String value1, String value2) {
            addCriterion("complaint_type not between", value1, value2, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodIsNull() {
            addCriterion("complaint_method is null");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodIsNotNull() {
            addCriterion("complaint_method is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodEqualTo(String value) {
            addCriterion("complaint_method =", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodNotEqualTo(String value) {
            addCriterion("complaint_method <>", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodGreaterThan(String value) {
            addCriterion("complaint_method >", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodGreaterThanOrEqualTo(String value) {
            addCriterion("complaint_method >=", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodLessThan(String value) {
            addCriterion("complaint_method <", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodLessThanOrEqualTo(String value) {
            addCriterion("complaint_method <=", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodLike(String value) {
            addCriterion("complaint_method like", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodNotLike(String value) {
            addCriterion("complaint_method not like", value, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodIn(List<String> values) {
            addCriterion("complaint_method in", values, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodNotIn(List<String> values) {
            addCriterion("complaint_method not in", values, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodBetween(String value1, String value2) {
            addCriterion("complaint_method between", value1, value2, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintMethodNotBetween(String value1, String value2) {
            addCriterion("complaint_method not between", value1, value2, "complaintMethod");
            return (Criteria) this;
        }

        public Criteria andComplaintContentIsNull() {
            addCriterion("complaint_content is null");
            return (Criteria) this;
        }

        public Criteria andComplaintContentIsNotNull() {
            addCriterion("complaint_content is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintContentEqualTo(String value) {
            addCriterion("complaint_content =", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentNotEqualTo(String value) {
            addCriterion("complaint_content <>", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentGreaterThan(String value) {
            addCriterion("complaint_content >", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentGreaterThanOrEqualTo(String value) {
            addCriterion("complaint_content >=", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentLessThan(String value) {
            addCriterion("complaint_content <", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentLessThanOrEqualTo(String value) {
            addCriterion("complaint_content <=", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentLike(String value) {
            addCriterion("complaint_content like", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentNotLike(String value) {
            addCriterion("complaint_content not like", value, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentIn(List<String> values) {
            addCriterion("complaint_content in", values, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentNotIn(List<String> values) {
            addCriterion("complaint_content not in", values, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentBetween(String value1, String value2) {
            addCriterion("complaint_content between", value1, value2, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andComplaintContentNotBetween(String value1, String value2) {
            addCriterion("complaint_content not between", value1, value2, "complaintContent");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelIsNull() {
            addCriterion("urgency_level is null");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelIsNotNull() {
            addCriterion("urgency_level is not null");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelEqualTo(String value) {
            addCriterion("urgency_level =", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelNotEqualTo(String value) {
            addCriterion("urgency_level <>", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelGreaterThan(String value) {
            addCriterion("urgency_level >", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelGreaterThanOrEqualTo(String value) {
            addCriterion("urgency_level >=", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelLessThan(String value) {
            addCriterion("urgency_level <", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelLessThanOrEqualTo(String value) {
            addCriterion("urgency_level <=", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelLike(String value) {
            addCriterion("urgency_level like", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelNotLike(String value) {
            addCriterion("urgency_level not like", value, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelIn(List<String> values) {
            addCriterion("urgency_level in", values, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelNotIn(List<String> values) {
            addCriterion("urgency_level not in", values, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelBetween(String value1, String value2) {
            addCriterion("urgency_level between", value1, value2, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andUrgencyLevelNotBetween(String value1, String value2) {
            addCriterion("urgency_level not between", value1, value2, "urgencyLevel");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneIsNull() {
            addCriterion("complaint_phone is null");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneIsNotNull() {
            addCriterion("complaint_phone is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneEqualTo(String value) {
            addCriterion("complaint_phone =", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneNotEqualTo(String value) {
            addCriterion("complaint_phone <>", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneGreaterThan(String value) {
            addCriterion("complaint_phone >", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("complaint_phone >=", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneLessThan(String value) {
            addCriterion("complaint_phone <", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneLessThanOrEqualTo(String value) {
            addCriterion("complaint_phone <=", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneLike(String value) {
            addCriterion("complaint_phone like", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneNotLike(String value) {
            addCriterion("complaint_phone not like", value, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneIn(List<String> values) {
            addCriterion("complaint_phone in", values, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneNotIn(List<String> values) {
            addCriterion("complaint_phone not in", values, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneBetween(String value1, String value2) {
            addCriterion("complaint_phone between", value1, value2, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andComplaintPhoneNotBetween(String value1, String value2) {
            addCriterion("complaint_phone not between", value1, value2, "complaintPhone");
            return (Criteria) this;
        }

        public Criteria andChargeIdIsNull() {
            addCriterion("charge_id is null");
            return (Criteria) this;
        }

        public Criteria andChargeIdIsNotNull() {
            addCriterion("charge_id is not null");
            return (Criteria) this;
        }

        public Criteria andChargeIdEqualTo(Long value) {
            addCriterion("charge_id =", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdNotEqualTo(Long value) {
            addCriterion("charge_id <>", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdGreaterThan(Long value) {
            addCriterion("charge_id >", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("charge_id >=", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdLessThan(Long value) {
            addCriterion("charge_id <", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdLessThanOrEqualTo(Long value) {
            addCriterion("charge_id <=", value, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdIn(List<Long> values) {
            addCriterion("charge_id in", values, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdNotIn(List<Long> values) {
            addCriterion("charge_id not in", values, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdBetween(Long value1, Long value2) {
            addCriterion("charge_id between", value1, value2, "chargeId");
            return (Criteria) this;
        }

        public Criteria andChargeIdNotBetween(Long value1, Long value2) {
            addCriterion("charge_id not between", value1, value2, "chargeId");
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