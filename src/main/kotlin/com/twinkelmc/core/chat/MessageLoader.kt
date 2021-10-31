package com.twinkelmc.core.chat

class MessageLoader {

    fun loadNL(): HashMap<String, String> {
        return hashMapOf(
            Pair("no_permission","U bent niet bevoegd dit commando uit te voeren"),
            Pair("please_use","Gebruik: {string}"),
            Pair("player_not_found","De speler {string} is niet gevonden"),
            Pair("fly_change_self","Uw fly is {string}"),
            Pair("fly_change_other","De fly van {player} is {string}"),
            Pair("gamemode_change_self","Uw gamemode is aangepast naar {string}"),
            Pair("gamemode_change_other","De gamemode van {player} is aangepast naar {string}"),
            Pair("gamemode_not_found","De gamemode {string} is niet gevonden"),
            Pair("teleport_self","U bent geteleporteerd naar {player}"),
            Pair("teleport_self_location","U bent geteleporteerd naar X:{double}, Y:{double}, Z:{double}"),
            Pair("teleport_other","U heeft {player} geteleporteerd naar {player}"),
            Pair("teleport_other_location","U heeft {player} geteleporteerd naar X:{double}, Y:{double}, Z:{double}"),
            Pair("teleport_back","U bent terug geteleporteerd naar uw vorige locatie"),
            Pair("teleport_here_other","U heeft {player} naar u toe geteleporteerd"),
            Pair("teleport_here_self","U bent naar {player} geteleporteerd"),
            Pair("teleport_to_self","U kunt niet naar u zelf teleporteren"),
            Pair("teleport_no_back","U heeft geen vorige locatie"),
            Pair("time_change_self","Uw tijd is aangepast naar {string}"),
            Pair("time_change_other","De tijd van {player} is aangepast naar {string}"),
            Pair("weather_change_self","Uw weer is aangepast naar {string}"),
            Pair("weather_change_other","Het weer van {player} is aangepast naar {string}"),
            Pair("fly_speed_self","Uw vlieg snelheid is aangepast naar {double}"),
            Pair("fly_speed_other","De vlieg snelheid van {player} is aangepast naar {double}"),
            Pair("walk_speed_self","Uw loop snelheid is aangepast naar {double}"),
            Pair("walk_speed_other","De loop snelheid van {player} is aangepast naar {double}"),
            Pair("speed_wrong","De snelheid {double} is niet toegestaan [0-10]"),
            Pair("give_self","U heeft {item} ontvangen"),
            Pair("give_other","U heeft {item} aan {player} gegeven"),
            Pair("list_title","Er zijn {int} spelers online"),
            Pair("rules_title","Onze regels"),
            Pair("heal_self","U bent gehealed"),
            Pair("heal_other","U heeft {player} gehealed"),
            Pair("teleport_up","U bent èèn verdieping omhoog geteleporteerd"),
            Pair("teleport_top","U bent naar het hoogste blok geteleporteerd"),
            Pair("no_floor_above","Er zijn geen verdiepingen meer boven u")
        )
    }

}