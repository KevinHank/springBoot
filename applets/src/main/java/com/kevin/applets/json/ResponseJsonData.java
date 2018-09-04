package com.kevin.applets.json;

/**
 * action 
 * 
 * @author jliu
 * 
 */
public class ResponseJsonData {
	//返回状态 true成功 false 失败
    private boolean flag = false;
    //返回信息
    private String msg = null;
    //返回具体内容
    private Object obj = null;
    
	public ResponseJsonData() {
		super();
	}

	public ResponseJsonData(boolean flag, String msg, Object obj) {
		super();
		this.flag = flag;
		this.msg = msg;
		this.obj = obj;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
