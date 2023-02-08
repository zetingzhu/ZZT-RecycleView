

ZZTUtilCode

https://github.com/zetingzhu/ZZTUtilCode.git

1. 创建目录
$ mkdir zt-util
$ cd zt-util

2. 以submodule的方式注入到项目中
$ git submodule add https://github.com/zetingzhu/ZZTUtilCode.git
$ git submodule add https://github.com/zetingzhu/ZZTSubmodul.git

添加项目关联到当前project
include(':ztTools')
project(':ztTools').projectDir = new File("zt-u1/ZZTUtilCode/zztToolsLibrary")


3. 克隆跟新
a) git submodule update --init --recursive
b) git clone --recurse-submodules
c) git pull origin master   
   

4. 查看当前submodule 版本提交信息
$ git submodule status

5. 更新最新版本
$ git submodule update zt-util/ZZTUtilCode 


6. 卸载删除 submodule
a) 卸载 submodule
$ git submodule deinit -f zt-util
b) 删除目录
$ git rm -r -f zt-util
c)
删除 .gitmodules 文件
d）
删除 .git/modules 中的submodule目录

