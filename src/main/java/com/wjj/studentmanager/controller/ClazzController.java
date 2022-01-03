package com.wjj.studentmanager.controller;

import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Clazz;
import com.wjj.studentmanager.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    @PostMapping("/add")
    public ServerResponse addClazz(@RequestBody Clazz clazz){
        return clazzService.addClazz(clazz);
    }

    @GetMapping("/list")
    public ServerResponse listClazz(){
        return clazzService.listClazz();
    }

    @PutMapping("/update")
    public ServerResponse updateClazz(@RequestBody Clazz clazz){
        return clazzService.updateClazz(clazz);
    }

    @DeleteMapping("/delete/{id}")
    public ServerResponse deleteClazz(@PathVariable Integer id){
        return clazzService.deleteClazz(id);
    }
}
