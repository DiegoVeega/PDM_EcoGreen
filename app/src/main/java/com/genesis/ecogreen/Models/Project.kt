package com.genesis.ecogreen.Models

class Project (
    var nombre : String = "",
    var descripcion : String = "",
    var objetivo : String = "",
    var creador : String = "",
    var fecha : String = "",
    var tareas : ArrayList<Task2>? = ArrayList(),
    var privado : String,
    var image:String?
)