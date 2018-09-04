/*
 * $Id: AuditedObject.java 12548 2014-11-28 07:58:23Z jliu $
 * 
 * Copyright (c) 2001-2008 Accentrix Company Limited. All Rights Reserved.
 */
package com.kevin.applets.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kevin.applets.json.JsonDateTimeDeserialize;
import com.kevin.applets.json.JsonDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class AuditedObject implements Serializable {

    private final static Logger log = LoggerFactory.getLogger(AuditedObject.class);

    public static final String CREATED_DATE = "createdDate";

    public static final String LAST_UPDATED_DATE = "lastUpdatedDate";

    @Column(name = "CREATED_DATE", nullable = true, updatable = false)
    protected Date createdDate;

    @Column(name = "LAST_UPDATED_DATE")
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    protected Date lastUpdatedDate;

    @PrePersist
    @PreUpdate
    public void updateAuditInfo() {
        Date now = new Date();
        if (createdDate == null) {
            setCreatedDate(now);
            setLastUpdatedDate(now);
        } else {
            setLastUpdatedDate(now);
        }
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getCreatedDate() {
        return createdDate;
    }

    @JsonDeserialize(using = JsonDateTimeDeserialize.class)
    public void setCreatedDate(@Temporal(TemporalType.TIMESTAMP) final Date createdDate) {
        this.createdDate = createdDate;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @JsonDeserialize(using = JsonDateTimeDeserialize.class)
    public void setLastUpdatedDate(@Temporal(TemporalType.TIMESTAMP) final Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

}
