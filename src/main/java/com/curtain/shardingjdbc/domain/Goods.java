package com.curtain.shardingjdbc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Curtain
 * @date 2019/4/22 10:40
 */
@Entity
@Table(name = "goods")
@Data
public class Goods {
    @Id
    private Long goodsId;

    private String goodsName;

    private Long goodsType;
}
