package com.AimacanaDaniel.cazapatos.Interfaces

interface FileHandler {
    fun SaveInformation(datosAGrabar:Pair<String,String>)
    fun ReadInformation():Pair<String,String>
}