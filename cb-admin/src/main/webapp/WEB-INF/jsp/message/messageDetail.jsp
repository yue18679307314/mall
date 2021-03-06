<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>消息详情</title>
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
                    <li><a href="#">消息中心配置</a></li>
                    <li class="active">消息详情</li>
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
                    <h2>消息详情</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="message.do">
                        <i class="fa fa-reply"></i>
                    </a>

                </div>
            </div>
        </header>
        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <ul class="ext-tabs">
                        <li class="active">
                            <a href="#content-tab-1">基本信息</a>
                        </li>
                        <li >
                            <a href="#content-tab-2">详细信息</a>
                        </li>
                    </ul>
                </div>
                <div class="pull-right">
                    <a class="btn" href="#" id="lockscreen-slider-trigger">
                        <i class="fa fa-lock"></i>
                    </a>
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div><!-- End .actionbar-->
            <div class="tab-content">
                <div id="content-tab-1" class="tab-pane active">
                    <div class="inner-padding">
                        <div class="row">
                            <div class="col-sm-2">
                                <label> 推送标题：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${message.pushTitle}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label> 作者：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${message.messageDespatcher}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label> 消息摘要：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${message.messageDigest}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label> 商品图片：</label>
                            </div>
                            <div class="col-sm-9">
                                <%--图片上传控件--%>
                                <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                                <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                                <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                                <script type="text/javascript">
                                    $(function(){
                                        var initPreview = new Array();//展示元素
                                        var initPreviewConfig = new Array();//展示设置
                                        //初始化图片上传组件
                                        $("#picUrl").fileinput({
                                            uploadUrl: "/admin/uploads/uploadFile/MESSAGE.do",
                                            showCaption: true,
                                            minImageWidth: 50,
                                            minImageHeight: 50,
                                            showUpload:false, //是否显示上传按钮
                                            showRemove :false, //显示移除按钮
                                            showPreview :true, //是否显示预览
                                            showCaption:false,//是否显示标题
                                            browseOnZoneClick: false,//是否显示点击选择文件
                                            language: "zh" ,
                                            showBrowse : false,
                                            showClose: false,
                                            maxFileSize : 2000,
                                            allowedFileExtensions: ["jpg", "png", "gif"],
                                            autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                            overwriteInitial: false,//不覆盖已存在的图片
                                            browseClass:"btn btn-primary", //按钮样式
                                            layoutTemplates:{
                                                actionUpload:'',    //设置为空可去掉上传按钮
                                                actionDelete:''
                                            },
                                            maxFileCount: 10  //上传的个数
                                        });
                                        //加载图片
                                        var a='${listAttachment}';
                                        var json=eval('(' + a + ')')
                                        for(var i=0,l=json.length;i<l;i++){
                                            initPreview[i]  = json[i].filePath;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/MESSAGE.do";
                                            config.key=json[i].timeStr;
                                            initPreviewConfig[i]=config;
                                            $("#picUrl").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            var html='<input name="imgurl" type="hidden" id="'+json.inputId+'" value="'+json[i].filePath+','+json[i].fileName+','+json[i].inputId+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                        }
                                    })
                                </script>
                                <input id="picUrl"  name="file" type="file" class="file-loading" accept="image/*" multiple>
                                <div id="imgDiv">
                                </div>
                                <%--图片上传控件结束--%>
                            </div>
                        </div>
                        <div class="spacer-25"></div>

                    </div><!-- End .inner-padding -->
                </div>
                <div id="content-tab-2" class="tab-pane ">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>消息内容</h3>
                        </div>
                        <div class="spacer-25"></div>
                        <div class="row">
                            <div class="col-sm-12" style="padding: 16px;overflow: hidden" >
                                ${message.messageContent}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="spacer-30"></div>
                <hr>
                <div class="spacer-30"></div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="btn-group pull-right">
                            <a class="btn btn-default pull-right" href="message.do">返回</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->
</div>

</body>
</html>
