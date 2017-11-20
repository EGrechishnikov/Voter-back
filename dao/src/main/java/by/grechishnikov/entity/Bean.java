package by.grechishnikov.entity;


import java.io.Serializable;

/**
 * Parent interface for all entities
 */
public interface Bean extends Serializable {
    void setId(int id);
}
