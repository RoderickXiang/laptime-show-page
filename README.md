<!-- TOC -->

- [简介](#简介)
- [核心功能](#核心功能)
- [具体实现](#具体实现)
  - [数据库设计](#数据库设计)
  - [文件上传以及前端回显](#文件上传以及前端回显)
    - [前端发送数据](#前端发送数据)
    - [后端接收数据](#后端接收数据)
    - [前端回显图片（不经过后端）](#前端回显图片不经过后端)
  - [防止明文密码泄露](#防止明文密码泄露)
    - [导包](#导包)
    - [配置](#配置)
    - [获取加密字符](#获取加密字符)

<!-- /TOC -->
## 简介
springboot的练手项目，没有使用前后端分离，直接使用thymleaf模板引擎和jsp类似
1. 项目简介展示
2. SpringBoot介绍
3. Thymleaf模板引擎
4. SpringSecurity
5. CRUD操作
6. 百度接口
7. AJAX发送数据
8. 前端回显

## 核心功能
1. 对于车辆圈速的展示
2. 简单的CRUD，实现对车辆成绩的增删改查
3. 继承SpringSecurity实现登录
4. 图片上传和前端回显 
5. 调用百度智能接口实现车辆识别

## 具体实现
### 数据库设计
数据库：
由于核心功能是比较圈速，所以核心是对时间的比较（因为要排序）
在数据库中使用time(2)保留秒后两位
后端：
从前端接收字符串，然后转换为特定的日期格式入库
```java
lap_time = new SimpleDateFormat("mm:ss.SSS").parse(lap_timestr);
```
入库：
```xml
<insert id="insertVehicle" parameterType="com.roderick.pojo.Vehicle" useGeneratedKeys="true" keyProperty="id">
    insert into vehicle (model_name,lap_time,introduction)
    value (#{model_name},#{lap_time},#{introduction})
</insert>
```


### 文件上传以及前端回显
#### 前端发送数据
1. 使用post提交数据
2. enctype="multipart/form-data"
3. input标签 type="file" 

```html
<form id="uploadForm" method="post" enctype="multipart/form-data">
    <div class="form-group insertFrom">
        <label for="file">上传图片</label>
        <input type="file" name="file" id="file">
    </div>
    <button id="submit-btn" class="btn btn-primary" type="button">提交</button>
</form>
```

#### 后端接收数据
后端接收：
```java
@PostMapping("/management/vehicleIdentification")
@ResponseBody
public String vehicleIdentification(@RequestParam("file") MultipartFile file) throws IOException {
    file.transferTo(new File("PATH"))   //储存文件
    return null;
}
```

#### 前端回显图片（不经过后端）
获取图片的src：
```javascript
function getObjectURL(file) {   //获取上传图片的地址生成零时文件（名称为uuid）
    let url = null;
    if (window.createObjectURL !== undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL !== undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file)
    } else if (window.webkitURL !== undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}
```
修改图片：
```javascript
let $file = $("#file");    //获取表单数据
let src = getObjectURL($file[0].files[0]);  //转化为DOM对象后，获取文件（使用jQuery对象无法进行操作）
$("#image").attr("src", src)    //修改图片的src属性
```

### 防止明文密码泄露
#### 导包
```xml
<!--加密组件-->
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.2</version>
</dependency>
```

#### 配置
使用一个字符串作为解密密码
```yml
jasypt:
  encryptor:
    password: Roderick  # 明文密码接管
```

#### 获取加密字符
```java
@Autowired
StringEncryptor stringEncryptor;

@Test
public void encryptTest() {
    String admin = stringEncryptor.encrypt("admin");    //加密
    System.out.println(stringEncryptor.decrypt(admin)); //解密
}
```
开始运行（配置了解密密码），可以直接从配置文件中获取解密的密码
```java
@Value("${baidu.SECRET_KEY}")
private String SECRET_KEY;
```
如果没有配置解密密码会导致无法解析ApplicationContext文件<br>

