package com.example.myhealthapp

class Reading {
    var id : Int = 0
    var date : String = ""
    var pressure : String = ""
    var sugar : Int = 0
    var weight : Int = 0

    constructor(date:String, pressure:String,sugar:Int, weight:Int){
        this.date = date
        this.pressure = pressure
        this.sugar = sugar
        this.weight = weight
    }
    constructor(){
    }
}