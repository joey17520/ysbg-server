package com.example.server.controller;


import com.example.server.pojo.Position;
import com.example.server.pojo.ResultBean;
import com.example.server.service.IPositionService;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public ResultBean addPostion(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return ResultBean.success("添加成功");
        }
        return ResultBean.fail("添加失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public ResultBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return ResultBean.success("更新成功");
        }
        return ResultBean.fail("更新失败");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public ResultBean deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.fail("删除失败");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public ResultBean deletePositions(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.fail("删除失败");
    }
}
