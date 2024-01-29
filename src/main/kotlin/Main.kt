fun main() {
/*
     Написать программу, которая обрабатывает введённые пользователем в консоль команды:
    exit
    help
    add <Имя> phone <Номер телефона>
    add <Имя> email <Адрес электронной почты>
    После выполнения команды, кроме команды exit, программа ждёт следующую команду.
    Имя – любое слово.
    Если введена команда с номером телефона, нужно проверить, что указанный телефон может начинаться с +,
    затем идут только цифры. При соответствии введённого номера этому условию – выводим его на экран
    вместе с именем, используя строковый шаблон. В противном случае - выводим сообщение об ошибке.
    Для команды с электронной почтой делаем то же самое, но с другим шаблоном – для простоты,
    адрес должен содержать три последовательности букв, разделённых символами @ и точкой.
    Пример команд:
    add Tom email tom@example.com
    add Tom phone +7876743558
 */
    var flag: Boolean = true
//println(checkEmail("tom@example.com"))
//   println(checkPhone("000003"))
//    println(checkName("Tom"))
    do{
        var responce = readlnOrNull()
        val parts = responce?.split(" ")
        if(parts != null) {
            when {
                parts[0] == "help" -> println("exit\n help\n add <Имя> phone <Номер телефона>\nadd <Имя> email <Адрес электронной почты>")
                parts[0] == "add" && checkName(parts[1]) && parts[2] == "phone" && checkPhone(parts[3]) -> println("${parts[1]} ${parts[3]}")
                parts[0] == "add" && checkName(parts[1]) && parts[2] == "email" && checkEmail(parts[3]) -> println("${parts[1]} ${parts[3]}")


                parts[0] == "exit" -> flag = false
                else -> println("input error")
            }
        }

    }while (flag)
    println("exit")


}
fun checkPhone(a: String) : Boolean {
    var result = false
    val pattern1 = """[0-9]+"""
    val pattern2 = "/+?[0-9]+"  //?????
    if(a.matches(Regex(pattern1)) ){
        result = true
    }
    if( a.matches(Regex(pattern2))){
        result = true
    }
    return result
}
fun checkName(a : String) : Boolean{
    return a.matches(Regex("""[A-Z][a-z]+"""))
}

fun checkEmail(a: String): Boolean{
    val pattern = "\\w+\\@\\w+\\.\\w+"
    return a.matches(Regex(pattern))
}


