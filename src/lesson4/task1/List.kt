@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
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
    for (i in 0 until v.size)
        x += sqr(v[i])
    return sqrt(x)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var x = 0.0
    for (i in 0 until list.size) {
        x += list[i]
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
fun center(list: MutableList<Double>): MutableList<Double> =
    if (list.size != 0) {
        val x = mean(list)
        for (i in 0 until list.size)
            list[i] -= x
        list
    } else list

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
    var z = 0
    for (i in 0 until p.size)
        z += p[i] * (x.toDouble().pow(i).toInt())
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
    for (i in 2..n) {
        if (x == 1) break
        while (x % i == 0) {
            v.add(i)
            x /= i
        }
    }
    if (v.size == 0) v.add(n)
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
    for (i in 0 until x.size) {
        l += x[i]
        l += "*"
    }
    return l.substring(0, l.length - 1)
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
    val list = listOf(
        "a", "b", "c", "d", "e", "f", "g",
        "h", "i", "j", "k", "l", "m", "n",
        "o", "p", "q", "r", "s", "t", "u",
        "v", "w", "x", "y", "z"
    )
    for (i in 0 until v.size)
        if (v[i] in 10..35) x += list[v[i] - 10]
        else x += v[i]
    /*if (v[i] > 9)
            x += (v[i] + 87).toChar()
        else x += v[i]*/
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
    var x = 0
    for (i in 0 until digits.size)
        x += digits[i] * base.toDouble().pow(digits.size - 1 - i).toInt()
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
fun perevod(v: Char): Int =
    when (v) {
        '0' -> 0
        '1' -> 1
        '2' -> 2
        '3' -> 3
        '4' -> 4
        '5' -> 5
        '6' -> 6
        '7' -> 7
        '8' -> 8
        '9' -> 9
        'a' -> 10
        'b' -> 11
        'c' -> 12
        'd' -> 13
        'e' -> 14
        'f' -> 15
        'g' -> 16
        'h' -> 17
        'i' -> 18
        'j' -> 19
        'k' -> 20
        'l' -> 21
        'm' -> 22
        'n' -> 23
        'o' -> 24
        'p' -> 25
        'q' -> 26
        'r' -> 27
        's' -> 28
        't' -> 29
        'u' -> 30
        'v' -> 31
        'w' -> 32
        'x' -> 33
        'y' -> 34
        'z' -> 35
        else -> 0
    }

fun decimalFromString(str: String, base: Int): Int {
    var x = 0
    for (i in 0 until str.length)
        x += perevod(str[str.length - 1 - i]) * base.toDouble().pow(i).toInt()
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
    while (z > 0)
        when {
            z >= 1000 -> {
                x += "M"; z -= 1000
            }
            z >= 900 -> {
                x += "CM"; z -= 900
            }
            z >= 500 -> {
                x += "D"; z -= 500
            }
            z >= 400 -> {
                x += "CD"; z -= 400
            }
            z >= 100 -> {
                x += "C"; z -= 100
            }
            z >= 90 -> {
                x += "XC"; z -= 90
            }
            z >= 50 -> {
                x += "L"; z -= 50
            }
            z >= 40 -> {
                x += "XL"; z -= 40
            }
            z >= 10 -> {
                x += "X"; z -= 10
            }
            z >= 9 -> {
                x += "IX"; z -= 9
            }
            z >= 5 -> {
                x += "V"; z -= 5
            }
            z >= 4 -> {
                x += "IV"; z -= 4
            }
            z >= 1 -> {
                x += "I"; z -= 1
            }
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
    val sot = listOf(
        "сто",
        "двести",
        "триста",
        "четыреста",
        "пятьсот",
        "шестьсот",
        "семьсот",
        "восемьсот",
        "девятьсот"
    )
    val dec = listOf(
        "двадцать",
        "тридцать",
        "сорок",
        "пятьдесят",
        "шестьдесят",
        "семьдесят",
        "восемьдесят",
        "девяносто"
    )
    val edn = listOf(
        "один",
        "два",
        "три",
        "четыре",
        "пять",
        "шесть",
        "семь",
        "восемь",
        "девять",
        "десять",
        "одиннадцать",
        "двенадцать",
        "тринацать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать"
    )
    val tisa = listOf("одна", "две", "а", "и")
    val tis = "тысяч"
    val prob = " "
    var l = ""
    var i = 1
    var c = digitNumber(n)
    var k = 0
    var g = 0
    while (c != 0) {
        val x = n / (10.0.pow(digitNumber(n) - i).toInt()) % 10
        when (c) {
            6 -> {
                l += sot[x - 1];g++
            }
            5 -> if (x !in 0..1) {
                l += dec[x - 2];g++
            } else if (x == 1) k++
            4 -> if (k == 1) {
                l += edn[x + 9] + prob + tis;k--;g++
            } else if (x in 1..2 && x != 0) {
                l += tisa[x - 1] + prob + tis + tisa[x + 1];g++
            } else if (x in 3..4) {
                l += edn[x - 1] + prob + tis + tisa[3];g++
            } else if (x != 0) {
                l += edn[x - 1] + prob + tis;g++
            } else {
                l += tis;g++
            }
            3 -> if (x != 0) {
                l += sot[x - 1];g++
            }
            2 -> if (x !in 0..1) {
                l += dec[x - 2];g++
            } else if (x == 1) k++
            1 -> if (k == 1) l += edn[x + 9] else if (x != 0) l += edn[x - 1]
        }
        if (g == 1) {
            l += prob;g--
        }
        i++
        c--
    }
    k = l.toCharArray().size
    return if (l[k - 1] == ' ') l.substring(0..k - 2)
    else l
}