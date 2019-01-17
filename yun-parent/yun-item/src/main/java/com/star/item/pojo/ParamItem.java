package com.star.item.pojo;

import java.util.List;

public class ParamItem {
      private String groupName;
      
      private List<ParamNode> params;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<ParamNode> getParams() {
		return params;
	}

	public void setParams(List<ParamNode> params) {
		this.params = params;
	}
      
      
}
