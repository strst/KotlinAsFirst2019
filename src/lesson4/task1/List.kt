@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.minDivisor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var x = 0.0
    for (i in v)
        x += sqr(i)
    return sqrt(x)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var x = 0.0
    for (i in list) {
        x += i
    }
    return if (list.isEmpty()) 0.0
    else x / list.size
}


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val x = mean(list)
    for (i in 0 until list.size)
        list[i] -= x
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var x = 0
    for (i in 0 until a.size)
        x += a[i] * b[i]
    return x
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var z = p[0]
    var v = 1
    for (i in 1 until p.size) {
        z += p[i] * x * v
        v *= x
    }
    return z
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size)
        list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val v = mutableListOf<Int>()
    var x = n
    while (x != 1) {
        val c = minDivisor(x)
        v += c
        x /= c
    }
    return v
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val x = factorize(n)
    var l = ""
    for (i in 0..x.size - 2) {
        l += "${x[i]}*"
    }
    l += x[x.size - 1]
    return l
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val v = mutableListOf<Int>()
    var x = n
    while (x >= base) {
        v.add(x % base)
        x /= base
    }
    v.add(x)
    for (i in 0 until v.size / 2) {
        x = v[i]
        v[i] = v[v.size - 1 - i]
        v[v.size - 1 - i] = x
    }
    return v
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val v = convert(n, base).toMutableList()
    var x = ""
    for (i in 0 until v.size)
        if (v[i] > 9)
            x += (v[i] + 87).toChar()
        else x += v[i]
    return x
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */

fun decimal(digits: List<Int>, base: Int): Int {
    if (digits.isEmpty()) return 0
    var x = digits[digits.size - 1]
    var l = 1
    if (digits.size > 1)
        for (i in digits.size - 2 downTo 0) {
            x += digits[i] * l * base
            l *= base
        }
    return x
}


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */

fun decimalFromString(str: String, base: Int): Int {
    var x = 0
    for (i in 0 until str.length) {
        val z = str[str.length - 1 - i].toInt()
        val c = base.toDouble().pow(i).toInt()
        x += if (z in 48..57) (z - 48) * c
        else (z - 87) * c
    }
    return x
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var x = ""
    var z = n
    var i = 0
    val number = listOf(
        1000, 900, 500, 400,
        100, 90, 50, 40,
        10, 9, 5, 4, 1
    )
    val word = listOf(
        "M", "CM", "D", "CD",
        "C", "XC", "L", "XL",
        "X", "IX", "V", "IV", "I"
    )
    while (z > 0)
        if (z >= number[i]) {
            z -= number[i]
            x += word[i]
        } else {
            i++
        }
    return x
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val z = mutableListOf(n / 1000, n % 1000)
    val x = mutableListOf<String>()
    val hundred = listOf(
        "сто", "двести", "триста", "четыреста", "пятьсот",
        "шестьсот", "семьсот", "восемьсот", "девятьсот"
    )
    val ten = listOf(
        "двадцать", "тридцать", "сорок", "пятьдесят",
        "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    )
    val ten1 = listOf(
        "десять", "одиннадцать", "двенадцать", "тринадцать",
        "четырнадцать", "пятнадцать", "шестнадцать",
        "семнадцать", "восемнадцать", "девятнадцать"
    )
    val one = listOf(
        "один", "два", "три", "четыре", "пять",
        "шесть", "семь", "восемь", "девять"
    )
    val one1 = listOf("одна", "две")
    for (i in 0..1) {
        if (z[i] / 100 != 0) x += hundred[z[i] / 100 - 1]
        if (z[i] / 10 % 10 in 2..9) x += ten[z[i] / 10 % 10 - 2]
        if (z[i] % 100 in 10..19) x += ten1[z[i] % 100 - 10]
        if (z[i] % 10 in 1..9 && z[i] % 100 !in 10..19) x += if (i == 0 && z[i] % 10 in 1..2) one1[z[i] % 10 - 1]
        else one[z[i] % 10 - 1]
        if (i == 0 && z[i] != 0)
            x += if (z[i] / 10 % 10 != 1)
                when (z[i] % 10) {
                    1 -> "тысяча"
                    in 2..4 -> "тысячи"
                    else -> "тысяч"
                }
            else "тысяч"
    }
    return x.joinToString(separator = " ")
}
