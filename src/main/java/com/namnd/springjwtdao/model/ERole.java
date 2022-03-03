package com.namnd.springjwtdao.model;

public enum ERole {
    ROLE_USER(1),
    ROLE_ADMIN(3);

    private long value;

    public Long getValue(){
        return value;
    }

    ERole(long value) {
        this.value = value;
    }
}