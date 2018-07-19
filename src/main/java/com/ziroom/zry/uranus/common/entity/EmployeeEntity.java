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
 * @Date Created in 2018年07月02日 17:52
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    private Integer id;
    private String fid;
    private String empCode;
    private String empName;
    private Integer empSex;
    private String empMail;
    private String empMobile;
    private Integer empValid;
    private String postCode;
    private String postName;
    private String departCode;
    private String departName;
    private String cityCode;
    private Integer isDel;
    private Date createDate;
    private Date lastModifyDate;
    private String createFid;
    private String ehrCityCode;
    private String centerCode;
    private String center;
    private String groupCode;
    private String groupName;
    private String branceCompanyCode;
}
