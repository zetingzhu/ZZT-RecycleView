

ZZTUtilCode

https://github.com/zetingzhu/ZZTUtilCode.git

1. 创建目录
$ mkdir zt-util
$ cd zt-util

2. 以submodule的方式注入到项目中
$ git submodule add https://github.com/zetingzhu/ZZTUtilCode.git



3. 克隆跟新
a) git submodule update --init --recursive
b) git clone --recurse-submodules
   

4. 查看当前submodule 版本提交信息
$ git submodule status

5. 更新最新版本
$ git submodule update zt-util/ZZTUtilCode 


6. 卸载删除 submodule
a) 卸载 submodule
$ git submodule deinit -f zt-util
b) 删除目录
$ git rm -r zt-util

