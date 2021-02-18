package org.jack.rock.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * User
 *
 * @author zhengzhe17
 * @date 2020/7/14
 */
@Data
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column(unique = true)
    private String name;

    private String passWord;
}
