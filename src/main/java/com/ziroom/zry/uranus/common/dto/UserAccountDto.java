package com.ziroom.zry.uranus.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author homelink
 * 登录人信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
	/**
	 * 中文名
 	 */
	private String name;
	/**
	 * 员工号
 	 */
	private String username;
	/**
	 * 	员工邮箱前缀-登录名
 	 */
	private String hlUser;
	/**
	 * 员工电话
 	 */
	private String phoneMobile;
	/**
	 * 项目列表
 	 */
	private List<String> projectIdList ;
}