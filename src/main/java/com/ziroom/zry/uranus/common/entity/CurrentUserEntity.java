package com.ziroom.zry.uranus.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
 * @Date Created in 2018年07月02日 17:51
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserEntity {
    private Integer id;
    private String fid;
    private String userAccount;
    private String userPassword;
    private String employeeFid;
    private Integer isAdmin;
    private Integer accountStatus;
    private String nationCode;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private Integer isDel;
    private Date lastModifyDate;
    private Date createDate;
    private String createFid;
}
