package com.foxnival.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SUBSCRIBER")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ORGANIZATION_NAME", nullable = false)
    private String organizationName;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "SUBSCRIBED_DATE", nullable = false)
    private Instant subscribedDate;

    @Column(name = "VALIDITY_DATE", nullable = false)
    private Instant validityDate;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = Instant.now();
    }
}
