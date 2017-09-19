package work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.Bmember;
import work.model.service.BmemberService;

/**
 * Servlet implementation class ModelServlet
 * 
 * controller의 정의
 * - 사용자가 입력한 정보, 전달 방법 처리
 * - 
 * 
 */
public class ModelServlet extends HttpServlet {

	//service 객체
	private BmemberService bs = new BmemberService();
	private Bmember dto = new Bmember();
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("HO");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
	}
	
	/**
	 * @see HttpServlet#process(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getMethod());
		String action = request.getParameter("action");
		System.out.println("action : "+action);
		switch(action) {
		case "login": login(request, response);
			break;
		case "logout": logout(request, response);
			break;
		case "register": register(request, response);
			break;
		case "infor": information(request, response);
		default :
			System.out.println("Error!!!!");
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	/**
	 * @see HttpServlet#login(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("memberId");
		String pw = request.getParameter("memberPw");
		
		// 아이디, 비밀번호 필수 입력항목 검증
		if ((id == null) || (id.trim().length() == 0)) {
			System.out.println("id 없음");
			request.setAttribute("errorMessage", "id X");   //메시지 설정 (key, value)
			//request.getRequestDispatcher("/Error.jsp").forward(request, response);
			//기존 요청, 응답객체 정보를 가지고 다른자원으로 포워드 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if ((pw == null) || (pw.trim().length() == 0)) {    //비밀번호 길이 및 확인
			System.out.println("pw 없음");
			//새로운 요청으로 페이지 이동하는 것이므로 앞선 요청에서 설정된 것들은 모두 사라짐
			response.sendRedirect("Login.jsp");
			return;
		}
		
		String service = bs.bmemberlogin(id, pw);
		if(service!=null) {
			System.out.println("성공");
			/*request.setAttribute("errorMessage", "성공");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);*/
			
			//세션객체 생성
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", id);
			response.sendRedirect("Service.jsp");
		} else {
			System.out.println("실패");
			request.setAttribute("errorMessage", "실패");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private boolean sessionCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if ((session != null)&&(session.getAttribute("userId") != null)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @see HttpServlet#login(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute("userId");
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원가입");
		String id = request.getParameter("rId");
		String pw = request.getParameter("rPw");
		String name = request.getParameter("rName");
		String nick = request.getParameter("rNick");
		String gender = request.getParameter("rGender");
		int question = Integer.parseInt(request.getParameter("rQuestion"));
		String answer = request.getParameter("rAnswer");
		String birth = request.getParameter("rYear")+"/"+request.getParameter("rMonth")+"/"+request.getParameter("rDay");
		String email = request.getParameter("rEmail1")+request.getParameter("rEmail2");
		String address = request.getParameter("rPostal")+":"+request.getParameter("rAddress1")+request.getParameter("rAddress2");
		String mobile = request.getParameter("rMobile1")+request.getParameter("rMobile2")+request.getParameter("rMobile3");
		
		if ((id == null) || (pw == null) || (name == null) || (nick == null)
				|| (birth == null) || (gender == null) || (answer == null)) {
			return;
		}
		if ((id.length() > 6) || (pw.length() > 10) || (name.length() > 7)
				|| (nick.length() > 10) || (gender.length() > 1) || (answer.length() > 25)) {
			return;
		}
		if (address.length() == 1) {
			address = "";
		}
		
		dto = new Bmember(id, pw, name, nick, birth, mobile, email, gender, address, question, answer);
		if (bs.bmemberAdd(dto) == 1) {
			request.setAttribute("Message", "회원가입 성공");
		} else {
			request.setAttribute("Message", "회원가입 실패");
		}
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}
	
	protected void information(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if ((session != null)&&(session.getAttribute("userId") != null)) {
			Bmember list = bs.bmemberinfor(session.getAttribute("userId").toString());
			request.setAttribute("Information", list);
		} else {
			request.setAttribute("Information", null);
		}
		request.getRequestDispatcher("/Service.jsp").forward(request, response);
	}

}
