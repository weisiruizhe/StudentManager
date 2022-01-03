package com.wjj.studentmanager.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Student;

public interface StudentService extends IService<Student> {
    ServerResponse addStudent(Student student);

    ServerResponse listStudent(Integer pageNum,Integer pageSize);

    ServerResponse deleteStudent(Integer id);

    ServerResponse updateStudent(Student student);

    ServerResponse searchStudent(String select,String content);
}
