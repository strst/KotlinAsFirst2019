@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun digitNumber(n: Int): Int  //TODO()
{
    var x=n
    var z=0
    for(i in 1..50)
    {
        x = x / 10
        z++
        if(x==0)break
    }
    return z
}
/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int  //TODO()
{
    var x=emptyArray<Int>()
    for(i in 0..n)
        x += 1
    for(i in 0..n-2)
        x[i+2]=x[i]+x[i+1]
    return x[n-1]
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int // TODO()
{
    var x=m
    var y=n
    var z=0
    for(i in 1..15)
    {
        if(y==0) return m*n/x
        z=x%y
        x=y
        y=z
    }
    return 1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int //= TODO()
{
    for (i in 2..n)
        if (n % i == 0) return i
    return 1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int //= TODO()
{
    var x=0
    for(i in 1..n)
        if(n%i==0&&i!=n) x= i
    return x
}
/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean //= TODO()
{
    if(m==1||n==1) return false
    var x=m
    var y=n
    var z=0
    for(i in 1..15)
    {
        if(y==0) if((m*n/x)==1) return true
        z=x%y
        x=y
        y=z
    }
    return false
}
/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean //= TODO()
{
    if(m==0&&n==0) return false
    for(i in 1..n)
        if(i*i in m..n&&n!=Int.MAX_VALUE) return true
    return false
}
/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int //= TODO()
{
    var z = 0
    var c=x
    for (i in 1..150)
        if (c == 1) return z
        else if (c % 2 == 0)
        {
            c= c / 2
            z++
        }
        else
        {
            c = 3 * c + 1
            z++
        }
    return 1
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double //= TODO()
{
    var z=x%(2* PI)
    var n = z;
    var sum = 0.0;
    var i = 1;
    do
    {
        sum += n;
        n *= -1.0 * z * z / ((2 * i) * (2 * i + 1));
        i++;
    }
    while (abs(n) > eps);
    return sum;
}
/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double
{
    var z=x%(2* PI)
    var n = 1.0;
    var sum = 0.0;
    var i = 1;
    do
    {
        sum += n;
        n *= -1.0 * z * z / ((2 * i - 1) * (2 * i));
        i++;
    }
    while (abs(n) > eps);
    return sum;
}
/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int //= TODO()
{
    var x=n
    var c=0
    var z=0
    for(i in 10 downTo 0)
    {
        if((n/(10.0.pow(i).toInt()))!=0)
        {
            x=(n/(10.0.pow(i).toInt()))%10

            z+=(x*(10.0.pow(c).toInt()))
            c++
        }
    }
    return z
}
/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean //= TODO()
{
    var x=n
    var c=0
    var z=0
    for(i in 10 downTo 0)
    {
        if((n/(10.0.pow(i).toInt()))!=0)
        {
            x=(n/(10.0.pow(i).toInt()))%10

            z+=(x*(10.0.pow(c).toInt()))
            c++
        }
    }
    if(z==n) return true
    return false
}
/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean //= TODO()
{
    if(n==0) return false
    var x=n%10
    var c=0
    for(i in 1..10)
    {
        if((n/(10.0.pow(i).toInt()))==0)
        {
            c=i-1
            break
        }
    }
    for(i in 1..c)
    {
        if((n/(10.0.pow(i).toInt())%10)!=x) return true
    }
    return false
}
/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int //= TODO()
{
    var c=0
    var g=0
    for(i in 1..n)
    {
        c=i*i
        for(i in 1..100)
        {
            if((c/(10.0.pow(i).toInt()))==0)
            {
                g+=i
                if(g>=n)
                {
                    g-=n
                    return ((c/(10.0.pow(g).toInt()))%10)
                }
                break
            }
        }

    }
    return 888
}
/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int //= TODO()
{
    var x=1
    var z=0
    var c=x
    var g =1
    for(i in 1..n-1)
    {
        c=x
        x+=z
        z=c
        if(z==0)z++
        if(x/100!=0)g=g+3
        else if(x/10!=0&&(x/100)==0)g=g+2
        else g++
        //if(g==n) break
        if(g==n&&x/100==0)
        {
            x%=10
            break
        }
        if(g==n+1&&x/100==0)
        {
            x/=10
            break
        }
        if(g==n+2&&x/100!=0)
        {
            x=(x/100)%10
            break
        }
        if(g==n+1&&x/100!=0)
        {
            x=(x/10)%10
            break
        }
        if(g==n&&x/100!=0)
        {
            x=x%10
            break
        }
    }
    return x
}