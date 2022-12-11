package io.github.skosijer.lotr.exception;

import java.util.concurrent.CompletionException;

/**
 * Exception is triggered when trying to use endpoints with mandatory id as null.
 */
public class EntityIdNullException extends CompletionException {

    @Override
    public String getMessage() {
        return "Entity id is mandatory for fetching APIs with id parameter.";
    }
}
