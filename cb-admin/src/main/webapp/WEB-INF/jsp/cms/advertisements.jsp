<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="bodyCss" value="hide-right-sidebar" scope="request"/>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>广告管理</title>
    <script type="text/javascript">
        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditAdvertisement.do?advertId=" + dataItem.advertId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确定删除吗？", function (result) {
                    if (result) {
                        $.get("removeAdvertisementById.do", {
                            advertId: dataItem.advertId,
                        }, function (data) {
                            if (data != 0) {
                                commonNotify("删除成功！", "success");
                                $("#grid").data("kendoGrid").dataSource.read();
                            } else {
                                commonNotify("删除失败!", "error");
                            }
                        });
                    }
                });
            }
        }

        function enabledItem(enabled) {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $.get("enableAdvertisementById.do", {
                    advertId: dataItem.advertId,
                    enabled: enabled,
                }, function (data) {
                    if (data != 0) {
                        commonNotify("操作成功！", "success");
                        $("#grid").data("kendoGrid").dataSource.read();
                    } else {
                        commonNotify("操作失败!", "error");
                    }
                });
            }
        }

        function formatAdvertiseType(advertisementType) {
            switch (advertisementType) {
                case "PTHOTO_AND_TEXT":
                    return "图文";
                case "TEXT":
                    return "纯文字";
                case "VIDEO":
                    return "视频";
            }
        }

        function formatAdvertisementPlace(advertisementPlace) {
            switch (advertisementPlace) {
                case "HOME":
                    return "首页";
                case "MIDDLE":
                    return "中部";
            }
        }


    </script>
</head>

<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>
<div id="main" class="clearfix">
    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="../console/dashboard.do">首页</a></li>
                    <li><a href="#">内容管理</a></li>
                    <li class="active"><a href="articles.do">广告管理</a></li>
                </ul>
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
    </header>
    <div id="content" class="clearfix">
        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>广告管理</h2>
                </div>
                <%--<div class="pull-right">
                    <div class="btn-group">
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-star"></i>
                        </a>
                        <a class="btn btn-default" href="#" id="modal-update-trigger">
                            Modal
                        </a>
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-cog"></i>
                        </a>
                    </div>
                </div>--%>
            </div>
        </header>
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

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h4>广告列表</h4>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddAdvertisement.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="enabledItem(true)" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;启用</a>
                                <a href="javascript:void(0);" onclick="enabledItem(false)" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 停用</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="500" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="广告标题"  field="advertTitle" width="150"/>
                            <kendo:grid-column title="广告编码"  field="advertCode" width="150"/>
                            <kendo:grid-column title="广告类型" filterable="false" field="advertisementType" width="100" template="#=formatAdvertiseType(advertisementType)#"/>
                            <kendo:grid-column title="广告位" filterable="false" field="advertisementType" width="100" template="#=formatAdvertisementPlace(advertisementPlace)#"/>
                            <kendo:grid-column title="创建时间" filterable="false" field="createTime" width="120" format="{0:yyyy-MM-dd HH:mm}"/>
                            <kendo:grid-column title="状态" filterable="false" field="enabled" template="#= enabled ? '启用' : '停用' #" width="80"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageAdvertisements.do" type="POST" contentType="application/json"/>
                                <kendo:dataSource-transport-parameterMap>
                                    <script>
                                        function parameterMap(options, type) {
                                            return JSON.stringify(options);
                                        }
                                    </script>
                                </kendo:dataSource-transport-parameterMap>
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </div>

            </div>

            <div class="spacer-40"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-40"></div>

        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>
</body>
</html>
