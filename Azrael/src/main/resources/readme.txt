https://open.weixin.qq.com/
xiaoyuzhongketang@126.com
Fltrp9899

https://mp.weixin.qq.com/
xiaoyuzhongketang@163.com
Fltrp9899

服务器IP：218.245.2.174
用户名/密码：wangwudi/wangwudi123
登录方式： ssh

重置目录可修改权限命令：sudo quanxian.sh
重启Tomcat命令：sudo tomcat_restart.sh
查看Tomcat进程命令：sudo cata_tomcat6

有修改权限的目录列表：
用户wangwudi目录：/home/wangwudi
Tomcat临时目录：/usr/local/tomcat6/work
网站根目录：/usr/local/tomcat6/webapps/site
Tomcat根目录：/usr/local/tomcat6/webapps/ROOT 


jar -xvf Azrael.war

AppID(应用ID)wxd032fd57ca9a9a79
AppSecret(应用密钥)587b241d21edec37b3badff8f179b0b6 

URL(服务器地址) http://218.245.2.174/site/callback/
Token(令牌) xiaoyuzhongketang
EncodingAESKey(消息加解密密钥) p7jKYXjcAZjGvW4kYlFikDeHINXSl1q6pcfI1xU7Nd9
消息加解密方式 明文模式


公众平台的开发接口的access_token长度将增长，其存储至少要保留512个字符空间。

微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次。

假如服务器无法保证在五秒内处理并回复，必须直接回复空串（是指回复一个空字符串，而不是一个XML结构体中content字段的内容为空，请切勿误解），微信服务器不会对此作任何处理，并且不会发起重试。

请注意，回复图片等多媒体消息时需要预先上传多媒体文件到微信服务器，只支持认证服务号。