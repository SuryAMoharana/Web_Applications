package com.task.services;

import com.task.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "UserServices", url="http://localhost:8080")
public interface UserServices {
    @GetMapping("/api/user/profile")
    public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
