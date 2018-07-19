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
 * @Date Created in 2018年07月02日 19:01
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriUserEntity {
    /**
     * 表自增主键
     * 表字段 : pri_user.ID
     * 
     */
    private Long id;

    /**
     * 
     * 表字段 : pri_user.VENETO
     * 
     */
    private String veneto;

    /**
     * 
     * 表字段 : pri_user.AREA
     * 
     */
    private String area;

    /**
     * 
     * 表字段 : pri_user.STORE
     * 
     */
    private String store;

    /**
     * 
     * 表字段 : pri_user.NAME
     * 
     */
    private String name;

    /**
     * 
     * 表字段 : pri_user.USERNAME
     * 
     */
    private String username;

    /**
     * 
     * 表字段 : pri_user.PASSWORD
     * 
     */
    private String password;

    /**
     * 
     * 表字段 : pri_user.IS_ENABLE
     * 
     */
    private Integer isEnable;

    /**
     * 
     * 表字段 : pri_user.TYPE
     * 
     */
    private Integer type;

    /**
     * 
     * 表字段 : pri_user.USER_TYPE
     * 
     */
    private Integer userType;

    /**
     * 
     * 表字段 : pri_user.ORG_ID
     * 
     */
    private String orgId;

    /**
     * 
     * 表字段 : pri_user.ORG_NAME
     * 
     */
    private String orgName;

    /**
     * 
     * 表字段 : pri_user.GROUP_NAME
     * 
     */
    private String groupName;

    /**
     * 
     * 表字段 : pri_user.ROLE_NAMES
     * 
     */
    private String roleNames;

    /**
     * 
     * 表字段 : pri_user.GROUP_ID
     * 
     */
    private Integer groupId;

    /**
     * 
     * 表字段 : pri_user.IS_DEL
     * 
     */
    private Integer isDel;

    /**
     * 
     * 表字段 : pri_user.EMPLID
     * 
     */
    private String emplid;

    /**
     * 
     * 表字段 : pri_user.EMPL_RCD
     * 
     */
    private Integer emplRcd;

    /**
     * 
     * 表字段 : pri_user.EFFDT
     * 
     */
    private Date effdt;

    /**
     * 
     * 表字段 : pri_user.EFFSEQ
     * 
     */
    private Integer effseq;

    /**
     * 
     * 表字段 : pri_user.HL_STATUS
     * 
     */
    private String hlStatus;

    /**
     * 
     * 表字段 : pri_user.NAME1
     * 
     */
    private String name1;

    /**
     * 
     * 表字段 : pri_user.LAST_NAME
     * 
     */
    private String lastName;

    /**
     * 
     * 表字段 : pri_user.FIRST_NAME
     * 
     */
    private String firstName;

    /**
     * 
     * 表字段 : pri_user.NAME_AC
     * 
     */
    private String nameAc;

    /**
     * 
     * 表字段 : pri_user.COUNTRY
     * 
     */
    private String country;

    /**
     * 
     * 表字段 : pri_user.HL_ADDRESS
     * 
     */
    private String hlAddress;

    /**
     * 
     * 表字段 : pri_user.CITY
     * 
     */
    private String city;

    /**
     * 
     * 表字段 : pri_user.COUNTY
     * 
     */
    private String county;

    /**
     * 
     * 表字段 : pri_user.STATE
     * 
     */
    private String state;

    /**
     * 
     * 表字段 : pri_user.POSTAL
     * 
     */
    private String postal;

    /**
     * 
     * 表字段 : pri_user.SEX
     * 
     */
    private String sex;

    /**
     * 
     * 表字段 : pri_user.MAR_STATUS
     * 
     */
    private String marStatus;

    /**
     * 
     * 表字段 : pri_user.MAR_STATUS_DT
     * 
     */
    private Date marStatusDt;

    /**
     * 
     * 表字段 : pri_user.BIRTHDATE
     * 
     */
    private Date birthdate;

    /**
     * 
     * 表字段 : pri_user.BIRTHCOUNTRY
     * 
     */
    private String birthcountry;

    /**
     * 
     * 表字段 : pri_user.BIRTHSTATE
     * 
     */
    private String birthstate;

    /**
     * 
     * 表字段 : pri_user.COUNTRY_CODE
     * 
     */
    private String countryCode;

    /**
     * 
     * 表字段 : pri_user.PHONE_MOBILE
     * 
     */
    private String phoneMobile;

    /**
     * 
     * 表字段 : pri_user.PHONE_COMPANY
     * 
     */
    private String phoneCompany;

    /**
     * 
     * 表字段 : pri_user.PHONE_HOME
     * 
     */
    private String phoneHome;

    /**
     * 
     * 表字段 : pri_user.HL_USER
     * 
     */
    private String hlUser;

    /**
     * 
     * 表字段 : pri_user.EMAIL_ADDR
     * 
     */
    private String emailAddr;

    /**
     * 
     * 表字段 : pri_user.PHONE_FAX
     * 
     */
    private String phoneFax;

    /**
     * 
     * 表字段 : pri_user.ACTION
     * 
     */
    private String action;

    /**
     * 
     * 表字段 : pri_user.ACTION_REASON
     * 
     */
    private String actionReason;

    /**
     * 
     * 表字段 : pri_user.SETID_DEPT
     * 
     */
    private String setidDept;

    /**
     * 
     * 表字段 : pri_user.SETID_JOBCODE
     * 
     */
    private String setidJobcode;

    /**
     * 
     * 表字段 : pri_user.SETID_LOCATION
     * 
     */
    private String setidLocation;

    /**
     * 
     * 表字段 : pri_user.DEPTID
     * 
     */
    private String deptid;

    /**
     * 
     * 表字段 : pri_user.HL_TREEPATH
     * 
     */
    private String hlTreepath;

    /**
     * 
     * 表字段 : pri_user.JOBCODE
     * 
     */
    private String jobcode;

    /**
     * 
     * 表字段 : pri_user.SUPERVISOR_ID
     * 
     */
    private String supervisorId;

    /**
     * 
     * 表字段 : pri_user.EMPL_STATUS
     * 
     */
    private String emplStatus;

    /**
     * 
     * 表字段 : pri_user.LOCATION
     * 
     */
    private String location;

    /**
     * 
     * 表字段 : pri_user.COMPANY
     * 
     */
    private String company;

    /**
     * 
     * 表字段 : pri_user.HL_APP_CHECK
     * 
     */
    private String hlAppCheck;

    /**
     * 
     * 表字段 : pri_user.HL_TEM_CLOSE
     * 
     */
    private String hlTemClose;

    /**
     * 
     * 表字段 : pri_user.EFFDT_FROM
     * 
     */
    private Date effdtFrom;

    /**
     * 
     * 表字段 : pri_user.HL_OUT_DT
     * 
     */
    private Date hlOutDt;

    /**
     * 
     * 表字段 : pri_user.HL_GLS_FLAG
     * 
     */
    private String hlGlsFlag;

    /**
     * 
     * 表字段 : pri_user.CREATE_DATE
     * 
     */
    private Date createDate;

    /**
     * 
     * 表字段 : pri_user.DEPTID_NEW
     * 
     */
    private String deptidNew;

    /**
     * 
     * 表字段 : pri_user.HL_TREEPATH_NEW
     * 
     */
    private String hlTreepathNew;

    /**
     * 
     * 表字段 : pri_user.JOBCODE_NEW
     * 
     */
    private String jobcodeNew;

    /**
     * 
     * 表字段 : pri_user.LAST_MODIFY_TIME_SYNCHRONOUS
     * 
     */
    private Date lastModifyTimeSynchronous;

    /**
     * 
     * 表字段 : pri_user.TERRITORY_CODE
     * 
     */
    private Integer territoryCode;

    /**
     * 
     * 表字段 : pri_user.ZC_USER_ID
     * 
     */
    private Long zcUserId;

    /**
     *
     * 表字段 : pri_user.USER_ROLE
     *
     */
    private String userRole;
}