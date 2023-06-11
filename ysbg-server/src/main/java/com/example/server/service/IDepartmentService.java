package com.example.server.service;

import com.example.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.ResultBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param dep
     * @return
     */
    ResultBean addDep(Department dep);

    /**
     * 删除部门
     * @param id
     * @return
     */
    ResultBean deleteDep(Integer id);
}
