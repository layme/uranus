package com.ziroom.zry.uranus.common.vo;

import com.ziroom.zry.uranus.common.entity.CurrentUserEntity;
import com.ziroom.zry.uranus.common.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
 * @Date Created in 2018年07月02日 17:50
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpsUserVo {
    private CurrentUserEntity currentUserEntity;
    private EmployeeEntity employeeEntity;
    private Set<String> resourceVoSet;
    private List<ResourceVo> resourceVoList;
    private List<String> roleFids;
}
