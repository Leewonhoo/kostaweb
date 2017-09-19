package work.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLifeCyle
 */
public class ServletLifeCyle extends HttpServlet {
	
	/*
	 * ## 서블릿 라이프사이클
		1. 서블릿 클래스 로딩
		2. new instance
		3. init() : 초기홪 작업
		4. service(request, response) : 사용자 요청 반복 서비스 제공
		5. destroy() : 
			=> 기존 서비스 변경
			=> 해당 컨텍스트 서비스 중지
			=> 서버 종료될 때
	 */
	
	public int requestCount=0;
	
	static {
		System.out.println("1. (변경)서블릿 클래스 로딩");
	}
	
	public ServletLifeCyle() {
		System.out.println("2. (변경)new instance");
	}
	

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("3. (변경)init() : 초기홪 작업");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("5. (변경)destroy() : ");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("4. (변경)service(request, response) : 사용자 요청 반복 서비스 제공");
		System.out.println(++requestCount);
		System.out.println();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
