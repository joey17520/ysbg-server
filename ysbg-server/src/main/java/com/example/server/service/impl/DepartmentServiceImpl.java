package com.example.server.service.impl;

import com.example.server.pojo.Department;
import com.example.server.mapper.DepartmentMapper;
import com.example.server.pojo.ResultBean;
import com.example.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     * @param dep
     * @return
     */
    @Override
    public ResultBean addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (dep.getResult() == 1) {
            return ResultBean.success("添加成功", dep);
        }
        return ResultBean.fail("添加失败");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public ResultBean deleteDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (dep.getResult() == -2) {
            return ResultBean.fail("操作失败，该部门下还存在子部门");
        }
        if (dep.getResult() == -1) {
            return ResultBean.fail("操作失败，该部门下还存在员工");
        }
        if (dep.getResult() == 1) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.fail("删除失败");
    }
}
