package com.foxnival.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.foxnival.view.Views;
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
    @JsonView(Views.Subscriber.class)
    private Long id;

    @Column(name = "ORGANIZATION_NAME", nullable = false)
    @JsonView(Views.Subscriber.class)
    private String organizationName;

    @Column(name = "ACTIVE", nullable = false, columnDefinition = "TINYINT(1)")
    @JsonView(Views.Subscriber.class)
    private boolean active;

    @Column(name = "PLANE_FOR_YEAR", nullable = false)
    @JsonView(Views.Subscriber.class)
    private String planForYear;

    @Column(name = "SUBSCRIBED_DATE", nullable = false)
    @JsonView(Views.Subscriber.class)
    private Instant subscribedDate;

    @Column(name = "VALIDITY_DATE", nullable = false)
    @JsonView(Views.Subscriber.class)
    private Instant validityDate;

    @Column(name = "CREATED_DATE")
    @JsonView(Views.Subscriber.class)
    private Instant createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @JsonView(Views.Subscriber.class)
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
