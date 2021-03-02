package com.demo.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.assignment.bean.QuizBean;
import com.demo.assignment.service.QuizService;

@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;

	@GetMapping
	public String doGet() {
		return "Welcome to the application.";
	}

	@GetMapping("/coding/exercise/quiz")
	public ResponseEntity<Map<String, Object>> quizList() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<QuizBean> list = quizService.quizList();
			response.put("quiz", list);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "No record found");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
