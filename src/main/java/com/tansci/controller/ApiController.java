package com.tansci.controller;

import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.service.payment.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName： ApiController.java
 * @ClassPath： com.tansci.controller.ApiController.java
 * @Description： API开发接口
 * @Author： tanyp
 * @Date： 2022/4/21 11:21
 **/
@Slf4j
@RestController
@RequestMapping("api")
@Api(value = "api", tags = "API开发接口")
public class ApiController {

    @Autowired
    private PayService payService;

    @PostMapping("wx/notify")
    @Log(modul = "API开发接口-微信支付回调", type = Constants.SELECT, desc = "微信支付回调")
    @ApiOperation(value = "微信支付回调")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        payService.wxNotify(request, response);
        PrintWriter out = response.getWriter();
        out.print("success");
        out.close();
    }

    @PostMapping("wx/refundNotify")
    @Log(modul = "API开发接口-微信退款回调", type = Constants.SELECT, desc = "微信退款回调")
    @ApiOperation(value = "微信退款回调")
    public void wxRefundNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        payService.wxRefundNotify(request, response);
        PrintWriter out = response.getWriter();
        out.print("success");
        out.close();
    }

    @PostMapping("ali/notify")
    @Log(modul = "API开发接口-支付宝回调", type = Constants.SELECT, desc = "支付宝回调")
    @ApiOperation(value = "支付宝回调")
    public void aliNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        payService.aliNotify(request, response);
        PrintWriter out = response.getWriter();
        out.print("success");
        out.close();
    }

}
