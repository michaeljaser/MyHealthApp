package com.example.myhealthapp

class User {
    var id : Int = 0
    var fname : String = ""
    var lname : String = ""
    var dob : String = ""
    var weight : Int = 0
    var height : Int = 0
    var email : String = ""

    constructor(fname:String, lname:String, dob:String, weight:Int, height:Int, email:String){
        this.fname = fname
        this.lname = lname
        this.dob = dob
        this.weight = weight
        this.height= height
        this.email = email
    }
    constructor(){
    }}