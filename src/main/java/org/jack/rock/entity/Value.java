package org.jack.rock.entity;

import lombok.Data;

/**
 * Value
 */
@Data
public class Value {
    private Long id;
    private String quote;
    @Override
    public String toString() {
        return "id=" + id +", quote = " + quote;
    }
}
