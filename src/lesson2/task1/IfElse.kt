@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String // TODO()
{
    if((age%10>4&&age%10<10)||age%10==0||(age<21&&age>4)||(age<121&&age>104))
        return "${age} лет"
    else if(age%10==1)
        return "${age} год"
    else(age%10<5)
    return "${age} года"
}
/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double =// TODO()
    if((t1*v1+t2*v2+t3*v3)/2<=t1*v1)
        (t1*v1+t2*v2+t3*v3)/2/v1
    else if((t1*v1+t2*v2+t3*v3)/2<=t2*v2+t1*v1)
        ((t1*v1+t2*v2+t3*v3)/2-t1*v1)/v2+t1
    else
        ((t1*v1+t2*v2+t3*v3)/2-t1*v1-t2*v2)/v3+t1+t2
/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int // TODO()
{
    if(kingX==rookX1&&kingX==rookX2) return 3
    else if(kingY==rookY1&&kingY==rookY2) return 3
    else if(kingX==rookX1||kingY==rookY1)
        if(kingX==rookX2||kingY==rookY2) return 3
    if((kingX==rookX1||kingY==rookY1)&&(kingX==rookX2||kingY==rookY2))
    else if(kingX==rookX1||kingY==rookY1) return 1
    else if(kingX==rookX2||kingY==rookY2) return 2
    return 0
}
/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int //= TODO()
{
    for(i in -8..8)
        if((kingX==bishopX+i&&kingY==bishopY+i)||(kingX==bishopX-i&&kingY==bishopY-i)||(kingX==bishopX+i&&kingY==bishopY-i)||
            (kingX==bishopX-i&&kingY==bishopY+i))
            if(kingX==rookX||kingY==rookY) return 3
    if(kingX==rookX||kingY==rookY) return 1
    for(i in -8..8)
        if ((kingX==bishopX+i&&kingY==bishopY+i)||(kingX==bishopX-i&&kingY==bishopY-i)||(kingX==bishopX+i&&kingY==bishopY-i)||
            (kingX==bishopX-i&&kingY==bishopY+i)) return 2
    return 0
}
/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int  //TODO()
{
    if(a > b + c||b > a + c||c > a + b) return -1
    if (a>=b&& a>=c)
        if (a < sqrt(b * b + c * c)) return 0
        else if (a == sqrt(b * b + c * c)) return 1
        else if (a > sqrt(b * b + c * c)) return 2
    if (b>=a&& b>=c)
        if (b < sqrt(a * a + c * c)) return 0
        else if (b == sqrt(a * a + c * c)) return 1
        else if (b > sqrt(a * a + c * c)) return 2
    if (c>=b&& c>=a)
        if (c < sqrt(a * a + b * b)) return 0
        else if (c == sqrt(a * a + b * b)) return 1
        else if (c > sqrt(a * a + b * b)) return 2
    return -1
}
/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int //= TODO()
{
    if (b < d&&b<c||d < a&&d<b) return -1
    if(a>c&&b<d) return b-a
    else if(a<c&&b>d) return d-c
    else if(c>a&&c<b) return b-c
    else if(a>c&&a<d) return d-a
    else if(b==c) return 0
    else if((a==b&&(a==c||a==d))||(c==d&&(c==a||c==b))) return 0
    return 8
}