package com.kirak.to.abstr;

/**
 * Created by Kir on 13.08.2017.
 */
public interface HasId {

    Number getId();

    void setId(Number id);

    default boolean isNew() {
        return (getId() == null);
    }
}
