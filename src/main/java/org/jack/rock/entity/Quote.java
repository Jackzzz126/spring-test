package org.jack.rock.entity;

import lombok.Data;

/**
 * Quote
 */
@Data
public class Quote {
    private String type;
    private Value value;
    @Override
    public String toString() {
        return "type=" + type +", value = " + value;
    }
}
