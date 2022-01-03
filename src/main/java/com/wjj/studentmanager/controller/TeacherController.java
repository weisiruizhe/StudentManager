package com.wjj.studentmanager.controller;

import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Teacher;
import com.wjj.studentmanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    /**
     * 添加老师信息
     * @param teacher
     * @return
     */
    @PostMapping("/add")
    public ServerResponse addTeacher(@RequestBody Teacher teacher){
        return teacherService.addTeacher(teacher);
    }

    /**
     * 查看老师信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ServerResponse listTeacher(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return teacherService.listTeacher(pageNum,pageSize);
    }

    /**
     * 修改老师信息
     * @param teacher
     * @return
     */
    @PutMapping("/update")
    public ServerResponse updateTeacher(@RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }

    /**
     * 删除老师信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ServerResponse deleteTeacher(@PathVariable Integer id){
        return teacherService.deleteTeacher(id);
    }

    @GetMapping("/search")
    public ServerResponse searchTeacher(String select,String  content){
        return teacherService.searchTeacher(select,content);
    }
}
