package com.codeup.springblog.models;

import javax.persistence.Entity;

public class RateAjax {

    private Long id;

    private String from;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
