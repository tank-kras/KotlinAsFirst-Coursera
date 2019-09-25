@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.IllegalArgumentException
import kotlin.math.max

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
fun main(args: Array<String>) {
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

    val parts = str.split(" ");

    val monthes: Map<String, Pair<Int, Int>> = mapOf("января" to Pair(1, 31), "февраля" to Pair(2, 28), "марта" to Pair(3, 31), "апреля" to Pair(4, 30), "мая" to Pair(5, 31),
            "июня" to Pair(6, 30), "июля" to Pair(7, 31), "августа" to Pair(8, 31), "сентября" to Pair(9, 30), "октября" to Pair(10, 31), "ноября" to Pair(11, 30), "декабря" to Pair(12, 31))

    if (parts.size != 3) return ""

    val day: Int = parts.get(0).toInt()
    val mounthstr: String = parts.get(1);
    val year: Int = parts.get(2).toInt()

    val dainmonth = monthes[mounthstr]

    if (dainmonth != null) {
        if (day < dainmonth.second) {
            return String.format("%02d.%02d.%04d", day, dainmonth.first, year)
        }
    }
    return ""
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
fun dateDigitToStr(digital: String): String = TODO()

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
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {

    val symboles = phone.toSet()
    val specsymbol = "+-() "
    val dig = '0'..'9'
    for (s in symboles) {
        if ((s in specsymbol || s in dig) == false) return ""
    }

    return phone.replace(" ", "").replace("-", "").replace("(", "").replace(")", "")
}

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
fun bestLongJump(jumps: String): Int = TODO()

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {

    val parts = jumps.replace("+ ", "+,").replace("- ", "-,").replace("% ", "%,").split(",")
    val map_jumps: MutableMap<Int, String> = mutableMapOf()

    for (part in parts) {

        val jump = part.split(" ");
        if (jump.size != 2) continue

        map_jumps.put(jump.get(0).toInt(), jump.get(1))

    }

    var max_jump: Int? = 0
    if (map_jumps.size == 0) return -1
    else {
        max_jump = map_jumps.filter { it.value.contains("+") }.keys.maxBy { it }
    }

    if (max_jump != null) {
        return max_jump
    }
    return -1
}

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

    val parts = expression.replace(" +", ",+&").replace(" -", ",-&").replace(" ", "").split(",")

    var result = 0;

    for (part in parts) {

        if (parts.indexOf(part) == 0) {

            if (part.contains("-") || part.contains("+")) {
                throw IllegalArgumentException("")
            }

            result = part.toInt();
        } else {
            val member: List<String> = part.split("&")

            if (member.size == 2) {
                val expr = member.get(0)
                val digital_str = member.get(1)
                try {

                    digital_str.toInt()
                } catch (e: IllegalArgumentException) {

                    return throw IllegalArgumentException("")
                }

                val digital = digital_str.toInt()

                when (expr) {
                    "+" -> result += digital
                    "-" -> result -= digital
                }
            }
        }
    }

    return result
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

    val str_lowercase = str.toLowerCase()
    val parts = str_lowercase.split(" ")
    var count = parts.size
    var result = ""

    while (count > 0) {

        val cur_word = parts.get(parts.size - count) + if (count == 1) "" else " "
        val cur_word2 = cur_word + cur_word

        if ((result + cur_word2) in str_lowercase) return str_lowercase.indexOf(cur_word2)
        result += cur_word
        count -= 1
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
fun mostExpensive(description: String): String {

    val parts = description.split(";")
    val mapOfGoods: MutableMap<String, Double> = mutableMapOf()

    for (part in parts) {
        val goods = part.trim().split(" ")
        if (goods.size != 2) {
            return ""
        }
        mapOfGoods.put(goods.get(0), goods.get(1).toDouble())
    }
    return mapOfGoods.filter { it -> it.value == mapOfGoods.values.maxBy { it } }.keys.first()
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
fun fromRoman(roman: String): Int {

    var result = 0
    var new_roman = roman
    val mapOfRoman: Map<String, Int> = mapOf("I" to 1, "IV" to 4, "V" to 5, "IX" to 9, "X" to 10, "XL" to 40, "L" to 50,
            "XC" to 90, "C" to 100, "CD" to 400, "D" to 500, "CM" to 900, "M" to 1000)
    val listOfRoman = mapOfRoman.keys.toList()

    while (new_roman != "") {
        if (new_roman == "") return break

        var was_replace = false
        for (a in 0..listOfRoman.size - 1) {
            val romanDig = listOfRoman.get(listOfRoman.size - 1 - a)
            if (new_roman.startsWith(romanDig)) {
                result += mapOfRoman.get(romanDig)!!
                new_roman = new_roman.replaceFirst(romanDig, "")
                was_replace = true
                break
            }
        }
        if (!was_replace) { return -1}
    }

    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собо
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
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
