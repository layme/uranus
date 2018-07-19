package com.ziroom.zry.uranus.common.vo;

import com.google.common.collect.Lists;
import com.ziroom.zry.uranus.common.entity.ResourceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
 * @Date Created in 2018年07月02日 17:53
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVo extends ResourceEntity {
    private String currentUserFid;
    private List<ResourceVo> childRes = Lists.newArrayList();
}
