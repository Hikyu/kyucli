# kyucli

## 简单的命令行工具

usage: help
 -cd <folder|file>   cd the folder specified with argument
 -help               print this message
 -run <app>          run the application specified with argument
 -url <uri>          open the url specified with argument

编辑argoptions.properties与booloptions.properties进行配置

argoptions.properties：带参数的命令

booloptions.properties：不带参数命令

目前实现功能：

-cd folder：快速打开文件夹  可在argoptions.properties中配置关键字与对应的文件夹绝对路径

-run application：快速启动应用程序 可在argoptions.properties中配置关键字与对应的程序绝对路径

-url uri：快速使用默认浏览器打开网页 可在argoptions.properties中配置关键字与对应的网页uri

