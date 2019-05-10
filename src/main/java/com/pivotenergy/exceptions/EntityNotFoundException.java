package com.pivotenergy.exceptions;

public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new <code>EntityNotFoundException</code> exception with
     * <code>null</code> as its detail message.
     */
    public EntityNotFoundException() {
        super();
    }

    /**
     * Constructs a new <code>EntityNotFoundException</code> exception with the
     * specified detail message.
     *
     * @param message
     *            the detail message.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new <code>EntityNotFoundException</code> exception
     * with the specified detail message and cause.
     * @param   message   the detail message.
     * @param   cause     the cause.
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new <code>EntityNotFoundException</code> exception
     * with the specified cause.
     * @param   cause     the cause.
     */
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

}
