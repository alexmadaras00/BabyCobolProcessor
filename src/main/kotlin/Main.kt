import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    // ACCEPT X
    // var X = readLine(
        val scanner = Scanner(System.`in`)
        print("Enter age: ")
        val age = scanner.nextInt()
        print("Enter gender (M or F): ")
        val gender = scanner.next()

        when {
            age > 60 && gender == "M" -> println("THE MAN IS RETIRED")
            age > 60 && gender == "F" -> println("THE WOMAN IS RETIRED")
            age <= 60 && gender == "M" -> println("THE MAN IS NOT RETIRED")
            age <= 60 && gender == "F" -> println("THE WOMAN IS NOT RETIRED")
            else -> {
                println("INVALID INPUT")
                println("AGE = $age and GENDER = $gender")
            }
        }
    /**
    IDENTIFICATION DIVISION.
    PROGRAM-ID. ACCPTST.
    ENVIRONMENT DIVISION.
    DATA DIVISION.
    WORKING-STORAGE SECTION.
    01 AGE                       PIC 9(3).
    01 GENDER                    PIC X(1).
    PROCEDURE DIVISION.
    ACCEPT AGE.
    ACCEPT GENDER.
    EVALUATE TRUE ALSO TRUE
    WHEN AGE > 60 ALSO GENDER = 'M'
    DISPLAY 'THE MAN IS RETIRED   '
    WHEN AGE > 60 ALSO GENDER = 'F'
    DISPLAY 'THE WOMAN IS RETIRED  '
    WHEN AGE <= 60 ALSO GENDER = 'M'
    DISPLAY 'THE MAN IS NOT RETIRED   '
    WHEN AGE <= 60 ALSO GENDER = 'F'
    DISPLAY 'THE WOMAN IS NOT RETIRED  '
    WHEN OTHER
    DISPLAY 'INVALID INPUT      '
    DISPLAY 'AGE =' AGE ' and GENDER =' GENDER
    END-EVALUATE.
    STOP RUN.
     */

}