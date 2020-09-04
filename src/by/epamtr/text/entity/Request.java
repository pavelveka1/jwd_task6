package by.epamtr.text.entity;

import java.io.Serializable;

public class Request implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String request;
	private Object param1;
	private Object param2;
	private Object param3;

	public Request() {
		super();
		this.request = "Default";
		this.param1 = null;
		this.param2 = null;
	}

	public Request(String request) {
		super();
		this.request = request;
		this.param1 = null;
		this.param2 = null;
		this.param3=null;
	}
	
	public Request(String request, Object param1) {
		super();
		this.request = request;
		this.param1 = param1;
		this.param2 = null;
		this.param3=null;
	}
	

	public Request(String request, Object param1, Object param2) {
		super();
		this.request = request;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = null;
	}

	public Request(String request, Object param1, Object param2, Object param3) {
		super();
		this.request = request;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
	}

	public String getRequest() {
		return request;
	}

	public Object getParam1() {
		return param1;
	}

	public Object getParam2() {
		return param2;
	}

	public Object getParam3() {
		return param3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((param1 == null) ? 0 : param1.hashCode());
		result = prime * result + ((param2 == null) ? 0 : param2.hashCode());
		result = prime * result + ((param3 == null) ? 0 : param3.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (param1 == null) {
			if (other.param1 != null)
				return false;
		} else if (!param1.equals(other.param1))
			return false;
		if (param2 == null) {
			if (other.param2 != null)
				return false;
		} else if (!param2.equals(other.param2))
			return false;
		if (param3 == null) {
			if (other.param3 != null)
				return false;
		} else if (!param3.equals(other.param3))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [request=" + request + ", param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + "]";
	}

}
