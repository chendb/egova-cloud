# OAuth2 Server

知道要点：

一、SSO单点登录
    SSO的定义是在多个应用系统，用户只需要登录一次就可以访问所有相互信任的应用系统。

二、OAuth2
    
    1、表结构
    表结构定义:https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
    oauth_access_token：用户存储访问资源的token
    oauth_client_details：存储客户端资源（我们可以把平台中的资源分成:移动端资源，给第三方平台外部资源，后台服务资源三类。我们可以把这三类信息以三条记录方式存储在表中）
    
    2、授权模式
    授权码模式(Authorization code)
        （A）用户访问客户端，后者将前者导向认证服务器。
        （B）用户选择是否给予客户端授权。
        （C）假设用户给予授权，认证服务器将用户导向客户端事先指定的"重定向URI"（redirection URI），同时附上一个授权码。
        （D）客户端收到授权码，附上早先的"重定向URI"，向认证服务器申请令牌。这一步是在客户端的后台的服务器上完成的，对用户不可见。
        （E）认证服务器核对了授权码和重定向URI，确认无误后，向客户端发送访问令牌（access token）和更新令牌（refresh token）。
    简化模式(Impilict)
       （A）客户端将用户导向认证服务器。
       （B）用户决定是否给于客户端授权。
       （C）假设用户给予授权，认证服务器将用户导向客户端指定的"重定向URI"，并在URI的Hash部分包含了访问令牌。
       （D）浏览器向资源服务器发出请求，其中不包括上一步收到的Hash值。
       （E）资源服务器返回一个网页，其中包含的代码可以获取Hash值中的令牌。
       （F）浏览器执行上一步获得的脚本，提取出令牌。
       （G）浏览器将令牌发给客户端。
    密码模式
        （A）用户向客户端提供用户名和密码。
        （B）客户端将用户名和密码发给认证服务器，向后者请求令牌。
        （C）认证服务器确认无误后，向客户端提供访问令牌。
    客户端模式
        （A）客户端向认证服务器进行身份认证，并要求一个访问令牌。
        （B）认证服务器确认无误后，向客户端提供访问令牌。
    
    
    3、jwt
        定  义:JWT 是JSON风格轻量级的授权和身份认证规范，可实现无状态、分布式的Web应用授权；
        流程图:http://images2015.cnblogs.com/blog/488490/201701/488490-20170117211606396-1300480328.png


三、注意事项
    
    1、使用jdbc存储token方式没有验证成功，在授权成功获取access token时报如下错误：
    Possible CSRF detected - state parameter was required but no state could be found
    
    2、自定义Auth Server 登录页面 时不起作用。
     解决方法：
      A、WebSecurityConfigurerAdapter 下重写如下方法并指定loginPage("/login")
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
                    .anyRequest().authenticated();
        }
      B、WebMvcConfigurerAdapter 下重写如下方法并指定登录页面的ViewName
          @Override
          public void addViewControllers(ViewControllerRegistry registry) {
              registry.addViewController("/login").setViewName("login");
          }
          
    3、授权成功后，重定向至子系统时默认转向login路由
      说明：该问题是因为出现1所描述的错误，而重定向至login页面。解决1此问题即不存在啦

