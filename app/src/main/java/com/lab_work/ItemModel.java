package com.lab_work;

public class ItemModel {
    private String number;

    public ItemModel(int number) {
        String str = num2words(number, 1);
        // Костыль но я не знаю как сделать правильно
        while (str.length() < 135)
        str = str + "   ";
        this.number = str;
    }

    public ItemModel() {
        this.number = "";
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }


    private static final String dig1[][] = {{"одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"один", "два"}};
    private static final String dig10[]  = {"десять","одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private static final String dig20[]  = {"двадцать", "тридцать", "сорок", "пятьдесят",
            "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private static final String dig100[] = {"сто","двести", "триста", "четыреста", "пятьсот",
            "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private static final String leword[][] = {
            {"", "", "", "0"},
            {"", "", "", "1"},
            {"тысяча", "тысячи", "тысяч", "0"},
            {"миллион", "миллиона", "миллионов", "1"},
            {"миллиард", "миллиарда", "миллиардов", "1"},
            {"триллион", "триллиона", "триллионов", "1"}};

    public String num2words(long num, int level) {
        StringBuilder words = new StringBuilder(50);
        if (num == 0) words.append("ноль ");
        int wordGender = leword[level][3].indexOf("1") + 1;
        int h = (int) (num % 1000);
        int d = h / 100;
        if (d > 0) words.append(dig100[d - 1]).append(" ");
        int n = h % 100;
        d = n / 10;
        n = n % 10;
        switch (d) {
            case 0:
                break;
            case 1:
                words.append(dig10[n]).append(" ");
                break;
            default:
                words.append(dig20[d - 2]).append(" ");
        }
        if (d == 1) n = 0;
        switch (n) {
            case 0:
                break;
            case 1:
            case 2:
                words.append(dig1[wordGender][n - 1]).append(" ");
                break;
            default:
                words.append(dig1[0][n - 1]).append(" ");
        }
        if (level != 1) {   //To skip appending currency suffixes
            switch (n) {
                case 1:
                    words.append(leword[level][0]);
                    break;
                case 2:
                case 3:
                case 4:
                    words.append(leword[level][1]);
                    break;
                default:
                    if ((h != 0) || ((h == 0) && (level == 1)))
                        words.append(leword[level][2]);
            }
        }
        long nextnum = num / 1000;
        if (nextnum > 0) {
            return (num2words(nextnum, level + 1) + " " + words.toString()).trim();
        } else {
            return words.toString().trim();
        }
    }
}
