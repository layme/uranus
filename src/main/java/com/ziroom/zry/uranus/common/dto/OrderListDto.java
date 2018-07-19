package com.ziroom.zry.uranus.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @Date Created in 2018年07月09日 17:42
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDto {
    @Builder.Default
    private Integer pageSize = 10;
    @Builder.Default
    private Integer pageIndex = 1;
    private String orderNumber;
    private String orderStatus;
    private Integer firstChannel;
    private String createStartTime;
    private String createEndTime;
    private String checkInStartTime;
    private String checkInEndTime;
    private String checkOutStartTime;
    private String checkOutEndTime;
    private Integer isDistribution;
    private String phone;
    private String name;
    private String credentialNumber;
    private String checkInUserName;
    private String otaOrderNumber;
}
