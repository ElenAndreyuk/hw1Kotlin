data class Person(val name: String, val phone: String? = null, val email: String? = null) {
    var phones: MutableList<String> = mutableListOf()
    var emails: MutableList<String> = mutableListOf()

    init {
        phone?.let { phones = mutableListOf(it) }
        email?.let { emails = mutableListOf(it) }
    }

    override fun toString(): String {
        return "name='$name', phones='$phones', emails='$emails'"
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
            println("""Commands:
            exit 
            help
            show
            showAll
            addPhone <Имя> <Номер телефона>
            addEmail <Имя>  <Адрес электронной почты>""".trimMargin())
            }
        }
    }

    class AddPhone(val value: List<String>) : Command {
        var name = value[1]
        var phone = value[2]
        override fun isValid(): Boolean {
            return name.matches(Regex("""[A-Z][a-z]+""")) && phone.matches(Regex("\\+?[0-9]+"))
        }

        fun savePersonPhone() {
            println("save1")
            if (Show.persons.containsKey(name)) {
                Show.persons[name]?.phones?.add(phone)
                println("save2")
            } else Show.persons.put(name, Person(name, phone = phone))
            println("save3")
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

            fun savePersonEmail() {
                if (Show.persons[name] != null) {
                    Show.persons[name]?.emails?.add(email)
                } else Show.persons.put(name, Person(name = name, email = email))
                println("Save ${name}, ${email}")
            }
        }

    class Show(val value: List<String>) : Command {
        var name = value[1]
            override fun isValid(): Boolean {
     //           var name = value[1]
                return (name.matches(Regex("""[A-Z][a-z]+""")))
            }

            companion object {
                val persons : MutableMap<String, Person> = mutableMapOf()
            }

            fun showPerson() {
                println(Show.persons[name] ?: "Not initialized")
            }
        }
    class ShowAll(val value: String) : Command{
        override fun isValid(): Boolean {
            return (value == "showAll")
        }
        fun showAll(){
            println(Show.persons)
        }
    }

