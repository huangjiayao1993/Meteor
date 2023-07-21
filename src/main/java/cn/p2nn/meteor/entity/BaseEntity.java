package cn.p2nn.meteor.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6107737929316506728L;

	/**
	 * 主键
	 */
	@TableId
	protected String id;

	/**
	 * 搜索值
	 */
	@TableField(exist = false)
	protected String searchValue;

	/**
	 * 创建者
	 */
	@TableField(fill = FieldFill.INSERT)
	protected String createBy;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	protected LocalDateTime createTime;

	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.UPDATE)
	protected String updateBy;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	protected LocalDateTime updateTime;

	/**
	 * 备注
	 */
	protected String remark;

	/**
	 * 开始时间
	 */
	@TableField(exist = false)
	protected String beginTime;

	/**
	 * 结束时间
	 */
	@TableField(exist = false)
	protected String endTime;

	/**
	 * 请求参数
	 */
	@Setter
	@TableField(exist = false)
	protected Map<String, Object> params = new HashMap<>();
}
