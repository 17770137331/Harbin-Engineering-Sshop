<%--
  Created by IntelliJ IDEA.
  User: 杨培豪
  Date: 2020/7/22
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="product/selectByPrimaryKey">测试</a>

<a href="product/getProducts">测试分页功能</a>


<h3>测试保存产品</h3>

<form action="product/saveProduct" method="post">

    产品编号：<input type="text" ,name="productid"/><br/>
    产品名称；<input type="text" ,name="name"/><br/>
    工厂：<input type="text" ,name="factory"/><br/>
    <input type="submit" value="提交"><br/>
</form>


<form action="index2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br/>
    <input type="submit" value="点击上传">
</form>

<a href="test">测试异常</a>

</body>
</html>
