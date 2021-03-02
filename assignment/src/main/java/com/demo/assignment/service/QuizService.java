package com.demo.assignment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.assignment.bean.APIResonseBean;
import com.demo.assignment.bean.APIResonseResultBean;
import com.demo.assignment.bean.QuizBean;
import com.demo.assignment.bean.ResultBean;

@Service
public class QuizService {

	private static final String URL_1 = "https://opentdb.com/api.php?amount=5&category=11";
	private static final String URL_2 = "https://opentdb.com/api.php?amount=5&category=12";

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Call api and read the response.
	 * 
	 * @return
	 */
	public List<QuizBean> quizList() {
		List<QuizBean> list = new ArrayList<QuizBean>();
		Map<String, List<ResultBean>> map = new HashMap<String, List<ResultBean>>();

		APIResonseBean response1 = restTemplate.getForObject(URL_1, APIResonseBean.class);
		if (response1 != null) {
			this.getResults(map, response1);
		}
		APIResonseBean response2 = restTemplate.getForObject(URL_2, APIResonseBean.class);
		if (response2 != null) {
			this.getResults(map, response2);
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			QuizBean bean = new QuizBean(key, map.get(key));
			list.add(bean);
		}
		return list;
	}

	/**
	 * @param map
	 * @param response
	 * @return Read json data from the api response and set to ResultBean.
	 */
	public Map<String, List<ResultBean>> getResults(Map<String, List<ResultBean>> map, APIResonseBean response) {
		for (APIResonseResultBean resp : response.getResults()) {
			List<ResultBean> resultBeans = map.get(resp.getCategory());

			List<String> all_answers = new ArrayList<String>();
			all_answers.addAll(resp.getIncorrect_answers());
			all_answers.add(resp.getCorrect_answer());
			if (resultBeans != null) {
				resultBeans.add(new ResultBean(resp.getType(), resp.getDifficulty(),
						resp.getQuestion().replaceAll("&.*?;", ""), all_answers, resp.getCorrect_answer()));
			} else {
				resultBeans = new ArrayList<ResultBean>();
				resultBeans.add(new ResultBean(resp.getType(), resp.getDifficulty(),
						resp.getQuestion().replaceAll("&.*?;", ""), all_answers, resp.getCorrect_answer()));
				map.put(resp.getCategory(), resultBeans);
			}
		}
		return map;
	}

}
