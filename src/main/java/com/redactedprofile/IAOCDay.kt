package com.redactedprofile;

import java.io.File
import java.util.function.Consumer

interface IAOCDay {

    var useSample:Boolean;
    var puzzleInputFilePath:String;
    var sampleInputFilePath:String;

    fun getInput():File
    fun getInputLinesByLine(reader:Consumer<String>)
    fun easy()
    fun hard()
}
