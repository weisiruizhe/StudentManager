package com.wjj.studentmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("t_teacher")
public class Teacher {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Integer age;
    Integer sex;
    String num;
    String course;
}
