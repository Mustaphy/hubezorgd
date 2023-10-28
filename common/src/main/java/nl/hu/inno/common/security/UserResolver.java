package nl.hu.inno.common.security;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class UserResolver implements HandlerMethodArgumentResolver {
    private final UserRepository users;
    public UserResolver(UserRepository users) {
        this.users = users;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameter().getType() == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String username = webRequest.getHeader("Authentication-Hack");

        if (username == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No user found, use Authentication-Hack header");
        }

        Optional<User> result = users.findById(username);

        if (result.isEmpty()) {
            User user = new User(username, "admin");
            users.save(user);
            return user;
        } else {
            return result.get();
        }
    }
}
