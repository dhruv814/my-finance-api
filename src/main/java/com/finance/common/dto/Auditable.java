package com.finance.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author dhruvkumar
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "createTime", "updatedBy", "updateTime", "version"},
        allowGetters = true
)
@Getter
@Setter
public abstract class Auditable implements Serializable {

    @CreatedBy
    @Column(name = "create_id")
    protected Long createdBy;

    @CreatedDate
    @Column(name = "create_time", nullable = false, updatable = false)
    protected LocalDateTime createTime;

    @LastModifiedBy
    @Column(name = "update_id")
    protected Long updatedBy;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    protected LocalDateTime updateTime;

    @Version
    @Column(name = "version", nullable = false)
    protected int version;
}
