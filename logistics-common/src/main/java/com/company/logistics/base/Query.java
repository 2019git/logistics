package com.company.logistics.base;

import lombok.Data;

/**
 * @author wzj
 * @date 2021/3/23 23:04
 */
@Data
public class Query {

    //当前页码
    private int page;
    //每页条数
    private int limit;

}
