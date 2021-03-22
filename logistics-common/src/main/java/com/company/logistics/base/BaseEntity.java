package com.company.logistics.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公用实体
 * @author wzj
 * @date 2020/12/24 22:40
 */
public class BaseEntity implements Serializable {

    protected Long id;

    protected Date createDate;

    protected Date updateDate;

    protected String createUser;

    protected String updateUser;

    protected Boolean deleted;

    public BaseEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
