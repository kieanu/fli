package com.leepark.fli.model.dtoId;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_sns")
    private Integer userSns;

    public UserId() {
    }

    public UserId(String userId, Integer userSns) {
        this.userId = userId;
        this.userSns = userSns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId1 = (UserId) o;
        return Objects.equals(userId, userId1.userId) &&
                Objects.equals(userSns, userId1.userSns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userSns);
    }
}