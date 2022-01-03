package com.wjj.studentmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjj.studentmanager.common.ResponseEnum;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Teacher;
import com.wjj.studentmanager.mapper.TeacherMapper;
import com.wjj.studentmanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    private static final String NUM = "1";
    private static final String NAME = "2";

    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 添加老师信息
     * @param teacher
     * @return
     */
    @Override
    public ServerResponse addTeacher(Teacher teacher) {
        try {
            if(null == teacher.getName() ||
                    null == teacher.getAge() ||
                    null == teacher.getSex()  ||
                    null == teacher.getNum() ||
                    null == teacher.getCourse()){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            }
            teacherMapper.insert(teacher);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 查看老师信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse listTeacher(Integer pageNum, Integer pageSize) {
        try {
            Page<Teacher> teacherPage = new Page<>(pageNum, pageSize);
            Page<Teacher> page = teacherMapper.selectPage(teacherPage, new QueryWrapper<Teacher>().orderByDesc("name"));
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 修改老师信息
     * @param teacher
     * @return
     */
    @Override
    public ServerResponse updateTeacher(Teacher teacher) {
        try {
            teacherMapper.updateById(teacher);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_TEACHER_FAILED);
        }
    }

    /**
     * 删除老师信息
     * @param id
     * @return
     */
    @Override
    public ServerResponse deleteTeacher(Integer id) {
        try {
            teacherMapper.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 根据教师编号或姓名来搜索教师信息
     * @param select
     * @param content
     * @return
     */
    @Override
    public ServerResponse searchTeacher(String select, String content) {
        List<Teacher> teachers = new ArrayList<>();
        try {
            switch (select){
                //根据教师编号模糊查询教师信息
                case NUM:
                    QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
                    teacherQueryWrapper.eq("num",content);
                    teachers = teacherMapper.selectList(teacherQueryWrapper);
                    break;
                //根据教师姓名模糊查询教师信息
                case NAME:
                    QueryWrapper<Teacher> teacherQueryWrapper1 = new QueryWrapper<>();
                    teacherQueryWrapper1.eq("name",content);
                    teachers = teacherMapper.selectList(teacherQueryWrapper1);
                    break;
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(teachers);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
