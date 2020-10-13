package com.rest.employ.entity;

import com.rest.employ.controller.Status;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustumerOrder {

    @Id
    @GeneratedValue
    private long id;

    private String description;
    private Status status;

    public CustumerOrder() {}

    public CustumerOrder(String description, Status status) {

        this.description = description;
        this.status = status;
    }

    public long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof CustumerOrder))
            return false;
        CustumerOrder order = (CustumerOrder) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
            && this.status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.status);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + '}';
    }
}