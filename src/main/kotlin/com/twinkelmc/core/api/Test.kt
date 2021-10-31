package com.twinkelmc.core.api

class Test {

    fun test() {

        Commands.create("tp")

            .arguments(0) {
                it[0] = ArgumentType.PLAYER
            }.onExecute {

            }.build()

            .arguments(1) {
                it[0] = ArgumentType.DOUBLE
                it[1] = ArgumentType.DOUBLE
                it[2] = ArgumentType.DOUBLE
            }.onExecute {

            }.build()

            .arguments(2) {
                it[0] = ArgumentType.PLAYER
                it[1] = ArgumentType.DOUBLE
                it[2] = ArgumentType.DOUBLE
                it[3] = ArgumentType.DOUBLE
            }.onExecute {

            }.build()

            .arguments(3) {
                it[0] = ArgumentType.PLAYER
                it[1] = ArgumentType.PLAYER
            }.onExecute {

            }.build()


    }

}