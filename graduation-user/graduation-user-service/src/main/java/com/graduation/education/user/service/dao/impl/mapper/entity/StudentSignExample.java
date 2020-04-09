package com.graduation.education.user.service.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentSignExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public StudentSignExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andStudentNoIsNull() {
            addCriterion("student_no is null");
            return (Criteria) this;
        }

        public Criteria andStudentNoIsNotNull() {
            addCriterion("student_no is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNoEqualTo(String value) {
            addCriterion("student_no =", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotEqualTo(String value) {
            addCriterion("student_no <>", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoGreaterThan(String value) {
            addCriterion("student_no >", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoGreaterThanOrEqualTo(String value) {
            addCriterion("student_no >=", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoLessThan(String value) {
            addCriterion("student_no <", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoLessThanOrEqualTo(String value) {
            addCriterion("student_no <=", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoLike(String value) {
            addCriterion("student_no like", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotLike(String value) {
            addCriterion("student_no not like", value, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoIn(List<String> values) {
            addCriterion("student_no in", values, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotIn(List<String> values) {
            addCriterion("student_no not in", values, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoBetween(String value1, String value2) {
            addCriterion("student_no between", value1, value2, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNoNotBetween(String value1, String value2) {
            addCriterion("student_no not between", value1, value2, "studentNo");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNull() {
            addCriterion("student_name is null");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNotNull() {
            addCriterion("student_name is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNameEqualTo(String value) {
            addCriterion("student_name =", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotEqualTo(String value) {
            addCriterion("student_name <>", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThan(String value) {
            addCriterion("student_name >", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThanOrEqualTo(String value) {
            addCriterion("student_name >=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThan(String value) {
            addCriterion("student_name <", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThanOrEqualTo(String value) {
            addCriterion("student_name <=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLike(String value) {
            addCriterion("student_name like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotLike(String value) {
            addCriterion("student_name not like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameIn(List<String> values) {
            addCriterion("student_name in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotIn(List<String> values) {
            addCriterion("student_name not in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameBetween(String value1, String value2) {
            addCriterion("student_name between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotBetween(String value1, String value2) {
            addCriterion("student_name not between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andClassNoIsNull() {
            addCriterion("class_no is null");
            return (Criteria) this;
        }

        public Criteria andClassNoIsNotNull() {
            addCriterion("class_no is not null");
            return (Criteria) this;
        }

        public Criteria andClassNoEqualTo(String value) {
            addCriterion("class_no =", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoNotEqualTo(String value) {
            addCriterion("class_no <>", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoGreaterThan(String value) {
            addCriterion("class_no >", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoGreaterThanOrEqualTo(String value) {
            addCriterion("class_no >=", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoLessThan(String value) {
            addCriterion("class_no <", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoLessThanOrEqualTo(String value) {
            addCriterion("class_no <=", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoLike(String value) {
            addCriterion("class_no like", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoNotLike(String value) {
            addCriterion("class_no not like", value, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoIn(List<String> values) {
            addCriterion("class_no in", values, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoNotIn(List<String> values) {
            addCriterion("class_no not in", values, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoBetween(String value1, String value2) {
            addCriterion("class_no between", value1, value2, "classNo");
            return (Criteria) this;
        }

        public Criteria andClassNoNotBetween(String value1, String value2) {
            addCriterion("class_no not between", value1, value2, "classNo");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandIsNull() {
            addCriterion("phone_brand is null");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandIsNotNull() {
            addCriterion("phone_brand is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandEqualTo(String value) {
            addCriterion("phone_brand =", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotEqualTo(String value) {
            addCriterion("phone_brand <>", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandGreaterThan(String value) {
            addCriterion("phone_brand >", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandGreaterThanOrEqualTo(String value) {
            addCriterion("phone_brand >=", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandLessThan(String value) {
            addCriterion("phone_brand <", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandLessThanOrEqualTo(String value) {
            addCriterion("phone_brand <=", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandLike(String value) {
            addCriterion("phone_brand like", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotLike(String value) {
            addCriterion("phone_brand not like", value, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandIn(List<String> values) {
            addCriterion("phone_brand in", values, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotIn(List<String> values) {
            addCriterion("phone_brand not in", values, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandBetween(String value1, String value2) {
            addCriterion("phone_brand between", value1, value2, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneBrandNotBetween(String value1, String value2) {
            addCriterion("phone_brand not between", value1, value2, "phoneBrand");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIsNull() {
            addCriterion("phone_model is null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIsNotNull() {
            addCriterion("phone_model is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelEqualTo(String value) {
            addCriterion("phone_model =", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotEqualTo(String value) {
            addCriterion("phone_model <>", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThan(String value) {
            addCriterion("phone_model >", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThanOrEqualTo(String value) {
            addCriterion("phone_model >=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThan(String value) {
            addCriterion("phone_model <", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThanOrEqualTo(String value) {
            addCriterion("phone_model <=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLike(String value) {
            addCriterion("phone_model like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotLike(String value) {
            addCriterion("phone_model not like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIn(List<String> values) {
            addCriterion("phone_model in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotIn(List<String> values) {
            addCriterion("phone_model not in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelBetween(String value1, String value2) {
            addCriterion("phone_model between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotBetween(String value1, String value2) {
            addCriterion("phone_model not between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("point is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("point is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(String value) {
            addCriterion("point =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(String value) {
            addCriterion("point <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(String value) {
            addCriterion("point >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(String value) {
            addCriterion("point >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(String value) {
            addCriterion("point <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(String value) {
            addCriterion("point <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLike(String value) {
            addCriterion("point like", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotLike(String value) {
            addCriterion("point not like", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<String> values) {
            addCriterion("point in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<String> values) {
            addCriterion("point not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(String value1, String value2) {
            addCriterion("point between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(String value1, String value2) {
            addCriterion("point not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andCoordtypeIsNull() {
            addCriterion("coordType is null");
            return (Criteria) this;
        }

        public Criteria andCoordtypeIsNotNull() {
            addCriterion("coordType is not null");
            return (Criteria) this;
        }

        public Criteria andCoordtypeEqualTo(String value) {
            addCriterion("coordType =", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeNotEqualTo(String value) {
            addCriterion("coordType <>", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeGreaterThan(String value) {
            addCriterion("coordType >", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeGreaterThanOrEqualTo(String value) {
            addCriterion("coordType >=", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeLessThan(String value) {
            addCriterion("coordType <", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeLessThanOrEqualTo(String value) {
            addCriterion("coordType <=", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeLike(String value) {
            addCriterion("coordType like", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeNotLike(String value) {
            addCriterion("coordType not like", value, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeIn(List<String> values) {
            addCriterion("coordType in", values, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeNotIn(List<String> values) {
            addCriterion("coordType not in", values, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeBetween(String value1, String value2) {
            addCriterion("coordType between", value1, value2, "coordtype");
            return (Criteria) this;
        }

        public Criteria andCoordtypeNotBetween(String value1, String value2) {
            addCriterion("coordType not between", value1, value2, "coordtype");
            return (Criteria) this;
        }

        public Criteria andChangePhoneIsNull() {
            addCriterion("change_phone is null");
            return (Criteria) this;
        }

        public Criteria andChangePhoneIsNotNull() {
            addCriterion("change_phone is not null");
            return (Criteria) this;
        }

        public Criteria andChangePhoneEqualTo(Byte value) {
            addCriterion("change_phone =", value, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneNotEqualTo(Byte value) {
            addCriterion("change_phone <>", value, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneGreaterThan(Byte value) {
            addCriterion("change_phone >", value, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneGreaterThanOrEqualTo(Byte value) {
            addCriterion("change_phone >=", value, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneLessThan(Byte value) {
            addCriterion("change_phone <", value, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneLessThanOrEqualTo(Byte value) {
            addCriterion("change_phone <=", value, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneIn(List<Byte> values) {
            addCriterion("change_phone in", values, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneNotIn(List<Byte> values) {
            addCriterion("change_phone not in", values, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneBetween(Byte value1, Byte value2) {
            addCriterion("change_phone between", value1, value2, "changePhone");
            return (Criteria) this;
        }

        public Criteria andChangePhoneNotBetween(Byte value1, Byte value2) {
            addCriterion("change_phone not between", value1, value2, "changePhone");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }
    }

    /**
     */
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