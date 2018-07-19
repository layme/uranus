package com.ziroom.zry.uranus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ziroom.zry.uranus.common.RestResult;
import com.ziroom.zry.uranus.common.RestResultGenerator;
import com.ziroom.zry.uranus.common.dto.OrderListDto;
import com.ziroom.zry.uranus.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @Time Created in 2018年07月05日 20:37
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @Value("#{'${server_url}'.trim()}")
    private String serverUrl;
    @Autowired
    private HttpUtil httpUtil;

    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    public RestResult<JSONObject> getUpsList(@RequestBody OrderListDto orderListDto) {
        String url = this.serverUrl + "/orders/list/v1";
        String result = this.httpUtil.post(url, orderListDto, String.class);
        return RestResultGenerator.genSuccessResult(JSON.parseObject(result));
    }
}
