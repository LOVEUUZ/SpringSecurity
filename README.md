# SpringSecurity
SpringSecurity的参考版本

通过以下视频学习
https://www.bilibili.com/video/BV1mm4y1X7Hc?p=1&vd_source=3bb0ba8acee722ac5136949659a79732

过滤链流程
我们加了一个自定义token过滤器 JwtAuthenticationTokenFilter 在 SecurityConfig 中将其设置在 UsernamePasswordAuthenticationFilter 过滤器之前

然后实现了 UserDetailsService 接口,就会修改默认设置使其在数据库中进行查询用户,将用户实体类和权限封装进实现了 UserDetails 的实体类

最后成功会来到登录接口的下半部分将token封装进入统一相应返回并存入Redis


![image-20211214151515385](https://user-images.githubusercontent.com/106130061/185892246-8eb6d319-65c1-4eee-9b9d-4cacd1288536.png)

![image-20211215095331510](https://user-images.githubusercontent.com/106130061/185892256-aa8cef88-6529-4225-8e8c-9aeb23920ff9.png)
