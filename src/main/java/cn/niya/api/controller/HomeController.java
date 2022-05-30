package cn.niya.api.controller;

import cn.niya.api.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
@Slf4j
public class HomeController {


    @GetMapping(value = "")
    public JsonResult home(){
        return JsonResult.ok("home Page");
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/role")
    public JsonResult testRole() {
        return JsonResult.ok("test role");
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping(value = "/role2")
    public JsonResult testRole2(){
        return JsonResult.ok();
    }


}
