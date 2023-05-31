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

    @GetMapping("/info/{userId}/{userSns}")
    @ApiOperation(value = "사용자 정보를 조회한다.", response = User.class)
    public User select(@PathVariable String userId, @PathVariable Integer userSns) {
        return uService.select(userId, userSns);
    }
    
    @PostMapping("/register")
    @ApiOperation(value = "사용자 정보를 추가한다.", response = Boolean.class)
    public Boolean insert(@RequestBody User user) {
    	// 사용자정보가 없을 때만 추가
    	if(uService.insert(user) > 0) return true;
    	return false;
    }

    @GetMapping("/isUsed/{userId}/{userSns}")
    @ApiOperation(value = "request parameter로 전달된 id가 이미 사용중인지 반환한다.", response = Boolean.class)
    public Boolean isUsed(@PathVariable String userId, @PathVariable Integer userSns) {
        return uService.isUsed(userId, userSns);
    }

    @DeleteMapping("/delete/{userId}/{userSns}")
    @ApiOperation(value = "사용자의 정보를 삭제합니다.", response = Boolean.class)
    public void deleteUser(@PathVariable String userId, @PathVariable Integer userSns) {
        uService.delete(userId, userSns);
    }
    
}
