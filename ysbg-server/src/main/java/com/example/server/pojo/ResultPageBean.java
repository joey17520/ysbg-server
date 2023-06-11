package com.example.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页公共返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPageBean {

    /**
     * 总条目
     */
    private Long total;
    /**
     * 数据List
     */
    private List<?> data;
}
