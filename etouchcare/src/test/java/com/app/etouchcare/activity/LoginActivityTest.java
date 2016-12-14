package com.app.etouchcare.activity;

import org.junit.Test;
import org.junit.Before;
import com.app.etouchcare.activity.LoginActivity;
import static org.junit.Assert.*;

/**
 * Created by wenzhongzheng on 2016-12-02.
 */
public class LoginActivityTest {
    private LoginActivity mLogin;

    @Before
    public void setmLogin(){
        mLogin = new LoginActivity();
    }


    @Test
    public void isEmailValid() throws Exception {
//        assertThat(mLogin.isEmailValid("abc@gmail.com"),is(true));
        assertTrue(mLogin.isEmailValid("abc@gmail.com"));
        assertFalse(mLogin.isEmailValid("abc"));
    }

    @Test
    public void isPasswordValid() throws Exception {
        assertTrue(mLogin.isPasswordValid("1234"));
        assertFalse(mLogin.isPasswordValid("123"));
    }

}