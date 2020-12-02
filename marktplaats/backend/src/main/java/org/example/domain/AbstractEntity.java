package org.example.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity<I extends Serializable> {

    @Id
    @GeneratedValue
    protected I id;

    public I getId() { return id; }

    public void setId(I id) { this.id = id; }
}
