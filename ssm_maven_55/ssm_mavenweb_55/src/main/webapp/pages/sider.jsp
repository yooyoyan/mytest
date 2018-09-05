<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
        <!-- 导航侧栏 -->
        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="./img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p>张猿猿</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                    </div>
                </div>
                <!-- search form -->
                <!--<form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="搜索...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>-->
                <!-- /.search form -->


                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li class="header">菜单</li>

                    <li id="admin-index"><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> <span>首页</span></a></li>

                    <!-- 菜单 -->


					<security:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="treeview">
                        <a href="#">
                    <i class="fa fa-folder"></i> <span>系统管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                        <ul class="treeview-menu">

                            <li id="admin-login">
                                <a href="${pageContext.request.contextPath }/sysUser/findAllUser">
                            <i class="fa fa-circle-o"></i> 用户管理
                        </a>
                            </li>

                            <li id="admin-register">
                                <a href="${pageContext.request.contextPath }/sysRole/findAllRole">
                            <i class="fa fa-circle-o"></i> 角色管理
                        </a>
                            </li>

                            <li id="admin-404">
                                <a href="${pageContext.request.contextPath }/permission/findAllPermission">
                            <i class="fa fa-circle-o"></i> 权限管理
                        </a>
                            </li>


                        </ul>
                    </li>
					</security:authorize>

					<!-- 可以看到数据管理菜单的角色为    -->
                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_PRODUCT','ROLE_ORDER')">
                    <li class="treeview">
                        <a href="#">
                    <i class="fa fa-pie-chart"></i> <span>数据管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                        <ul class="treeview-menu">
							<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_PRODUCT')">
                            <li id="charts-chartjs">
                                <a href="${pageContext.request.contextPath }/product/findAllProduct">
                            <i class="fa fa-circle-o"></i> 产品管理
                        </a>
                            </li>
							</security:authorize>
							<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_ORDER')">
                            <li id="charts-morris">
                                <a href="${pageContext.request.contextPath }/order/findAllOrder">
                            <i class="fa fa-circle-o"></i> 订单管理
                        </a>
                            </li>
							</security:authorize>
                            
                        </ul>
                    </li>
					</security:authorize>


                  
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
        <!-- 导航侧栏 /-->
 