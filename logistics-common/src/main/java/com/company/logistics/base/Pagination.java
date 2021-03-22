package com.company.logistics.base;

import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author wzj
 * @date 2020/12/27 0:02
 */
@Data
public class Pagination<T> {

    /**
     * 当前页码
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 数据
     */
    private List<T> rows;

}
