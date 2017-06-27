package com.kirak.web;

import com.kirak.model.User;
import com.kirak.model.abstraction.BaseEntity;
import com.kirak.model.abstraction.BaseIntEntity;
import com.kirak.to.UserTo;
import com.kirak.util.model.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

/**
 * Created by Kir on 27.06.2017.
 */
public class AuthorizedUser{

    public static int id = BaseIntEntity.START_SEQ;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }


    //    private static final long serialVersionUID = 1L;
//
//    private final UserTo userTo;
//
//    public AuthorizedUser(User user) {
//        super(user.getEmail(), user.getPassword(), true, true, true,
//                true, user.getRoles());
//        this.userTo = UserUtil.asTo(user);
//    }
//
//    public static AuthorizedUser safeGet() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null) {
//            return null;
//        }
//        Object principal = auth.getPrincipal();
//        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
//    }
//
//    public static AuthorizedUser get() {
//        AuthorizedUser user = safeGet();
//        requireNonNull(user, "No authorized user found");
//        return user;
//    }
//
//    public static int id() {
//        return (int) get().userTo.getId();
//    }
//
//    public UserTo getUserTo() {
//        return userTo;
//    }
//
//    @Override
//    public String toString() {
//        return userTo.toString();
//    }


}
