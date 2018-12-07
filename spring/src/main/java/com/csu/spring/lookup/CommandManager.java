package com.csu.spring.lookup;

import org.springframework.stereotype.Component;

@Component("commandManager")
public abstract class CommandManager {

    public Object process(Object commandState) {

        Command command = createCommand();

        command.setState(commandState);

        return command.execute();
    }

//    @Lookup("asyncCommand")
    protected abstract Command createCommand();
}
