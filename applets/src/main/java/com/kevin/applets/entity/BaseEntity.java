package com.kevin.applets.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Kevin on 2018/8/12 0012.
 * Desc:
 */
@MappedSuperclass
public class BaseEntity extends AuditedObject {

    public static final String ID = "id";

    public static final String VERSION = "version";

    @Id
    @Column(name = "ID", nullable = false, length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;

    @Version
    protected Integer version;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }
}
