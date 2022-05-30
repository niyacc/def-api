package cn.niya.api.controller;


import cn.niya.api.entity.UserPO;
import cn.niya.api.service.UserService;
import cn.niya.api.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * @description user register api
     * @param userPO
     * @return
     */
    @PostMapping(value = "/register")
    public JsonResult register(@ModelAttribute("user") UserPO userPO){
        try {
            Object register = userService.register(userPO);
            return JsonResult.ok(register);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }



}
