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
 * @Date Created in 2018年07月03日 19:34
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEntity {
    private Integer id;
    private String fid;
    private String systemsFid;
    private String resName;
    private String className;
    private String resUrl;
    private Integer isLeaf;
    private Integer orderCode;
    private String parentFid;
    private Integer resLevel;
    private Integer resType;
    private Integer isDel;
    private Date createDate;
    private Date lastModifyDate;
    private String createFid;
    private Integer resState;
    private Integer menuAuth;
}
