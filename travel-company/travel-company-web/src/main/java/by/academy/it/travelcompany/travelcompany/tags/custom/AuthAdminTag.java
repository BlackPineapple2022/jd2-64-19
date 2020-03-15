package by.academy.it.travelcompany.travelcompany.tags.custom;

import by.academy.it.travelcompany.travelcompany.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

public class AuthAdminTag extends ConditionalTagSupport {

    private String path = "";

    @Override
    protected boolean condition() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        return ((user != null) && (user.getRole().equals("ADMIN")));
    }

    public void setPath(String path) {
        this.path = path;
    }
}