<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div class="sidebar-nav-fixed affix" style="margin-left: -15px">
    <div class="well">
        <ul class="nav navbar restNavbar">
            <li class="menu-head text-center">
                <spring:message code="common.admin"/>
            </li>
            <li><hr></li>
            <li>
                <a class="restNavLi" href="admin/show_hotels">
                    <h5><strong><spring:message code="hotels.title"/></strong></h5>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="admin/show_users">
                    <h5><strong><spring:message code="common.users"/></strong></h5>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="admin/show_super_bookings">
                    <h5><strong><spring:message code="common.votes"/></strong></h5>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="admin/show_votes">
                    <h5><strong><spring:message code="common.bookings"/></strong></h5>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="admin/show_apt_types">
                    <h5><strong><spring:message code="common.apt_types_short"/></strong></h5>
                </a>
            </li>
            <li>
                <a class="restNavLi" href="admin/show_regions">
                    <h5><strong><spring:message code="common.regions"/></strong></h5>
                </a>
            </li>
            <li><hr></li>
            <li><hr></li>
        </ul>
    </div>
</div>

</body>
</html>