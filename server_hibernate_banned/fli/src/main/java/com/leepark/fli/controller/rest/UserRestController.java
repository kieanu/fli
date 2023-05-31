package com.leepark.fli.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {
    // 클라이언트에 에러메시지 보내는 로직 구현 -> response로 클라이언트에 알려줄 수 있음
	
    @Autowired
    UserService uService;

    @GetMapping("/info")
    @ApiOperation(value = "사용자 정보를 조회한다.", response = User.class)
    public User select(@RequestBody User user) {
        return uService.select(user.getUserId());
    }
    
    @PostMapping("/register")
    @ApiOperation(value = "사용자 정보를 추가한다.", response = Boolean.class)
    public Boolean insert(@RequestBody User user) {
    	// 사용자정보가 없을 때만 추가
    	if(uService.isUsed(user.getUserId()) == false) return uService.insert(user);
    	return true;
    }

    @GetMapping("/isUsed")
    @ApiOperation(value = "request parameter로 전달된 id가 이미 사용중인지 반환한다.", response = Boolean.class)
    public Boolean isUsed(@RequestBody User user) {
        return uService.isUsed(user.getUserId());
    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value = "사용자의 정보를 삭제합니다.", response = Boolean.class)
    public Boolean deleteUser(@PathVariable User user) {
        return uService.delete(user.getUserId());
    }
    
//    자체 로그인 기능 X, SNS 로그인
//  @PostMapping("/login")
//  @ApiOperation(value = "로그인 처리 후 성공적으로 로그인 되었다면 loginId라는 쿠키를 내려보낸다.", response = User.class)
//  public User login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
//      User selected = uService.login(user.getUserId());
//      
////       쿠키 추가 정상동작 여부 확인 해야함
////      if (selected != null) {
////          Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getId(), "utf-8"));
////          cookie.setMaxAge(1000 * 1000);
////          response.addCookie(cookie);
//      
//      selected = new User();
//      return selected;
//  }

}
