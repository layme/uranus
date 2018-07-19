package com.ziroom.zry.uranus.common;

import lombok.Data;

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
 * @Date Created in 2018年07月03日 19:22
 * @since 1.0
 */
@Data
public class RestResult<T> {
    private boolean result;
    private String message;
    private T data;

    private RestResult() {}

    public static <T> RestResult<T> newInstance() {
        return new RestResult<>();
    }
}