fun main() {
    var flag = true
    do{
        var command = readCommand()
        if(command.isValid()){
            when(command){
                is Help -> Help.showHelp()
                is Exit -> flag = false
                is AddPhone -> if( command.isValid())command.savePersonPhone()
                is AddEmail -> if( command.isValid())command.savePersonEmail()
                is Show ->  if(command.isValid())command.showPerson()
                is ShowAll -> command.showAll()
            }
        }else Help.showHelp()
    }while (flag)
    println("exit")
}

fun readCommand(): Command {
    var responce = readlnOrNull()
    val parts = responce?.split(" ")
    if(parts!=null && parts[0] != null) {
        return when (parts?.get(0)) {
            "exit"     -> Exit(parts[0])
            "help"     -> Help(parts[0])
            "addPhone" -> AddPhone(parts)
            "addEmail" -> AddEmail(parts)
            "show"     -> Show(parts)
            "showAll"  -> ShowAll(parts[0])
            else       -> Help(parts[0])
        }
    }else return Help("help")
}






