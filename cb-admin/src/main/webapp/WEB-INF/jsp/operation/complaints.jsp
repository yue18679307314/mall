<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>投诉处理</title>
  <script type="application/javascript">
    function toEditStore(){
      var dataItem = getSelectedGridItem("grid");
      window.location.href = "../complaint/toEditComplaint.do?complaintId=" + dataItem.complaintId;
    }
    function toDeleteComplaint(){
      var dataItem = getSelectedGridItem("grid");
      window.location.href = "../complaint/updateComplaintStatusById.do?complaintId=" + dataItem.complaintId+"&enable="+true;
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
          <li><a href="#">运营管理</a></li>
          <li><a href="#">投诉处理</a></li>
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
          <h2>投诉处理</h2>
        </div>
        <div class="pull-right">

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
          <!-- End .btn-group -->

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
        <div class="toolbar responsive-helper">
          <form style="width: 100%">
            <div class="pull-left">
              <div class="toolbar-field">
                <strong>标题:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="title" data-operator="contains" class="form-control grid-filter" placeholder="请输入标题"/>
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
              <h3>投诉列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
              </div>
            </div>
          </header>
        </div>
        <div class="table-wrapper">
          <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"  >
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-columns>
              <kendo:grid-column  field="complaintId" hidden="true" />
              <kendo:grid-column title="标题" field="title" width="350px" />
              <kendo:grid-column title="内容" field="content"  />
              <kendo:grid-column title="创建时间" field="createTime" width="350px"
                                 format="{0:yyyy-MM-dd HH:mm:ss}" />
            </kendo:grid-columns>
            <kendo:grid-dataBound>
              <script>
                function onDataBound(arg) {
                }
              </script>
            </kendo:grid-dataBound>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
              <kendo:dataSource-schema data="content" total="totalElements">
                <kendo:dataSource-schema-model>
                  <kendo:dataSource-schema-model-fields>
                    <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                  </kendo:dataSource-schema-model-fields>
                </kendo:dataSource-schema-model>
              </kendo:dataSource-schema>
              <kendo:dataSource-transport>
                <kendo:dataSource-transport-read url="pageComplaints.do" type="POST" contentType="application/json"/>
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
  <!-- End #content -->
</div>
<!-- End #main -->

</body>
</html>