package com.demo.stockmarket;

import java.util.Date;

public class RequestContext {

    /**
     * Request header representing the user name.
     */
    public static final String HEADER_KEY_TOKEN = "TOKEN";

    public static final String HEADER_KEY_USER_ROLES = "user.roles";

    public static final String HEADER_KEY_USER_EMAIL = "user.email";
    
    public static final String UNKNOWN = "unknown";

    private final long timestamp = new Date().getTime();

    private String email;
    private String role;

    /**
     * Constructor.
     */
    public RequestContext() {
        this.email=null;
        this.role = null;
    }

    protected RequestContext(
        final String email,
        final String role
    ) {
        this.email=email;
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestContext{");
        sb.append("email='").append(email).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * builder class for MetacatRequestContext.
     * @return the builder class for MetacatRequestContext
     */
    public static RequestContext.RequestContextBuilder builder() {
        return new RequestContext.RequestContextBuilder();
    }

    /**
     * MetacatRequestContext builder class.
     */
    public static class RequestContextBuilder {
        private String bEmail;
        private String bRole;

        RequestContextBuilder() {
            this.bEmail = UNKNOWN;
            this.bRole = UNKNOWN;
        }

        /**
         * set email.
         *
         * @param email user name at client side
         * @return the builder
         */
        public RequestContext.RequestContextBuilder email(final String email) {
            this.bEmail = email;
            return this;
        }

        public RequestContext.RequestContextBuilder role(final String role) {
            this.bRole = role;
            return this;
        }

        /**
         * builder.
         *
         * @return MetacatRequestContext object
         */
        public RequestContext build() {
            return new RequestContext(
                this.bEmail,
                this.bRole
            );
        }
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
