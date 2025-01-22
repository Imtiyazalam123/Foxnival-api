package com.foxnival.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.foxnival.constant.Role;
import com.foxnival.view.Views;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSCRIBER_ID", referencedColumnName = "ID" , nullable = false)
    @JsonView({Views.UserWithSubscriber.class})
    private Subscriber subscriber;

    @Column(name = "FULL_NAME", nullable = false)
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
    private String name;

    @Column(name = "USERNAME", nullable = false, unique = true)
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
    private String username;

    @Column(name = "MOBILE_NO", nullable = false)
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
    private String mobile;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACTIVE", nullable = false, columnDefinition = "TINYINT(1)")
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
    private boolean active = true;

    @Column(name = "ROLE", nullable = false)
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
    private String role;

    @Column(name = "CREATED_DATE")
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
    private Instant createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @JsonView({Views.UserWithSubscriber.class, Views.User.class})
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
