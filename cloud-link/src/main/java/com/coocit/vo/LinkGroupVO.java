package com.coocit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author: Coocit
 * @date: 2024/3/20
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LinkGroupVO {

    //@JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 组名
     */
    private String title;

    /**
     * 账号唯一编号
     */
    private Long accountNo;

    private Date gmtCreate;

    private Date gmtModified;

}
