package com.elf.sys.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("SYS_APP")
public class App extends DataEntity {
    @TableId
    private String appId;

    private String title;

    private String style;

    private String appType;

    private String appOrder;

    private String href;

    private String showDefault;

    @Override
    public void preInsert() {
        this.setAppId(StringUtils.getUUID());
        this.setCreationTime(new Date());
    }

    @Override
    public void preUpdate() {
        this.setModificationTime(new Date());
    }

    /**
     * @Description: serialVersionUID
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月15日
     */
    private static final long serialVersionUID = 1L;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppOrder() {
		return appOrder;
	}

	public void setAppOrder(String appOrder) {
		this.appOrder = appOrder;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getShowDefault() {
		return showDefault;
	}

	public void setShowDefault(String showDefault) {
		this.showDefault = showDefault;
	}
}