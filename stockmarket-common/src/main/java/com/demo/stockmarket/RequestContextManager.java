package com.demo.stockmarket;


public final class RequestContextManager {

    private static InheritableThreadLocal<RequestContext> context = new InheritableThreadLocal<>();

    private RequestContextManager() {
    }

    /**
     * Removes the context from this manager.
     */
    public static void removeContext() {
        context.remove();
    }

    /**
     * Returns the current thread context.
     *
     * @return context
     */
    public static RequestContext getContext() {
        RequestContext result = context.get();
        if (result == null) {
            result = new RequestContext();
            setContext(result);
        }
        return result;
    }

    /**
     * Sets the context in this manager.
     *
     * @param context context
     */
    public static void setContext(final RequestContext context) {
        RequestContextManager.context.set(context);
    }
}
