package com.example.server.controller;


import com.example.server.pojo.ResultBean;
import com.example.server.pojo.Salary;
import com.example.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JoeyWong
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资账套")
    @PostMapping("/")
    public ResultBean addSalary(@RequestBody Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)) {
            return ResultBean.success("添加成功");
        }
        return ResultBean.fail("添加失败");
    }

    @ApiOperation(value = "删除工资账套")
    @DeleteMapping("/{id}")
    public ResultBean deleteSalary(@PathVariable("id")Integer id) {
        if (salaryService.removeById(id)) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.fail("删除失败");
    }

    @ApiOperation(value = "更新工资账套")
    @PutMapping("/")
    public ResultBean updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateById(salary)) {
            return ResultBean.success("更新成功");
        }
        return ResultBean.fail("更新失败");
    }

}
