import java.io.File

/************************************************************
 * Name: DJ Harrison
 * Date: 4/10/2026
 * Assignment: Text Search Regular Expressions Assignment
 * Class Number: 410
 * Description: search through a data file using regular expressions
 ************************************************************/


fun main() {

    val fileName = "res/Enrollment.txt"
    val lines = File(fileName).readLines()
    var loop = true
    while(loop) {
        print("Department Name: ")
        val department = readln().uppercase()
        if (department == "EXIT"){
            loop = false
            continue
        }
        print("Class Number: ")
        val classNumber = readln()
        println()
//        Regex pattern to look for classes with [user input] department and class number
        val primaryMatchPattern = """(?<=\d{3}\s)((${department}&?[^\S\r\n]*?${classNumber})(?=[^\S\r\n]+))""".toRegex()
//        Regex to match all classes (with lookahead and lookbehind to avoid false-positives). Also look for blank lines
//        or for other junk lines:
        val secondaryMatchPattern = """(?<=\d{3}\s)(([A-Z]{2,5}&?[^\S\r\n]*?\d{3})(?=[^\S\r\n]+))|^[\s-_]*$""".toRegex()
//        print column headers from input file
        println(lines[4])
        println(lines[5])
//        Loop through file, beginning with first real data line
        for(i in 7..<lines.size){
//            Check each line for primary regex match & print it if found
            val matchResult = primaryMatchPattern.find(lines[i])
            if (matchResult != null){
                println(lines[i])
//                Check lines following primary match for additional associated lines. Stop at next class or junk line
                for (extraLine in i+1..<lines.size) {
                    val secondaryMatch = secondaryMatchPattern.find(lines[extraLine])
                    if (secondaryMatch == null ){
                        println(lines[extraLine])
                    } else { break }
                }
            }
        }
        print("\n--- Press Enter to continue ---")
        readln()
        println("\n\n")

    }


}