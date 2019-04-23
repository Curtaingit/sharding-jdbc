package com.curtain.readwriteseparation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Curtain
 * @date 2019/4/23 9:35
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    private Long id;

    private String city;

    private String name;
}
