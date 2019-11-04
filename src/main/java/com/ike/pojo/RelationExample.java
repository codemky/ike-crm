package com.ike.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RelationExample() {
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

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Long value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Long value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Long value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Long value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Long> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Long> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Long value1, Long value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Long value1, Long value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andRelationNameIsNull() {
            addCriterion("relation_name is null");
            return (Criteria) this;
        }

        public Criteria andRelationNameIsNotNull() {
            addCriterion("relation_name is not null");
            return (Criteria) this;
        }

        public Criteria andRelationNameEqualTo(String value) {
            addCriterion("relation_name =", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotEqualTo(String value) {
            addCriterion("relation_name <>", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameGreaterThan(String value) {
            addCriterion("relation_name >", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameGreaterThanOrEqualTo(String value) {
            addCriterion("relation_name >=", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLessThan(String value) {
            addCriterion("relation_name <", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLessThanOrEqualTo(String value) {
            addCriterion("relation_name <=", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLike(String value) {
            addCriterion("relation_name like", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotLike(String value) {
            addCriterion("relation_name not like", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameIn(List<String> values) {
            addCriterion("relation_name in", values, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotIn(List<String> values) {
            addCriterion("relation_name not in", values, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameBetween(String value1, String value2) {
            addCriterion("relation_name between", value1, value2, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotBetween(String value1, String value2) {
            addCriterion("relation_name not between", value1, value2, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationSexIsNull() {
            addCriterion("relation_sex is null");
            return (Criteria) this;
        }

        public Criteria andRelationSexIsNotNull() {
            addCriterion("relation_sex is not null");
            return (Criteria) this;
        }

        public Criteria andRelationSexEqualTo(Boolean value) {
            addCriterion("relation_sex =", value, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexNotEqualTo(Boolean value) {
            addCriterion("relation_sex <>", value, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexGreaterThan(Boolean value) {
            addCriterion("relation_sex >", value, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexGreaterThanOrEqualTo(Boolean value) {
            addCriterion("relation_sex >=", value, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexLessThan(Boolean value) {
            addCriterion("relation_sex <", value, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexLessThanOrEqualTo(Boolean value) {
            addCriterion("relation_sex <=", value, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexIn(List<Boolean> values) {
            addCriterion("relation_sex in", values, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexNotIn(List<Boolean> values) {
            addCriterion("relation_sex not in", values, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexBetween(Boolean value1, Boolean value2) {
            addCriterion("relation_sex between", value1, value2, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationSexNotBetween(Boolean value1, Boolean value2) {
            addCriterion("relation_sex not between", value1, value2, "relationSex");
            return (Criteria) this;
        }

        public Criteria andRelationPositionIsNull() {
            addCriterion("relation_position is null");
            return (Criteria) this;
        }

        public Criteria andRelationPositionIsNotNull() {
            addCriterion("relation_position is not null");
            return (Criteria) this;
        }

        public Criteria andRelationPositionEqualTo(String value) {
            addCriterion("relation_position =", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionNotEqualTo(String value) {
            addCriterion("relation_position <>", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionGreaterThan(String value) {
            addCriterion("relation_position >", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionGreaterThanOrEqualTo(String value) {
            addCriterion("relation_position >=", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionLessThan(String value) {
            addCriterion("relation_position <", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionLessThanOrEqualTo(String value) {
            addCriterion("relation_position <=", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionLike(String value) {
            addCriterion("relation_position like", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionNotLike(String value) {
            addCriterion("relation_position not like", value, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionIn(List<String> values) {
            addCriterion("relation_position in", values, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionNotIn(List<String> values) {
            addCriterion("relation_position not in", values, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionBetween(String value1, String value2) {
            addCriterion("relation_position between", value1, value2, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationPositionNotBetween(String value1, String value2) {
            addCriterion("relation_position not between", value1, value2, "relationPosition");
            return (Criteria) this;
        }

        public Criteria andRelationCallIsNull() {
            addCriterion("relation_call is null");
            return (Criteria) this;
        }

        public Criteria andRelationCallIsNotNull() {
            addCriterion("relation_call is not null");
            return (Criteria) this;
        }

        public Criteria andRelationCallEqualTo(String value) {
            addCriterion("relation_call =", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallNotEqualTo(String value) {
            addCriterion("relation_call <>", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallGreaterThan(String value) {
            addCriterion("relation_call >", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallGreaterThanOrEqualTo(String value) {
            addCriterion("relation_call >=", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallLessThan(String value) {
            addCriterion("relation_call <", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallLessThanOrEqualTo(String value) {
            addCriterion("relation_call <=", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallLike(String value) {
            addCriterion("relation_call like", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallNotLike(String value) {
            addCriterion("relation_call not like", value, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallIn(List<String> values) {
            addCriterion("relation_call in", values, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallNotIn(List<String> values) {
            addCriterion("relation_call not in", values, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallBetween(String value1, String value2) {
            addCriterion("relation_call between", value1, value2, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationCallNotBetween(String value1, String value2) {
            addCriterion("relation_call not between", value1, value2, "relationCall");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneIsNull() {
            addCriterion("relation_phone is null");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneIsNotNull() {
            addCriterion("relation_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneEqualTo(String value) {
            addCriterion("relation_phone =", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneNotEqualTo(String value) {
            addCriterion("relation_phone <>", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneGreaterThan(String value) {
            addCriterion("relation_phone >", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("relation_phone >=", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneLessThan(String value) {
            addCriterion("relation_phone <", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneLessThanOrEqualTo(String value) {
            addCriterion("relation_phone <=", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneLike(String value) {
            addCriterion("relation_phone like", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneNotLike(String value) {
            addCriterion("relation_phone not like", value, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneIn(List<String> values) {
            addCriterion("relation_phone in", values, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneNotIn(List<String> values) {
            addCriterion("relation_phone not in", values, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneBetween(String value1, String value2) {
            addCriterion("relation_phone between", value1, value2, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationPhoneNotBetween(String value1, String value2) {
            addCriterion("relation_phone not between", value1, value2, "relationPhone");
            return (Criteria) this;
        }

        public Criteria andRelationEmailIsNull() {
            addCriterion("relation_email is null");
            return (Criteria) this;
        }

        public Criteria andRelationEmailIsNotNull() {
            addCriterion("relation_email is not null");
            return (Criteria) this;
        }

        public Criteria andRelationEmailEqualTo(String value) {
            addCriterion("relation_email =", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailNotEqualTo(String value) {
            addCriterion("relation_email <>", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailGreaterThan(String value) {
            addCriterion("relation_email >", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailGreaterThanOrEqualTo(String value) {
            addCriterion("relation_email >=", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailLessThan(String value) {
            addCriterion("relation_email <", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailLessThanOrEqualTo(String value) {
            addCriterion("relation_email <=", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailLike(String value) {
            addCriterion("relation_email like", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailNotLike(String value) {
            addCriterion("relation_email not like", value, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailIn(List<String> values) {
            addCriterion("relation_email in", values, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailNotIn(List<String> values) {
            addCriterion("relation_email not in", values, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailBetween(String value1, String value2) {
            addCriterion("relation_email between", value1, value2, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationEmailNotBetween(String value1, String value2) {
            addCriterion("relation_email not between", value1, value2, "relationEmail");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryIsNull() {
            addCriterion("relation_primary is null");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryIsNotNull() {
            addCriterion("relation_primary is not null");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryEqualTo(Byte value) {
            addCriterion("relation_primary =", value, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryNotEqualTo(Byte value) {
            addCriterion("relation_primary <>", value, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryGreaterThan(Byte value) {
            addCriterion("relation_primary >", value, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryGreaterThanOrEqualTo(Byte value) {
            addCriterion("relation_primary >=", value, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryLessThan(Byte value) {
            addCriterion("relation_primary <", value, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryLessThanOrEqualTo(Byte value) {
            addCriterion("relation_primary <=", value, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryIn(List<Byte> values) {
            addCriterion("relation_primary in", values, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryNotIn(List<Byte> values) {
            addCriterion("relation_primary not in", values, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryBetween(Byte value1, Byte value2) {
            addCriterion("relation_primary between", value1, value2, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andRelationPrimaryNotBetween(Byte value1, Byte value2) {
            addCriterion("relation_primary not between", value1, value2, "relationPrimary");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberIsNull() {
            addCriterion("landline_number is null");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberIsNotNull() {
            addCriterion("landline_number is not null");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberEqualTo(String value) {
            addCriterion("landline_number =", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberNotEqualTo(String value) {
            addCriterion("landline_number <>", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberGreaterThan(String value) {
            addCriterion("landline_number >", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberGreaterThanOrEqualTo(String value) {
            addCriterion("landline_number >=", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberLessThan(String value) {
            addCriterion("landline_number <", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberLessThanOrEqualTo(String value) {
            addCriterion("landline_number <=", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberLike(String value) {
            addCriterion("landline_number like", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberNotLike(String value) {
            addCriterion("landline_number not like", value, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberIn(List<String> values) {
            addCriterion("landline_number in", values, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberNotIn(List<String> values) {
            addCriterion("landline_number not in", values, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberBetween(String value1, String value2) {
            addCriterion("landline_number between", value1, value2, "landlineNumber");
            return (Criteria) this;
        }

        public Criteria andLandlineNumberNotBetween(String value1, String value2) {
            addCriterion("landline_number not between", value1, value2, "landlineNumber");
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