package com.demo.assignment.bean;

import java.util.List;

public class QuizBean {

	private String category;
	private List<ResultBean> results;

	public QuizBean(String category, List<ResultBean> results) {
		super();
		this.category = category;
		this.results = results;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ResultBean> getResults() {
		return results;
	}

	public void setResults(List<ResultBean> results) {
		this.results = results;
	}

}
