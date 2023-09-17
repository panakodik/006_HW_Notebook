import java.util.*;

public class Notebook {
    private String brand;
    private int ram;
    private int storage;
    private String os;
    private String color;

    public Notebook(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    // Геттеры для всех полей
    public String getBrand() { // Бренд ноутбука
        return brand;
    }

    public int getRam() { // Объем оперативной памяти
        return ram;
    }

    public int getStorage() { // Объем накопителя постоянной памяти
        return storage;
    }

    public String getOs() { // Операционная система, установленная на ноутбуке
        return os;
    }

    public String getColor() { // Цвет ноутбука
        return color;
    }

    public static List<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<Integer, Integer> filters) {
        List<Notebook> filteredNotebooks = new ArrayList<>();

        for (Notebook notebook : notebooks) {
            boolean passesFilter = true;

            // Проверка фильтров
            for (Map.Entry<Integer, Integer> entry : filters.entrySet()) {
                int criteria = entry.getKey();
                int minValue = entry.getValue();

                switch (criteria) {
                    case 1:
                        if (notebook.getRam() < minValue) {
                            passesFilter = false;
                        }
                        break;
                    case 2:
                        if (notebook.getStorage() < minValue) {
                            passesFilter = false;
                        }
                        break;
                    case 3:
                        if (!notebook.getOs().equalsIgnoreCase("Windows")) { // Задаем операционную систему для филтра
                            passesFilter = false;
                        }
                        break;
                    case 4:
                        if (!notebook.getColor().equalsIgnoreCase("Grey")) { // Задаем цвет для филтра
                            passesFilter = false;
                        }
                        break;
                    // При необходимости, можно добавить другие критерии опсывающие характеристики ноутбуков
                }

                if (!passesFilter) {
                    break; // Если не прошел один из критериев, программа выйдет из цикла
                }
            }

            if (passesFilter) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }

    public static void main(String[] args) {
        // Список ноутбуков в нашем магазине техники
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("Samsung", 8, 512, "Windows", "Black"));
        notebooks.add(new Notebook("MacBook", 16, 1024, "MacOS", "Silver"));
        notebooks.add(new Notebook("Asus", 4, 256, "Linux", "Black"));
        notebooks.add(new Notebook("Aser", 2, 128, "NotOs", "Grey"));
        notebooks.add(new Notebook("Lenovo", 32, 2048, "Windows", "Black"));
        notebooks.add(new Notebook("HP", 16, 1024, "Windows", "Grey"));

        // Критерии фильтрации
        Map<Integer, Integer> filters = new HashMap<>();
        filters.put(1, 16); // ОЗУ не менее 16 ГБ
        filters.put(2, 1024); // Объем ЖД не менее 1024 ГБ
        filters.put(3, 1); // Операционная система - Windows
        filters.put(4, 1); // Цвет - Grey

        // Фильтр ноутбуков и вывод результата
        List<Notebook> filteredNotebooks = filterNotebooks(notebooks, filters);
        for (Notebook notebook : filteredNotebooks) {
            System.out.println("Brand: " + notebook.getBrand() +
                               ", RAM: " + notebook.getRam() +
                               ", Storage: " + notebook.getStorage() +
                               ", OS: " + notebook.getOs() +
                               ", Color: " + notebook.getColor());
        }
    }
}