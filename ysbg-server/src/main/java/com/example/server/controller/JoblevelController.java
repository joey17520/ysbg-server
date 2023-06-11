package com.example.server.controller;


import com.example.server.pojo.Joblevel;
import com.example.server.pojo.ResultBean;
import com.example.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevel() {
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public ResultBean addJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return ResultBean.success("添加成功");
        }
        return ResultBean.fail("添加失败");
    }

    @ApiOperation(value = "更新职位")
    @PutMapping("/")
    public ResultBean updateJobLevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return ResultBean.success("更新成功");
        }
        return ResultBean.fail("更新失败");
    }

    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    public ResultBean deleteJobLevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.fail("删除失败");
    }

    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public ResultBean deleteJobLevelByIds(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.fail("删除失败");
    }
}
