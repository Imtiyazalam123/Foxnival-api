package com.foxnival.entity;

import com.foxnival.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENT_DETAILS")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @Column(name = "ORDER_ID", nullable = false)
    private String orderId;

    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    @Column(name = "PAYMENT_ID")
    private String paymentId;

    @Column(name = "PAYMENT_SIGNATURE")
    private String paymentSignature;

    @Column(name = "ERROR_CODE")
    private String errorCode;

    @Column(name = "FAILED_REASON")
    private String failedReason;

    @Column(name = "FAILED_AT_STEP")
    private String failedStep;

    @Column(name = "FAILED_SOURCE")
    private String failedSource;

    @Column(name = "FAILED_DESCRIPTION")
    private String failedDescription;

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
