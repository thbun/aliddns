解决某里云域名解析到动态公网ip 本项目由java编写，采用springboot和定时任务满足需求。实现阿里云的动态ip修改绑定域名。获取本机公网ip使用https://jsonip.com的接口，通过地域ID，AccessKey
ID，AccessKey Secret，修改阿里ddns内解析ip的地址。 本项目使用教程： 1.修改TimeTask.java内如图内容
![img.png](img.png)
2.打包项目（这里使用idea和maven进行打包）
![img_1.png](img_1.png)
3.启动jar包（需运行机器安装java运行环境，例：jdk1.8） 在项目目录下面找到jar包，放入有公网ip的机器进行启动
![img_2.png](img_2.png)
命令行输入启动命令(java -jar aliddns-0.0.1-SNAPSHOT.jar)，项目进行启动
![img_3.png](img_3.png)
注：本项目默认1分钟执行一次，如若修改，请修改TimeTask项目内corn表达式即可。
