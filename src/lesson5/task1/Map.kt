@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

//import jdk.internal.org.jline.utils.Colors.s
import ru.spbstu.kotlin.typeclass.kind
import ru.spbstu.kotlin.typeclass.value

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): MutableMap<Int, List<String>> {
    if (grades == mapOf<String, Int>()) return mutableMapOf()
    val v = mutableMapOf<Int, List<String>>()
    var x1 = mutableListOf<Int>()
    var x2 = mutableListOf<MutableList<String>>()
    for ((j, k) in grades) {
        x1.add(k)
        x2.add(mutableListOf(j))
    }
    x1 = x1.reversed().toMutableList()
    x2 = x2.reversed().toMutableList()
    var i = 0
    var k = 0
    while (i != -1) {
        while (k != -1) {
            k++
            if (k == x1.size) break
            if (x1[i] == x1[k]) {
                x1.removeAt(k)
                x2[i].plusAssign(x2[k])
                x2.removeAt(k)
                k--
            }
        }
        i++
        if (i == x1.size) break
        k = i
    }
    for (z in 0 until x1.size)
        v[x1[z]] = x2[z]
    return v
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    var k = 0
    for (i in 0 until a.size)
        if (b.keys.contains(a.keys.elementAt(i)) && b.values.contains(a.values.elementAt(i))) k++
    return k == a.size
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): MutableMap<String, String> {
    val x = mutableListOf<String>()
    for (k in b)
        for (j in a)
            if (k == j)
                x += k.key
    for (i in x)
        a.remove(i)
    return a
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val set = mutableSetOf<String>()
    for (k in a)
        for (j in b)
            if (k == j) set.add(k)
    return set.toList()
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val c = mutableMapOf<String, String>()
    c += mapA
    c += mapB
    for ((key, value) in mapA)
        for ((key1, value1) in mapB)
            if (key1 == key && value != value1)
                c[key1] = "$value, $value1"
    return c
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): MutableMap<String, Double> {
    val v = mutableMapOf<String, Double>()
    v += stockPrices
    val s = mutableListOf<Pair<String, Double>>()
    s += stockPrices
    var i = 0
    while (i != s.size) {
        if (v.containsKey(s[i].first) && v.containsValue(s[i].second)) {
            s.removeAt(i)
            i--
        }
        i++
    }
    for ((first, second) in s)
        v[first] = v.getValue(first) + second
    val e = mutableMapOf<String, Double>()
    e += s
    for ((key) in e)
        if (v.contains(key)) v[key] = v.getValue(key) / 2
    return v
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    val l = mutableMapOf<String, Double>()
    for ((key, value) in stuff)
        if (value.first == kind) l[key] = value.second
    if (l.isEmpty()) return null
    var s = l.keys.elementAt(0)
    for (i in 1 until l.size)
        if (l.values.elementAt(i) < l.values.elementAt(i - 1)) s = l.keys.elementAt(i)
    return s
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    var c = 0
    for (a in chars)
        if (word.contains(a))
            c++
    return c == chars.size && c != 0 || (chars.isEmpty() && word.isEmpty())
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): MutableMap<String, Int> {
    val g = list.toMutableList()
    val l = mutableMapOf<String, Int>()
    var v = 1
    var a = 0
    var b = 0
    while (a < g.size) {
        while (b < g.size) {
            if (a != b && g.elementAt(a) == g.elementAt(b)) {
                g.removeAt(b)
                v++
                b--
            }
            b++
        }
        if (v > 1) {
            l[g.elementAt(a)] = v
            v = 1
        }
        b = 0
        a++
    }
    return l
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    var k = 0
    for (a in 0 until words.size)
        for (b in 0 until words.size)
            if (canBuildFrom(words[a].toList(), words[b]) && b != a) {
                k++
                break
            }
    return k != 0
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val g = mutableMapOf<String, MutableSet<String>>()
    for ((key, value) in friends)
        g[key] = value.toMutableSet()
    for (a in 0 until friends.keys.size)
        for (b in 0 until friends.keys.size) {
            if (a == b) continue
            if (g.values.elementAt(a).elementAt(0) == g.keys.elementAt(b))
                g.values.elementAt(a).add(g.values.elementAt(b).elementAt(0))
        }
    var k = 0
    for (a in 0 until friends.keys.size) {
        for (b in 0 until friends.keys.size) {
            if (a == b) continue
            if (g.values.elementAt(a).elementAt(0) != g.keys.elementAt(b))
                k++
            if (k == friends.keys.size - 1) {
                g[g.values.elementAt(a).elementAt(0)] = mutableSetOf()
                k = 0
            }
        }
        k = 0
    }
    if (g.values.size == 1)
        g[g.values.elementAt(0).elementAt(0)] = mutableSetOf()
    return g
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    var g = Pair(-1, -1)
    for (a in 0 until list.size)
        for (b in 0 until list.size) {
            if (a == b) continue
            if (list[a] + list[b] == number) g = Pair(b, a)
        }
    return if (g == Pair(-1, -1)) Pair(-1, -1)
    else g
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    if (treasures.isEmpty()) return emptySet()
    val t = treasures.toMutableMap()
    var c = capacity
    val q = mutableSetOf<String>()
    var g = t.values.elementAt(0).first
    for (a in 0 until t.values.size)
        if (g > t.values.elementAt(a).first) {
            g = t.values.elementAt(a).first
        }
    var b = 0
    while (c > g) {
        for (a in 0 until t.values.size)
            if (g > t.values.elementAt(a).first) {
                g = t.values.elementAt(a).first
                b = a
            }
        c -= g
        q.add(t.keys.elementAt(b))
        t.remove(t.keys.elementAt(b))
    }
    return q
}
