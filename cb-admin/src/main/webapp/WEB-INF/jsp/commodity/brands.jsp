<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>品牌管理</title>

    <script type="text/javascript">

        function detailItem(){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "brandDetail.do?brandId=" + dataItem.brandId;
            }
        }

        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditBrand.do?brandId=" + dataItem.brandId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认删除吗？", function (result) {
                    if (result) {
                        $.get("removeBrandById.do", {
                            brandId: dataItem.brandId
                        }, function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                // $("#grid").data("kendoGrid").dataSource.read();
                                window.location.reload();
                            } else {
                                bootbox.alert("失败");
                            }
                        });
                    }
                });
            }
        }

        function enabledItem(enabled) {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $.get("enableBrandById.do", {
                    brandId: dataItem.brandId,
                    enabled: enabled,
                    rad: Math.random()
                }, function (data) {
                    if ("success" == data) {
                        commonNotify("操作成功！", "success");
                        $("#grid").data("kendoGrid").dataSource.read();
                    } else {
                        commonNotify("操作失败!", "error");
                    }
                });
            }
        }
    </script>
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
                    <li class="active"><a href="#">品牌管理</a></li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>
    <div id="content" class="clearfix">
        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>品牌管理</h2>
                </div>
<%--                <div class="pull-right">
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
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>品牌编码:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" type="text" data-filter="brandNo" data-operator="contains" class="form-control grid-filter" placeholder="请输入品牌编码"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>品牌名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" type="text" data-filter="brandName" data-operator="contains" class="form-control grid-filter" placeholder="请输入品牌名称"/>
                            </div>
                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                        <!-- End .pull-right -->
                    </form>
                </div>
                <!-- End .toolbar -->

                <div class="spacer-10"></div>

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>品牌列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:void(0);"  onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>
                                <a href="toAddBrand.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:editItem();"  class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:removeItem();"  class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="enabledItem(true)" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;启用</a>
                                <a href="javascript:void(0);" onclick="enabledItem(false)" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 停用</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="450" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="所属分类" filterable="false" field="category" width="150px" template="#=category.categoryName#"/>
                            <kendo:grid-column title="图片" field="picPath" width="140px" template="<img src='#=picPath#'  width='120px' height='60px'/>" sortable="false" filterable="false"/>
                            <kendo:grid-column title="编码" field="brandNo" width="100px"/>
                            <kendo:grid-column title="名称" field="brandName" width="100px" template="<a href='brandDetail.do?brandId=#=brandId#'>#=brandName#</a>"/>
                            <kendo:grid-column title="标题"  field="brandTitle" width="100px"/>
                            <kendo:grid-column title="关键字" filterable="false" field="brandKey" width="100px"/>
                            <kendo:grid-column title="seo关键字" filterable="false" field="seoKey" width="100px"/>
                            <kendo:grid-column title="seo标题" filterable="false" field="seoTitle" width="100px"/>
                            <kendo:grid-column title="显示" filterable="false" field="display" width="100px" template="#= display ? '是' : '否' #"/>
                            <kendo:grid-column title="热门" filterable="false" field="hot" width="100px" template="#= hot ? '是' : '否' #"/>
                            <kendo:grid-column title="状态" filterable="false" field="enabled" width="100px" template="#= enabled ? '启用' : '停用' #"/>
                            <kendo:grid-column title="创建时间" field="createTime" width="150px" format="{0:yyyy-MM-dd HH:mm}" filterable="false"/>
                            <kendo:grid-column title="备注" field="remark" filterable="false" width="200px"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                                <kendo:dataSource-schema-model>
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                                    </kendo:dataSource-schema-model-fields>
                                </kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageBrands.do" type="POST" contentType="application/json"/>
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

            <!-- End .inner-padding -->
        </div>
        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
</div>
</body>
</html>
