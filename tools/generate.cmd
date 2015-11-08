cls
cd %~dp0
java -cp jflex\JFlex.jar JFlex.Main -d ..\src\com\davidmogar\njc\lexicon ..\src\com\davidmogar\njc\lexicon\lexicon.flex
pause
byaccj\yacc.exe -J -v -Jpackage=com.davidmogar.njc.syntactic -Jsemantic=Object "../src/com/davidmogar/njc/syntactic/syntactic.y"
move Parser.java ../src/com/davidmogar/njc/syntactic
move y.output byaccj