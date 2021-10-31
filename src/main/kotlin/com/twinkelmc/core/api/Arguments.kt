package com.twinkelmc.core.api

class Arguments(val command: Command): ArrayList<ArgumentType>() {

    fun onExecute(f: (ExecutedCommand) -> Unit): Arguments {
        return this
    }

    fun build(): Command {
        return command
    }

}