package com.example.test.simpleroomdbdemo.domain.model;

public class Account {

    private String name="";

    private String password="";

    private Account(Builder builder) {
        name = builder.name;
        password = builder.password;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private String password;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
