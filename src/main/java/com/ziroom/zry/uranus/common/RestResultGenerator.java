package com.ziroom.zry.uranus.common;

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
public class RestResultGenerator {
    /**
     * 一般格式
     * @param result
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genResult(boolean result, T data, String message) {
        RestResult<T> restResult = RestResult.newInstance();
        restResult.setResult(result);
        restResult.setData(data);
        restResult.setMessage(message);
        return restResult;
    }

    /**
     * 请求成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genSuccessResult(T data) {
        return genResult(true, data, null);
    }

    /**
     * 请求失败
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(String message) {
        return genResult(false, null, message);
    }
}