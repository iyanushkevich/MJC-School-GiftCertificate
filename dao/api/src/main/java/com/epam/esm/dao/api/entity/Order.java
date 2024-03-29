package com.epam.esm.dao.api.entity;


import com.epam.esm.dao.api.audit.OrderAuditListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Order.
 */
@EntityListeners(OrderAuditListener.class)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "gift_certificate_id")
    private Long giftCertificateId;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param id                the id
     * @param userId            the user id
     * @param giftCertificateId the gift certificate id
     * @param cost              the cost
     * @param purchaseDate      the purchase date
     */
    public Order(Long id, Long userId, Long giftCertificateId, BigDecimal cost, LocalDateTime purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.giftCertificateId = giftCertificateId;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets gift certificate id.
     *
     * @return the gift certificate id
     */
    public Long getGiftCertificateId() {
        return giftCertificateId;
    }

    /**
     * Sets gift certificate id.
     *
     * @param giftCertificateId the gift certificate id
     */
    public void setGiftCertificateId(Long giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Gets purchase date.
     *
     * @return the purchase date
     */
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets purchase date.
     *
     * @param purchaseDate the purchase date
     */
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(giftCertificateId, order.giftCertificateId) &&
                Objects.equals(cost, order.cost) &&
                Objects.equals(purchaseDate, order.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, giftCertificateId, cost, purchaseDate);
    }
}
