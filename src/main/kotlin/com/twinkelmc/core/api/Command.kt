package com.twinkelmc.core.api

class Command {

    private val arguments = arrayListOf<Arguments>()

    fun arguments(index: Int, f: (Arguments) -> Unit): Arguments {
        val args = arguments.getOrNull(index) ?: Arguments(this)
        f.invoke(args)
        arguments[index] = args
        return args
    }

}