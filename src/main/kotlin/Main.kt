import java.util.*

fun main(args: Array<String>) {

    val input: StringBuilder = StringBuilder("10.3 + -2")
    val queueNumbers: Queue<Double> = LinkedList<Double>()
    val queueActions: Queue<Char> = LinkedList<Char>()
    println('\"' + input.toString() + '\"')
    prepareIn(input, queueNumbers, queueActions)
    calculateIn(queueNumbers, queueActions)
}

fun prepareIn(input: StringBuilder, queueNumbers: Queue<Double>, queueActions: Queue<Char>) {

    var numDetected = false
    var startIndex = 0
    val trimmedIn = input.filter { !it.isWhitespace() }

    for (i in 0..trimmedIn.lastIndex) {
        when {
            trimmedIn[i].isDigit() && !numDetected -> {
                startIndex = i
                numDetected = true
            }
            !trimmedIn[i].isDigit() && numDetected && trimmedIn[i] != '.' -> {
                queueNumbers.add(trimmedIn.substring(startIndex, i).toDouble())
                queueActions.add(trimmedIn[i])
                numDetected = false
            }
            !trimmedIn[i].isDigit() && !numDetected && trimmedIn[i] == '-' -> {
                startIndex = i
                numDetected = true
            }
        }
    }
    if (numDetected) {
        queueNumbers.add(trimmedIn.substring(startIndex..trimmedIn.lastIndex).toDouble())
    }
    println(queueNumbers)
    println(queueActions)
}

fun calculateIn(queueNumbers: Queue<Double>, queueActions: Queue<Char>) {
    var action: Char
    var result = queueNumbers.poll()
    var operand: Double

    while (!queueNumbers.isEmpty()) {
        operand = queueNumbers.poll()
        action = queueActions.poll()
        when (action) {
            '-' -> result -= operand
            '+' -> result += operand
            '*' -> result *= operand
            '/' -> result /= operand
            '%' -> result = result % operand * -1.0
        }
    }
    var pointNum = 8.3

    println("pointNum = " + pointNum)
    println(if(pointNum.compareTo(pointNum.toInt()) == 0) pointNum.toInt() else pointNum)

    println("result = " + result)
    println(if(result.compareTo(result.toInt()) == 0) result.toInt() else result)
}
