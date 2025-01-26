package com.foxnival.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.foxnival.constant.Source;
import com.foxnival.view.Views;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSCRIBER_ID", referencedColumnName = "ID" , nullable = false)
    private Subscriber subscriber;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "MOBILE_NO", nullable = false)
    private String mobileNo;

    @Column(name = "PURPOSE", nullable = false)
    private String purpose;

    @Column(name = "SOURCE", nullable = false)
    private Source source;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "COMMENTS")
    private String comments;

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
