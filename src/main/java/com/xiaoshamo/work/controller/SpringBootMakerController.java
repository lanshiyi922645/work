package com.xiaoshamo.work.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wugenqiang
 * @create 2019-01-18 18:04
 */
@RestController
public class SpringBootMakerController {
 
    @RequestMapping("/")
    public String abc(){
        return "hello docker! I am wugenqiang";
    }
}