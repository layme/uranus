package com.ziroom.zry.uranus.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ziroom.zry.uranus.common.RestResult;
import com.ziroom.zry.uranus.common.RestResultGenerator;
import com.ziroom.zry.uranus.common.vo.MenuDataVo;
import com.ziroom.zry.uranus.common.vo.ResourceVo;
import com.ziroom.zry.uranus.common.vo.UpsUserVo;
import com.ziroom.zry.uranus.common.dto.UserAccountDto;
import com.ziroom.zry.uranus.common.entity.PriUserEntity;
import com.ziroom.zry.uranus.common.vo.UserInfoVo;
import com.ziroom.zry.uranus.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
 * @Date Created in 2018年07月02日 19:01
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/system")
public class SystemController {
    @Autowired
    private HttpUtil httpUtil;

    @Value("#{'${system_code}'.trim()}")
    private String systemCode;

    @Value("#{'${res_user_msg_api}'.trim()}")
    private String resUserMsgApi;

    @Autowired
    private Environment environment;

    /**
     * 登录系统
     * @author renhy
     * @created 2018年07月03日 19:39:49
     * @param
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam String ticket) {
        UserAccountDto userAccountDto = UserAccountDto.builder().name("任宏远").hlUser("renhy").username("60006896").build();
        UpsUserVo userUps = this.getUserUpsByUserName(userAccountDto.getHlUser());
        List<ResourceVo> resourceVoList = userUps.getResourceVoList();
    }

    /**
     * 获取用户权限列表
     * @author renhy
     * @created 2018年07月03日 19:38:42
     * @param
     * @return
     */
    @RequestMapping(value = "/getUpsList", method = RequestMethod.GET)
    public RestResult<UserInfoVo> getUpsList() {
        UserAccountDto userAccountDto = UserAccountDto.builder().name("任宏远").hlUser("renhy").username("60006896").build();
        UpsUserVo userUps = this.getUserUpsByUserName(userAccountDto.getHlUser());
        List<ResourceVo> resourceVoList = userUps.getResourceVoList();
        List<MenuDataVo> menuDataVos = Lists.newArrayList();
        final int[] index = {1};
        resourceVoList.forEach(item -> {
            menuDataVos.add(MenuDataVo.builder()
                    .id(item.getFid())
                    .name(item.getResName())
                    .route("/v" + index[0]++)
                    .build());
            item.getChildRes().forEach(key -> {
                menuDataVos.add(MenuDataVo.builder()
                        .id(key.getFid())
                        .name(key.getResName())
                        .parentId(item.getFid())
                        .route(key.getResUrl().substring(1, key.getResUrl().length()))
                        .build());
            });
        });
        return RestResultGenerator.genSuccessResult(
                UserInfoVo
                        .builder()
                        .employeeEntity(userUps.getEmployeeEntity())
                        .menuDataVos(menuDataVos)
                        .build());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        String CASLogoutUrl= this.environment.getProperty("cas.logout.url");
        String serverUrl =this.environment.getProperty("server.url");
        return new ModelAndView(new RedirectView(CASLogoutUrl+serverUrl));
    }

    /**
     * 调用UPS查询用户权限信息
     * @author renhy
     * @created 2018年07月03日 19:38:49
     * @param
     * @return
     */
    private UpsUserVo getUserUpsByUserName(String hl_user) {
        UpsUserVo upsUserVo = null;
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("userAccount", hl_user);
        postParameters.add("sysCode", systemCode);
        String resultJson = httpUtil.post(resUserMsgApi, postParameters, MediaType.APPLICATION_FORM_URLENCODED, String.class);
        try {
            upsUserVo = JSON.parseObject(resultJson).getJSONObject("data").getJSONObject("upsUser").toJavaObject(UpsUserVo.class);
        } catch (RuntimeException e) {
            log.error("can't find upsUser from remote", e);
        }
        return upsUserVo;
    }
}
