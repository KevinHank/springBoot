package com.kevin.demo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kevin.demo.json.JsonDateSerializer;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by Kevin on 2018/8/12 0012.
 * Desc:
 */
@Entity
@Table(name="syst_user")
public class User extends BaseEntity {

    private String userName;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date birthDay;

    private String sex;

    private String address;

    @Transient
    private PagingDto pagingDto;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PagingDto getPagingDto() {
        return pagingDto;
    }

    public void setPagingDto(PagingDto pagingDto) {
        this.pagingDto = pagingDto;
    }
}
