<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会员注册页面</title>

		<%--	静态包含 base标签/css样式/jQuery文件	--%>
		<%@include file="/pages/common/head.jsp"%>

		<script type="text/javascript">
			// 页面加载完成之后
			$(function () {

				//判断用户名是否重复
				$("#username").blur(function (){//失焦
					//1.获取用户名
					var username=this.value;
					$.getJSON("http://localhost:8080/book/userServlet",
							"action=ajaxExistsUsername&username="+username,
							function (data){
								console.log(data)//可以在f12里的 控制台 查看此时是false还是true

								//existsUsername是之前在Servlet里保存的结果,因为是json,所以用key点运算,取出里面的value
								if (data.existsUsername) {
									$("span.errorMsg").text("用户名已存在！");
								} else {
									$("span.errorMsg").text("用户名可用！");
								}
							}
					)

				});




				//给验证码的图片 绑定单击事件
				$("#code_img").click(function (){//这里的单击事件,就是操作验证码图片
					//this指代这个验证码图片的img标签
					//src是指这个验证码图片的src地址 ,也就是img标签的src地址
					this.src="${basePath}kaptcha.jpg?d=" +new Date();

				});


				// 给注册绑定单击事件
				$("#sub_btn").click(function () {
					// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var usernameText = $("#username").val();
					//2 创建正则表达式对象
					var usernamePatt = /^\w{5,12}$/;
					//3 使用test方法验证
					if (!usernamePatt.test(usernameText)) {
						//4 提示用户结果
						$("span.errorMsg").text("用户名不合法！");

						return false;
					}

					// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var passwordText = $("#password").val();
					//2 创建正则表达式对象
					var passwordPatt = /^\w{5,12}$/;
					//3 使用test方法验证
					if (!passwordPatt.test(passwordText)) {
						//4 提示用户结果
						$("span.errorMsg").text("密码不合法！");

						return false;
					}

					// 验证确认密码：和密码相同
					//1 获取确认密码内容
					var repwdText = $("#repwd").val();
					//2 和密码相比较
					if (repwdText != passwordText) {
						//3 提示用户
						$("span.errorMsg").text("确认密码和密码不一致！");

						return false;
					}

					// 邮箱验证：xxxxx@xxx.com
					//1 获取邮箱里的内容
					var emailText = $("#email").val();
					//2 创建正则表达式对象
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					//3 使用test方法验证是否合法
					if (!emailPatt.test(emailText)) {
						//4 提示用户
						$("span.errorMsg").text("邮箱格式不合法！");

						return false;
					}

					// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
					var codeText = $("#code").val();

					//去掉验证码前后空格
					// alert("去空格前：["+codeText+"]")
					codeText = $.trim(codeText);
					// alert("去空格后：["+codeText+"]")

					if (codeText == null || codeText == "") {
						//4 提示用户
						$("span.errorMsg").text("验证码不能为空！");

						return false;
					}

					// 去掉错误信息
					$("span.errorMsg").text("");

				});

			});

		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册书城会员</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">

									<input type="hidden" name="action" value="regist">

									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
											value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}"	/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 120px;" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;
											width: 100px;height: 28px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%-- 静态包含 页脚内容	--%>
		<%@include file="/pages/common/footer.jsp"%>
	</body>
</html>