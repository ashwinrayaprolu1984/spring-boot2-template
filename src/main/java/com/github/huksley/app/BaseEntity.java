package com.github.huksley.app;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Version;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Basic JPA entity. All Entities should be derived from this entity.
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(exclude = { "version", "created", "updated" })
@ToString(exclude = { "version", "created", "updated" })
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * String based ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    protected String id;

    /**
     * Version of record. Incremented on each update via JPA.
     */
    @Version
    @Column(name = "version")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    protected Long version = 1L;

    /**
     * Date when the record have been created in database.
     */
    @Column(name = "created")
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ssZZZ", timezone = "UTC")
    @ApiModelProperty(hidden = true)
    protected Calendar created;

    /**
     * Date when the record have been updated via JPA.
     */
	@Column(name = "updated")
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ssZZZ", timezone = "UTC")
    @ApiModelProperty(hidden = true)
    protected Calendar updated;
}
