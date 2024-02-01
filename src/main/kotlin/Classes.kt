data class Person(var name: String, var phone: String = "", var email: String = "") {
    override fun toString(): String {
        return "name='$name', phone='$phone', email='$email'"
    }
}

sealed interface Command {
    fun isValid(): Boolean
}
    class Exit(val value: String) : Command {
        override fun isValid(): Boolean {
        return (value == "exit")
        }
    }

    class Help(val value: String) : Command{
        override fun isValid(): Boolean {
            return (value == "help")
        }
        companion object {
            fun showHelp() {
            println("exit\n help\n show\n addPhone <Имя> <Номер телефона>\naddEmail <Имя>  <Адрес электронной почты>")
            }
        }
    }

    class AddPhone(val value: List<String>) : Command{
        var name = value[1]
        var phone = value[2]
        override fun isValid(): Boolean {
            return name.matches(Regex("""[A-Z][a-z]+""")) && phone.matches(Regex("\\+?[0-9]+"))
        }

        fun savePersonPhone() {
            Show.lastPerson = Person(name = name, phone = phone)
            println("Save ${name}, ${phone}")
        }
    }

    class AddEmail(val value: List<String>) : Command {
        var name = value[1]
        var email = value[2]
        override fun isValid(): Boolean {
            return (name.matches(Regex("""[A-Z][a-z]+"""))
                    && email.matches(Regex("\\w+\\@\\w+\\.\\w+")))
        }
        fun savePersonEmail(){
            Show.lastPerson = Person(name = name, phone = email)
            println("Save ${name}, ${email}")
        }
    }

    class Show(val value: String) : Command {
        override fun isValid(): Boolean {
            return (value == "show")
        }
        companion object{
            var lastPerson: Person? = null
        }
        fun showPerson(){
        println(lastPerson?: "Not initialized")
        }
    }


