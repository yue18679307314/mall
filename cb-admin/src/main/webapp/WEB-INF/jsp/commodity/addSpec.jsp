<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>新增商品规格</title>
    <%--<script type="application/javascript">--%>
    <%--$(document).ready(function () {--%>
    <%--$('#catalogTrigger').focusin(function (e) {--%>
    <%--$('#catalogDialog').modal();--%>
    <%--e.preventDefault();--%>
    <%--});--%>

    <%--//            $("#validateSubmitForm").validate({--%>
    <%--//                rules: {--%>
    <%--//                    groupName: {--%>
    <%--//                        required: true,--%>
    <%--//                        minlength: 2,--%>
    <%--//                        maxlength: 32--%>
    <%--//                    },--%>
    <%--//                    categoryName: {--%>
    <%--//                        required: true,--%>
    <%--//                        minlength: 2,--%>
    <%--//                        maxlength: 32--%>
    <%--//                    },--%>
    <%--//                    sortOrder: {--%>
    <%--//                        required: true,--%>
    <%--//                        digits: true--%>
    <%--//                    }--%>
    <%--//                },--%>
    <%--//                onkeyup: false,--%>
    <%--//                onfocusout: function (e) {--%>
    <%--//                    $(e).valid();--%>
    <%--//                }--%>
    <%--//            });--%>

    <%--});--%>
    <%--</script>--%>
</head>
<body>


<jsp:include page="../layouts/left.jsp"/>

<jsp:include page="../layouts/sidebarRight.jsp"/>

<div id="main" class="clearfix">

    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <!-- * This is the responsive logo * -->
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <!-- * This is the trigger that will show/hide the menu * -->
                <!-- * if the layout is in responsive mode              * -->
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">商品管理</a></li>
                    <li><a href="#">商品规格</a></li>
                    <li class="active">新增商品规格</li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>
    <!-- End #header-main -->

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>新增商品规格</h2>
                </div>
                <div class="pull-right">


                </div>
            </div>
            <!-- End .inner-padding -->
        </header>
        <!-- End #header-sec -->


        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <a href="#" class="btn small-toggle-btn" data-toggle-sidebar="left"></a>
                    <a href="#" id="lockscreen-slider-trigger" class="btn">
                        <i class="fa fa-lock"></i>&nbsp; Lock screen
                    </a>
                </div>
                <div class="pull-right">
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div>
            <!-- End .actionbar-->


            <div class="inner-padding">
                <form:form action="addSpec.do" cssClass="form-horizontal" method="post" commandName="spec">
                    <fieldset>
                        <legend>新增商品规格</legend>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>商品规格名称：</label>
                            </div>
                            <div class="col-sm-6">
                                <form:input cssClass="form-control" required="true" path="specName" aria-required="true"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-6">
                                <textarea class="form-control" name="remark"></textarea>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </div>
        <div class="spacer-40"></div>
        <div class="hr-totop"><span>Top</span></div>
        <div class="spacer-40"></div>
        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->


</body>
</html>
