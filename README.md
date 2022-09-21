解决某里云域名解析到动态公网ip
         本项目由java编写，采用springboot和定时任务满足需求。实现阿里云的动态ip修改绑定域名。获取本机公网ip使用https://jsonip.com的接口，通过地域ID，AccessKey ID，AccessKey Secret，修改阿里ddns内解析ip的地址。
本项目使用教程：
1.修改TimeTask.java内如图内容

![image](https://user-images.githubusercontent.com/50077379/191391784-8d5ffa3d-791c-4eaa-816c-574bc0b7567a.png)

2.打包项目（这里使用idea和maven进行打包）

![image](https://user-images.githubusercontent.com/50077379/191391854-38541340-1200-4fb1-aa37-6c3d790b4e46.png)

3.启动jar包（需运行机器安装java运行环境，例：jdk1.8）
在项目目录下面找到jar包，放入有公网ip的机器进行启动

![image](https://user-images.githubusercontent.com/50077379/191391883-5cf7424d-7030-46ad-830e-7bdda8707122.png)

命令行输入启动命令(java -jar aliddns-0.0.1-SNAPSHOT.jar)，项目进行启动

![image](https://user-images.githubusercontent.com/50077379/191391901-0937cfa4-5d11-42cb-9813-8ec7fe80eb0f.png)

注：本项目默认1分钟执行一次，如若修改，请修改TimeTask项目内corn表达式即可。
