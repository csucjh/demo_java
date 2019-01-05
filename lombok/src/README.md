lombok的特性分为两种：
    
    Stable：稳定特性
    Experimental：试点特性(不稳定)
    
在日常使用中，只使用Stable的特性：

    @NonNull：验空
    @Getter/@Setter：快速生成getter/setter
    @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
    @Builder：提供便于实例化类的Builder
    @Log：日志
    @Cleanup：可以被jdk7中的try-with-resources取代，不建议再使用
    var：尽量不要使用这种弱类型(类似有个experimental的var)
    @Synchronized：生成同步代码，没有必要用
    @Generated：自动生成的代码会被自动添加此标记注解，已弃用，绝对不要用