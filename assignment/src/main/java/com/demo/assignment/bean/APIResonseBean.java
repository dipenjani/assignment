package com.demo.assignment.bean;

import java.util.List;

public class APIResonseBean {

	private Integer response_code;
	private List<APIResonseResultBean> results;

	public Integer getResponse_code() {
		return response_code;
	}

	public void setResponse_code(Integer response_code) {
		this.response_code = response_code;
	}

	public List<APIResonseResultBean> getResults() {
		return results;
	}

	public void setResults(List<APIResonseResultBean> results) {
		this.results = results;
	}

}
