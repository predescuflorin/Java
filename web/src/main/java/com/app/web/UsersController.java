package com.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.web.models.LoginForm;
import com.app.web.models.ServerMemory;
import com.app.web.models.User;
import com.app.web.models.UserMapper;


@Controller
public class UsersController {
//	TODO: github
	@Autowired
	JdbcTemplate jdbcTemplate;

	HashMap<String, ServerMemory> memory = new HashMap<String, ServerMemory>();

	@GetMapping("/register-form")
	public ModelAndView register(
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password, 
			@RequestParam(name = "re-password", required = true) String password2) 
	{

		ModelAndView registerMV = new ModelAndView("register.html");

		if (!password.equals(password2)) {
			registerMV.addObject("errPass", "Parolele nu coincid");
			return registerMV;
		} else {
			jdbcTemplate.update("INSERT INTO users values (null, ?, ?, ?)",
					username, password, email);
		}

		return new ModelAndView("redirect:/index.html");

	}

	@GetMapping("/register")
	public ModelAndView showRegister() {
		ModelAndView registerMV = new ModelAndView("register.html");
		return registerMV;
	}

	@GetMapping("/login")
	public ModelAndView showLogin() {
		ModelAndView registerMV = new ModelAndView("login.html");
		return registerMV;
	}

	@GetMapping("/login-form")
//	http://localhost:8080/login-form?email=lalala@yahoo.com&password=xyz
//	HashMap<String, String> -> requestParam
//	{
//		email: lalala@yahoo.com
//		password: xyz
//	}
	public ModelAndView login(
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {
		ModelAndView loginMV = new ModelAndView("login.html");
		
		String queryStm = "SELECT * FROM users WHERE email='" + email + "';";

		ArrayList<User> users = (ArrayList<User>) jdbcTemplate.query(queryStm, new UserMapper());

		if (users.size() == 0) {
			loginMV.addObject("errLogin", "Nu exista un cont de utilizator cu acest email.");
			return loginMV;
		} else if (users.size() > 1) {
			loginMV.addObject("errLogin", "Exista mai multi utilizatori cu acelasi email");
			return loginMV;
		} else {
			User dbUser = users.get(0);
			if (dbUser.getPassword().equals(password)) {
				return new ModelAndView("redirect:/dashboard");
			} else {
				loginMV.addObject("errLogin", "Parola incorecta");
				return loginMV;
			}
		}
	}

	@PostMapping(value="/login-form", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView loginPost(
			HttpServletRequest req, HttpServletResponse resp,
			LoginForm loginForm) {
		ModelAndView loginMV = new ModelAndView("login.html");

		ArrayList<User> users = (ArrayList<User>) jdbcTemplate.query
				("SELECT * FROM users WHERE email='" + loginForm.getEmail() + "';", new UserMapper());

		if (users.size() == 0) {
			loginMV.addObject("errLogin", "Nu exista un cont de utilizator cu acest email.");
			return loginMV;
		} else if (users.size() > 1) {
			loginMV.addObject("errLogin", "Exista mai multi utilizatori cu acelasi email");
			return loginMV;
		} else {
//			http://dontpad.com/java-req
			User dbUser = users.get(0);
			if (dbUser.getPassword().equals(loginForm.getPassword())) {
				resp.addCookie(new Cookie("username", dbUser.getUsername()));

				Cookie[] cookies = req.getCookies();
				Cookie sessionCookie = createUserCookies(dbUser, cookies);

				resp.addCookie(sessionCookie);
				return new ModelAndView("redirect:/dashboard");
			} else {
				loginMV.addObject("errLogin", "Parola incorecta");
				return loginMV;
			}
		}
	}

	@GetMapping("/dashboard")
	public ModelAndView showDashboard(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView dashboardMV = new ModelAndView("dashboard.html");

		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			dashboardMV.addObject("err", "Utilizatorul nu este autentificat");
			return dashboardMV;
		}

		boolean isAuthenticated = false;

		for (Cookie cookie: cookies) {
			if (cookie.getName().equals("username")) {
				dashboardMV.addObject("username", cookie.getValue());
				isAuthenticated = true;
			}
		}

		if (!isAuthenticated) {
			dashboardMV.addObject("err", "Utilizatorul nu este autentificat");
		}

		dashboardMV.addObject("isAuthenticated", isAuthenticated);

		return dashboardMV;
	}

	@GetMapping("/play")
	public ModelAndView showGame(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView playMV = new ModelAndView("play.html");

		Cookie[] cookies = req.getCookies();
		Cookie sessionCookie = findCookie("sessionId", cookies);

		if (sessionCookie == null) {
			playMV.addObject("err", "Utilizatorul nu este autentificat");
			playMV.addObject("isAuthenticated", false);
			return playMV;
		}

		ServerMemory userMemory = memory.get(sessionCookie.getValue());
		
		if (userMemory == null) {
			playMV.addObject("err", "Utilizatorul nu este autentificat");
			playMV.addObject("isAuthenticated", false);
			return playMV;
		}

		playMV.addObject("score", userMemory.getScore());
		playMV.addObject("nr1", userMemory.getNr1());
		playMV.addObject("nr2", userMemory.getNr2());
		playMV.addObject("isAuthenticated", true);

		return playMV;
	}

	@GetMapping("/play-form")
	public ModelAndView play(@RequestParam(name = "result", required = true) Integer result,
			HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView playMV = new ModelAndView("play.html");

		Cookie[] cookies = req.getCookies();
		Cookie sessionCookie = findCookie("sessionId", cookies);

		if (sessionCookie == null) {
			playMV.addObject("err", "Utilizatorul nu este autentificat");
			playMV.addObject("isAuthenticated", false);
			return playMV;
		}

		ServerMemory userMemory = memory.get(sessionCookie.getValue());
		
		if (userMemory == null) {
			playMV.addObject("err", "Utilizatorul nu este autentificat");
			playMV.addObject("isAuthenticated", false);
			return playMV;
		}

		if (result.equals(userMemory.getNr1() + userMemory.getNr2())) {
			int score = userMemory.getScore() + 1;
			userMemory.setScore(score); // new score
		}

		userMemory.setNr1(createRandomInt()); // new nr1
		userMemory.setNr2(createRandomInt()); // new nr2
		
//		memory.put(sessionCookie.getValue(), userMemory);

		playMV.addObject("score", userMemory.getScore());
		playMV.addObject("nr1", userMemory.getNr1());
		playMV.addObject("nr2", userMemory.getNr2());
		playMV.addObject("isAuthenticated", true);


		return playMV;
	}

	private Cookie createUserCookies(User dbUser, Cookie[] cookies) {		
		Cookie sessionCookie = findCookie("sessionId", cookies);
		if (sessionCookie == null) {
			// create new session cookie
			UUID uuid = UUID.randomUUID();
			memory.put(uuid.toString(), new ServerMemory(
					dbUser.getUsername(), 0, createRandomInt(), createRandomInt()));
//			memory.put(uuid.toString(), new ServerMemory(dbUser.getUsername()));
			sessionCookie = new Cookie("sessionId", uuid.toString());
		}
		return sessionCookie;

	}

	private int createRandomInt() {
		Random rand = new Random();
		return rand.nextInt(10);
	}

	private Cookie findCookie(String name, Cookie[] cookies) {
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie: cookies) {
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}
}
