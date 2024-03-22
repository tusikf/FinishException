/*Напишите приложение, которое будет запрашивать у пользователя следующие данные, разделенные пробелом:
Фамилия Имя Отчество дата _ рождения номер _ телефона пол
Форматы данных:
фамилия, имя, отчество - строки
дата _ рождения - строка формата dd.mm.yyyy
номер _ телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.
Приложение должно проверить введенные данные по количеству. Если количество не совпадает, вернуть код ошибки,
обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
Приложение должно распарсить полученную строку и выделить из них требуемые значения. Если форматы данных не совпадают,
нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои.
Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку
должны записаться полученные данные, вида
        <Фамилия> <Имя> <Отчество> <дата _ рождения> <номер _ телефона> <пол>
Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
пользователь должен увидеть стектрейс ошибки.
Данная промежуточная аттестация оценивается по системе "зачет" / "не зачет"
        "Зачет" ставится, если слушатель успешно выполнил задание.
"Незачет" ставится, если слушатель не выполнил задание.
Критерии оценивания: Слушатель написал приложение, которое запрашивает у пользователя следующие данные, разделенные пробелом:
Фамилия Имя Отчество дата _ рождения номер _ телефона пол
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите через пробел : Фамилия Имя Отчество _ дата рождения _ номер телефона _ пол");
        System.out.println("Вводимые данные должны быть в формате:\n" +
                "        Фамилия Имя Отчество - текст\n" +
                "        Дата рождения - dd.mm.yyyy\n" +
                "        Номер телефона - целые числа без знаков\n" +
                "        Пол - символ латиницей f или m\n");
        String string = new String();
        String[] vvod = new String[]{};
        Scanner scanner = new Scanner(System.in);
        string = scanner.nextLine();

        // Сплитуем строку по пробелу
        vvod = string.split("\\s+");
//        for (String i: vvod) {
//            System.out.println(i);
//        }

        // Проверяем количество введенных данных
        if (vvod.length > 6) throw new IllegalArgumentException("Вы ввели лишние данные");
        if (vvod.length < 6) throw new IllegalArgumentException("Вы ввели не все данные");

        // Делим введенные данные на отдельные части
        String thename = vvod[0];
        String name = vvod[1];
        String secondname = vvod[2];
        String birthdate = vvod[3];
        String phonenumber = vvod[4];
        char gender = vvod[5].charAt(0);

        // Проверяем форматы введенных данных
        if (birthdate.length() != 10) {
            throw new IllegalArgumentException("Дата должна быть формата - dd.mm.yyyy");
        }
        if (!phonenumber.matches("\\d+") || phonenumber.length() != 11) {
                throw new IllegalArgumentException("В номере телефона должно быть только 10 цифры.");
            }
        if ((gender != 'f') && (gender != 'm')) {
                throw new IllegalArgumentException("Введен неверный формат пола.");
            }

        // Записываем в файл

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(thename + ".txt", true);
            fileWriter.write(thename + " " + name + " " + secondname + " " + birthdate + " " + phonenumber + " " + gender + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println("До этого не дойдет - для проверки");

    }
}
