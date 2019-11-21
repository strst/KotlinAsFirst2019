@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

val month = listOf(
    "", "января", "февраля", "марта", "апреля",
    "мая", "июня", "июля", "августа", "сентября",
    "октября", "ноября", "декабря"
)

fun num(str: String): Boolean {
    for (i in str) if (!i.isDigit()) return true
    return false
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val s = str.split(" ").toMutableList()
    if (s.size != 3 || num(s[0]) || num(s[2])) return ""
    s[1] = month.indexOf(s[1]).toString()
    val d = daysInMonth(s[1].toInt(), s[2].toInt())
    if (d < s[0].toInt() || d == 1) return ""
    return String.format("%02d.%02d.%d", s[0].toInt(), s[1].toInt(), s[2].toInt())
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val s = digital.split(".").toMutableList()
    if (s.size != 3 || num(s[0]) || num(s[1]) || num(s[2])) return ""
    val d = daysInMonth(s[1].toInt(), s[2].toInt())
    if (d < s[0].toInt() || d == 1) return ""
    s[1] = month[s[1].toInt()]
    return String.format("%d %s %d", s[0].toInt(), s[1], s[2].toInt())
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах."+7 (921) 123-45-67"
 */
fun flattenPhoneNumber(phone: String): String =
    if (!phone.matches(Regex("""(\+\d+)? ?(\([\d\-\s]+\))? ?([\d\-\s])*\d+"""))) ""
    else phone.filter { it != ' ' && it != '-' && it != '(' && it != ')' }

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int =
    if (jumps.filter { it != ' ' }.contains(Regex("""[^\d-%]"""))) -1
    else if (!jumps.contains(Regex("""\d"""))) -1
    else jumps.split(" ").filter { it != "-" && it != "%" }.max()!!.toInt()

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int =
    if (jumps.filter { it != ' ' }.contains(Regex("""[^\d-%+]"""))) -1
    else jumps.split(" ")
        .filterIndexed { i, v -> !num(v) && jumps.split(" ").elementAt(i + 1).matches(Regex("""\+.*""")) }
        .max()!!.toInt()

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val result = expression.split(" ").toMutableList()
    if (!expression.matches(Regex("""(\d+ (\+?-?) )*\d+"""))) throw IllegalArgumentException()
    var a = result[0].toInt()
    for (i in 2 until result.size step (2))
        if (result[i - 1] == "+") a += result[i].toInt()
        else a -= result[i].toInt()
    return a
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val s = str.toUpperCase().split(" ")
    (0..s.size - 2).forEach {
        if (s[it] == s[it + 1]) {
            var z = 0
            (0..it).forEach { it1 -> z += s[it1].length;z++ }
            return z - s[it].length - 1
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): Any =
    if (!Regex("""([A-zA-я\d]+ \d+(\.\d+)?; )*([A-zA-я\d]+ \d+(\.\d+)?)""").matches(description)) ""
    else {
        val a = description.split(" ", ";").toMutableList()
        var name = ""
        var number = -1.0
        for (i in 0..a.size - 2 step 3)
            if (a[i + 1].toDouble() > number) {
                number = a[i + 1].toDouble()
                name = a[i]
            }
        name
    }

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int =
    if (roman.isEmpty() || !roman.matches(Regex("M*(CM|DC{0,3}|CD|C{0,3})?(XC|LX{0,3}|XL|X{0,3})?(IX|VI{0,3}|IV|I{0,3})?"))) -1
    else {
        var r1 = roman
        var res = 0
        val numbers = mapOf(
            "IV" to 4, "IX" to 9, "I" to 1, "V" to 5, "XL" to 40,
            "XC" to 90, "CD" to 400, "CM" to 900, "X" to 10,
            "L" to 50, "C" to 100, "D" to 500, "M" to 1000
        )
        for ((key, value) in numbers) {
            res += (Regex(key).findAll(r1)).count() * value
            r1 = r1.replace(key, "")
        }
        res
    }

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    require(!Regex("""[^\[\]+><-]+""").containsMatchIn(commands.filter { it != ' ' }))
    require(commands.filter { it == '[' }.length == commands.filter { it == ']' }.length)
    val l = mutableListOf<Int>()
    for (i in 0 until cells) {
        l.add(0)
    }
    var i = cells / 2
    var k = 0
    var lim = 0
    val bracket = mutableListOf<Int>()
    while (k != commands.length && lim != limit) {
        check(i in 0 until cells)
        when (commands[k]) {
            '+' -> l[i] += 1
            '-' -> l[i] -= 1
            '>' -> i++
            '<' -> i--
            '[' -> if (l[i] == 0) {
                var c = 0
                loop@ for (it in k until commands.length) {
                    when {
                        commands[it] == '[' -> c++
                        commands[it] == ']' -> c--
                    }
                    if (c == 0) {
                        k = it
                        break@loop
                    }
                }
            } else bracket.add(k)
            ']' -> if (l[i] != 0) k = bracket.last()
            else bracket.remove(bracket.last())
        }
        k++
        lim++
    }
    return l
}