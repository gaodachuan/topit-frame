<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/MyCenter.css" rel="stylesheet" type="text/css" />
<script>
   function backParent(){
	   window.parent.loginOut('loginOut.do?');
   }
</script>
</head>
<body>
	<div style="z-index:0;position:absolute;left:0px;top:0px;width:100%;height:100%;">
		<div style="overflow-x:hidden;overflow-y:hidden;position:absolute;z-index:10;left:0px;top:0px;width:100%;height:59px;">
			<div style="z-index:0;position:absolute;left:0px;top:0px;width:100%;height:59px;">
				<!--Logo显示处-->
				<div tabindex="1" style="z-index:11;position:absolute;outline:none;cursor:default;left:25px;top:0px;width:94px;height:59px;">
					<div style="overflow-x:hidden;overflow-y:hidden;position:absolute;z-index:10;left:5px;top:12px;width:84px;height:46px;">
						<div style="z-index:0;position:absolute;cursor:default;left:0px;top:4px;width:84px;height:39px;">
							<div
								style="overflow: hidden; position: absolute; z-index: 10; width: 84px; height: 39px; background-image: url(http://images.gmx.com/images/outsource/application/mailclient/com/resource/mailclient/widgets/blue/parts/overview/logo_top-3123981904.png); background-position: 0px 0px; background-repeat: no-repeat no-repeat; ">
							</div>
						</div>
					</div>
				</div>
				<!--快速通道栏-->
				<div style="z-index:11;position:absolute;cursor:default;left:127px;top:18px;width:566px;height:41px;">
					<div style="overflow-x:hidden;overflow-y:hidden;position:absolute;z-index:10;width:566px;height:41px;padding-left:30px">
						<input id="quickInput" class="easyui-searchbox" data-options="prompt:'',searcher:doSearch" style="width:230px">
					</div>
				</div>
				<div>
					<div>
						<!--导航按钮-->
						<div tabindex="1" class="navbtnRegion" style="right:367px;width:72px;">
							<a href="javascript:alert('首页')" style="text-decoration:none">
								<div class="navbtnRegionFront" style="width:62px;">
									<div class="navbtnRegionFront_TitleBack">
										<div class="navbtnRegionFront_Title">首页</div>
									</div>
									<div class="navbtnRegionFront_ImageBack" style="left:23px;">
										<div class="navbtnRegionFront_Image" style="background-position: -144px 0px"></div>
									</div>
								</div>
							</a>
							<div class="navbtnRegionBack">
								<div class="navbtnRegionBack_Left"></div>
								<div class="navbtnRegionBack_Middle"></div>
								<div class="navbtnRegionBack_Right"></div>
							</div>
						</div>


						<!--导航按钮-->
						<div tabindex="1" class="navbtnRegion" style="right: 282px;width: 85px;">
							<a href="javascript:alert('换密码')" style="text-decoration:none">
								<div class="navbtnRegionFront" style="width:75px;">
									<div class="navbtnRegionFront_TitleBack">
										<div class="navbtnRegionFront_Title">换密码</div>
									</div>
									<div class="navbtnRegionFront_ImageBack" style="left:30px;">
										<div class="navbtnRegionFront_Image" style="background-position: -216px 0px"></div>
									</div>

								</div>
							</a>
							<div class="navbtnRegionBack">
								<div class="navbtnRegionBack_Left"></div>
								<div class="navbtnRegionBack_Middle"></div>
								<div class="navbtnRegionBack_Right"></div>
							</div>
						</div>

						<!--导航按钮-->
						<div tabindex="1" class="navbtnRegion" style="right: 222px;width: 60px;">
							<a href="javascript:alert('论坛')" style="text-decoration:none">
								<div class="navbtnRegionFront" style="width:50px;">
									<div class="navbtnRegionFront_TitleBack">
										<div class="navbtnRegionFront_Title">论坛</div>
									</div>
									<div class="navbtnRegionFront_ImageBack" style="left:17px;">
										<div class="navbtnRegionFront_Image" style="background-position: -248px 0px"></div>
									</div>

								</div>
							</a>
							<div class="navbtnRegionBack">
								<div class="navbtnRegionBack_Left"></div>
								<div class="navbtnRegionBack_Middle"></div>
								<div class="navbtnRegionBack_Right"></div>
							</div>
						</div>

						<!--导航按钮-->
						<div tabindex="1" class="navbtnRegion" style="right: 162px;width: 60px;">
							<a href="javascript:alert('帮助')" style="text-decoration:none">
								<div class="navbtnRegionFront" style="width:50px;">
									<div class="navbtnRegionFront_TitleBack">
										<div class="navbtnRegionFront_Title">帮助</div>
									</div>
									<div class="navbtnRegionFront_ImageBack" style="left:17px;">
										<div class="navbtnRegionFront_Image" style="background-position: -280px 0px"></div>
									</div>

								</div>
							</a>
							<div class="navbtnRegionBack">
								<div class="navbtnRegionBack_Left"></div>
								<div class="navbtnRegionBack_Middle"></div>
								<div class="navbtnRegionBack_Right"></div>
							</div>
						</div>
						<!--导航按钮-->
						<div tabindex="1" class="navbtnRegion" style="right: 100px;width: 62px;">
							<a href="javascript:alert('设置')" style="text-decoration:none">
								<div class="navbtnRegionFront" style="width:52px;">
									<div class="navbtnRegionFront_TitleBack">
										<div class="navbtnRegionFront_Title">设置</div>
									</div>
									<div class="navbtnRegionFront_ImageBack" style="left:18px;">
										<div class="navbtnRegionFront_Image" style="background-position: -376px 0px"></div>
									</div>

								</div>
							</a>
							<div class="navbtnRegionBack">
								<div class="navbtnRegionBack_Left"></div>
								<div class="navbtnRegionBack_Middle"></div>
								<div class="navbtnRegionBack_Right"></div>
							</div>
						</div>
						<!--导航按钮-->
						<div tabindex="1" class="navbtnRegion" style="right: 0px;width: 100px;">
							<a href="javascript:backParent()"  target="_self" style="text-decoration:none">
								<div class="navbtnRegionFront" style="width:90px;">
									<div class="navbtnRegionFront_TitleBack">
										<div class="navbtnRegionFront_Title">退出</div>
									</div>
									<div class="navbtnRegionFront_ImageBack" style="left:37px;">
										<div class="navbtnRegionFront_Image" style="background-position: -296px 0px"></div>
									</div>

								</div>
							</a>
							<div class="navbtnRegionBack">
								<div class="navbtnRegionBack_Left"></div>
								<div class="navbtnRegionBack_Middle"></div>
								<div class="navbtnRegionBack_Right"></div>
							</div>
						</div>

					</div>
				</div>
				<div
					style="position: absolute; top: 0px; left: 0px;width: 100%; height: 59px; background-image: url(http://images.gmx.com/images/outsource/application/mailclient/com/resource/mailclient/widgets/blue/common/navigator_bg_left-3190120233.jpg);  z-index: 5; background-position: 0px 0px; background-repeat: no-repeat no-repeat; "></div>
			</div>
		</div>
		<div
			style="position: absolute; top: 0px; left: 0px;width: 100%; height: 59px; background-image: url(http://images.gmx.com/images/outsource/application/mailclient/com/resource/mailclient/widgets/blue/common/navigator_bg-3190120233.jpg);  z-index: 5; background-position: 0px 0px; background-repeat: repeat no-repeat; "></div>
	</div>

</body>

</html>

