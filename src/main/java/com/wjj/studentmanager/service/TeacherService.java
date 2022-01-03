package com.wjj.studentmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Teacher;

public interface TeacherService extends IService<Teacher> {

    ServerResponse addTeacher(Teacher teacher);

    ServerResponse listTeacher(Integer pageNum, Integer pageSize);

    ServerResponse updateTeacher(Teacher teacher);

    ServerResponse deleteTeacher(Integer id);

    ServerResponse searchTeacher(String select, String content);
}
