

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
1)git submodule update --init --recursive
2)git pull
3)git submodule update

# 开发者B
cd MainProject
git pull # 主模块远程更新合并到本地。
git submodule update --remote --merge #子模块远程更新合并到本地
# 或者
cd $submodule_dir; git fetch; git merge origin/master # 拉取子模块更新
  

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

