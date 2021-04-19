package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineUser;

public interface UserLoginService {

  ReslineUser userLogin(String userNumber, String passWord);

}
