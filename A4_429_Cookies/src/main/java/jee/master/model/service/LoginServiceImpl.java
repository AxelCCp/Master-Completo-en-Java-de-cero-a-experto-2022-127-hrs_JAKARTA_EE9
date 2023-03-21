package jee.master.model.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImpl implements  ILoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {

        Cookie[]cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
        return cookieOptional;
    }
}
