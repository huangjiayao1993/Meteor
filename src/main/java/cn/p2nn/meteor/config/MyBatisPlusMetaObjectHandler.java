package cn.p2nn.meteor.config;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import cn.dev33.satoken.stp.StpUtil;
import cn.p2nn.meteor.constants.FieldConstant;

/**
 * 自定义填充公共 name 字段
 *
 * @author huangjiayao1993
 */
@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 插入填充，字段为空自动填充
	 *
	 * @param metaObject
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		this.setTime(FieldConstant.Fields.createTime, metaObject);
		this.setBy(FieldConstant.Fields.createBy, metaObject);
	}

	/**
	 * 更新填充
	 *
	 * @param metaObject
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		this.setTime(FieldConstant.Fields.updateTime, metaObject);
		this.setBy(FieldConstant.Fields.updateBy, metaObject);
	}

	/**
	 * 设置时间
	 *
	 * @param timeField
	 * @param metaObject
	 */
	private void setTime(String timeField, MetaObject metaObject) {
		setFieldValByName(timeField, LocalDateTime.now(), metaObject);
	}

	/**
	 * 设置用户
	 *
	 * @param byField
	 * @param metaObject
	 */
	private void setBy(String byField, MetaObject metaObject) {
		if (StpUtil.isLogin()) {
			setFieldValByName(byField, StpUtil.getLoginId(), metaObject);
		}
	}

}