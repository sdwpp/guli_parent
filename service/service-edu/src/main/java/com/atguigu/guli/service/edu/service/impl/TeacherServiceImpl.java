package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.mapper.TeacherMapper;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-12
 */
@Service
public class TeacherServiceImpl extends ServiceImpl< TeacherMapper, Teacher > implements TeacherService {


    @Override
    public IPage< Teacher > selectPage(Long page, Long limit, TeacherQueryVo teacherQueryVo) {
//     分页
        Page< Teacher > pageParam = new Page<>(page, limit);
//        排序
        QueryWrapper< Teacher > queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("sort");

        if (teacherQueryVo == null) {
            return baseMapper.selectPage(pageParam, queryWrapper);
        }
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.likeRight("name",name);

        }
        if(level != null){
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("join_date",joinDateBegin);
        }
        if(!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("join_date",joinDateEnd);
        }



        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
