import java.io.IOException;
import java.util.Scanner;


public class Countries {
    public static void main(final String[] args) {
        Country[] countries = Country.values();
        for (int i = 0; i < countries.length; i++) {
            System.out.println(countries[i]);
        }
        while (true) {

            System.out.println("Введите страну:");
            Scanner in = new Scanner(System.in);
            String input;
            input = in.nextLine();

            if (input.equals("stop")) {
                System.exit(1);
            }

            Country result;
            try {
                result = Country.valueOf(input);
                System.out.println(result.getInfo());
            } catch (IllegalArgumentException e) {
                System.out.println("Наименование страны на английском введено некорректно, проверяем русское название...");
                try {
                    result = Country.getByRuName(input);
                    System.out.println(result.getInfo());
                } catch (NoSuchCountryException e1) {
                    System.out.println("Страны '" + input + "' не существует.");
                }
            }
        }
    }

    public static class NoSuchCountryException extends IOException {
        public NoSuchCountryException(String countryName) {
            super("Страны '" + countryName + "' не существует.");
        }
    }

    public enum Country {
        RUSSIA("Россия", false) {},
        GERMANY("Германия", true) {},
        SPAIN("Испания", true) {};

        private final String ruName;
        private final boolean isOpen;

        Country(final String _ruName, final boolean _isOpen) {
            ruName = _ruName;
            isOpen = _isOpen;
        }

        @Override
        public String toString() {
            return (this.name() + " (" + this.ruName + ")");
        }

        public String getInfo() {
            String isOpenMessage;
            if (this.isOpen)
                isOpenMessage = "открыта";
            else
                isOpenMessage = "закрыта";
            return ("Страна [" + this.toString() + "] " + isOpenMessage + " для посещения");
        }

        public static Country getByRuName(String ruNameQuery) throws NoSuchCountryException {
            Country[] countries = Country.values();
            for (int i = 0; i < countries.length; i++) {
                if (countries[i].ruName.equals(ruNameQuery))
                    return countries[i];
            }
            throw new NoSuchCountryException(ruNameQuery);
        }
    }
}




