package com.atguigu.guli.service.edu.controller.admin;

import com.atguigu.guli.service.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@Slf4j
public class AdminTeacherController {

    @Autowired
    private TeacherService teacherService;
    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public R listAll(){

        List< Teacher > list = teacherService.list();
        return R.ok().data("item",list).message("获取讲师列表");
    }
    @ApiOperation( value =  "根据ID删除讲师",notes = "根据ID删除讲师")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "讲师ID") @PathVariable String id){

        boolean result = teacherService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }{
            return R.ok().message("删除失败");
        }
    }

    @ApiOperation("分页讲师列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true)@PathVariable Long page,
                      @ApiParam(value = "每页记录数",required = true)@PathVariable Long limit,
                      @ApiParam(value = "讲师列表",required = true)TeacherQueryVo teacherQueryVo
                      ){
        IPage< Teacher > pageModel = teacherService.selectPage(page,limit,teacherQueryVo);
        return R.ok().data("pageModel",pageModel);

    }

    @ApiOperation("新增讲师")
    @PostMapping("/save")
    public R save(@ApiParam(value = "讲师对象") @RequestBody Teacher teacher){
        boolean result = teacherService.save(teacher);
        if(result){
            return R.ok().message("新增成功");
        }{
            return R.error().message("新增失败");
        }
    }
    @ApiOperation("更新讲师")
    @PutMapping("/update")
    public R updateById(@ApiParam(value = "讲师对象")
                        @RequestBody Teacher teacher
    ){
        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("数据不存在");
        }

    }
    @ApiOperation("根据id获取讲师信息")
    @DeleteMapping("get/{id}")
    public R deleteById(@ApiParam(value = "讲师ID",required = true)@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher!=null){
            return R.ok().data("item",teacher);
        }else {
            return R.error().message("数据不存在");
        }
    }

}