package data

data class Test(
    val title:String,
    val description:String,
    val questions: Array<Question>
){
    var questionQuantity:Int = this.questions.size
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as Test

        if (title != other.title) return false
        if (!questions.contentEquals(other.questions)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + questions.contentHashCode()
        return result
    }
    override fun toString(): String  = this.title
}
val testList : Array<Test> = arrayOf(
    Test("Тест по чрезвычайным ситуациям","\n" +
            "Человек на протяжении всей своей истории пытается существовать в мире и согласии с природой, но иногда она преподносит нам крайне разрушительные подарки. Пройдите тест на тему природных чрезвычайных ситуаций и узнайте, смогут ли ваши знания спасти вашу жизнь.", arrayOf(
        Question("Чем всегда сопровождается взрыв, нанося человеку наибольшее количество урона?",
                1,arrayOf(
                "Огромным количеством пламени",
                "Резким повышением давления",
                "Сильной взрывной волной",
                "Осклоками стекла"),2),
        Question("Как называется ветер скоростью до 13 м/с?",
                1,arrayOf(
                "Шторм",
                "Бриз",
                "Вьюга",
                "торнадо"),1),
        Question("Как называется неконтролируемое горение растительности, которое самостоятельно распространяется по лесной местности?",
                1,arrayOf(
                "Природный пожар",
                "Оползень",
                "Лесной пожар",
                "Подрыв"),2),
        Question("Чем опасно проникающее воздействие радиации?",
                1,arrayOf(
                "Возникновением лучевой болезни",
                "Мутациями в период от 5-и суток после облучения",
                "Поражением слизистых оболочек"),0),
        Question("Чем отличаются такие явления как буря и ураган?" ,
                1,arrayOf(
                "Скорость ветра бури больше, чем скорость ветра урагана",
                "В бурях поток воздуха всегда теплый, что приводит к меньшим разрушениям",
                "В ураганах преобладают осадки смешанные с воздухом, нанося больший урон"),0),
        Question("Какой способ является основным при спасении людей во время извержения вулкана?" ,
                1,arrayOf(
                "Укрытие в специальных огнеупорных строениях",
                "Использование индивидуальной и урбанистической защиты",
                "Эвакуация"),2))),
    Test("Котлин","Хорошо разбираетесь в Kotlin? Проверьте свои знания в нашем тесте.", arrayOf(
        Question("Что такое to в приведенном ниже примере:\n" +
                "\n" +
                "            \n" +
                "val test = 33 to 42",1,arrayOf(
            "Инфиксная функция, создающая пару (33, 42)",
            "Ключевое слово Kotlin для создания пары (33, 42)",
            "Ключевое слово для создания диапазона от 33 до 42",
            "Опечатка"),0),
        Question("Какое выражение Kotlin эквивалентно данному из Java?\"int x = a ? b : c\"",1,arrayOf(
                "val x = a ?: b, c",
                "val x = if (a) b : c",
                "val x = a ? b : c",
                "val x = if (a) b else c"),3),
        Question("Что применимо для следующего объявления класса?\n" +
                "\n" +
                "            \n" +
                "class Person (val name: String)",1,arrayOf(
                "Он package-private",
                "Он может быть расширен другими классами",
                "Он public",
                "У него приватное свойство \"name\""),2),
        Question("Что из этого в настоящее время не поддерживается в Kotlin ?",
            1,arrayOf(
                "JVM",
                "JavaScript",
                "LLVM",
                ".NET CLR"),3),
        Question("Есть ли у Kotlin примитивные типы данных, такие как int, long, float?",1,arrayOf(
                "Нет, Kotlin не имеет и не использует примитивные типы данных.",
                "Нет, не на уровне языка. Но компилятор Kotlin использует примитивы JVM для лучшей производительности.",
                "Да, но Kotlin всегда конвертирует их в не примитивные аналоги.",
                "Да, Kotlin в этом отношении похож на Java."),1)

    )),
    Test("Заготовка для теста №1","тест для большего количества тестов", arrayOf(
        Question("воп31",5,arrayOf("1","2","3","4"),0),
        Question("воп32",4,arrayOf("1","2","3","4"),0),
        Question("воп33",3,arrayOf("1","2","3","4"),0),
        Question("воп34",2,arrayOf("1","2","3","4"),0),
        Question("воп35",1,arrayOf("1","2","3","4","5","6"),0)
    )),
    Test("Заготовка для теста №2","такой же как и 1", arrayOf(
        Question("воп36",5,arrayOf("1","2","3","4"),0),
        Question("воп37",4,arrayOf("1","2","3","4"),0),
        Question("воп38",3,arrayOf("1","2","3","4"),0),
        Question("воп39",2,arrayOf("1","2","3","4"),0),
        Question("воп40",1,arrayOf("1","2","3","4","5","6"),0)
    ))
)
