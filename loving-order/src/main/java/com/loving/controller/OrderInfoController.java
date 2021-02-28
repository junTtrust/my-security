package com.loving.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yijunjun
 * @Date: 2021/2/27 17:13
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderInfoController {
    @RequestMapping("/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo, @AuthenticationPrincipal String userName) {
        log.info("userName:{}",userName);
        return userName;
    }


    @RequestMapping("/saveOrder")
    public Object saveOrder(@RequestBody String str) {
        log.info("保存订单:{}",str);
        return str;
    }

}
