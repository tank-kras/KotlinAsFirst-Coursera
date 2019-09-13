

fun main(args: Array<String>){

    println(timeStrToSeconds("11:45:38"))
    println(11*3600 + 45 * 60 + 38)
}

fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}