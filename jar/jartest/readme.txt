
linux下cp使用冒号分隔:
java -Dfile.encoding=UTF-8 -cp ./lib/*:./conf  com.wlqq.maxwell.Main

windows下cp使用分号分隔
java -Dfile.encoding=UTF-8 -cp ./lib/*;./conf  com.wlqq.maxwell.Main


使用java.ext.dirs参数增加到扩展类加载器路径，注意要加上jre的ext
java -Djava.ext.dirs=lib;%JAVA_HOME%\jre\lib\ext -jar test.jar


linux下后台运行：nohup command >myout.log 2>&1 &
示例：nohup java -cp ./lib/*:./conf  com.wlqq.maxwell.Main >myout.log 2>&1 &