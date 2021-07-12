package exceptionsTrainingGolovach_p2.overridingAndThrows;

import java.io.IOException;

/*
 * Однако тут мы попытались «расширить тип» бросаемых исключений
 */
public class Parent01 {
    public void f() throws IOException, InterruptedException {}
}

class ChildB extends Parent {
//    @Override
//    public void f() throws Exception {}      //ошибка компиляции
}