package common.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {

	private int code = 200;
	private String msg = "";
	private Object data;

	public Result() {
	}

	public Result(Object data) {
		super();
		this.data = data;
	}

	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Result(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

}
