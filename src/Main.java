
abstract class Car {
    protected String brand;
    protected String model;
    protected String color;
    protected String bodyType;
    protected int wheels;
    protected String fuelType;
    protected String transmission;
    protected double engineVolume;

    public Car(String brand, String model, String color, String bodyType, int wheels,
               String fuelType, String transmission, double engineVolume) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.bodyType = bodyType;
        this.wheels = wheels;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.engineVolume = engineVolume;
    }

    // Базовые методы
    public void drive() {
        System.out.println("Автомобиль движется");
    }

    public void service() {
        System.out.println("Проведено обслуживание");
    }

    public void switchGear() {
        System.out.println("Переключение передачи");
    }

    public void toggleHeadlights() {
        System.out.println("Фары включены/выключены");
    }

    public void toggleWipers() {
        System.out.println("Дворники включены/выключены");
    }
}

// 2. Конкретный автомобиль (наследование)
class Sedan extends Car {
    public Sedan() {
        super("Toyota", "Camry", "Black", "Sedan", 4, "Petrol", "Automatic", 2.5);
    }
}

// 3. Расширение Car
interface StreetSweeper {
    void sweepStreet();
}

class StreetSweeperCar extends Car implements StreetSweeper {
    public StreetSweeperCar() {
        super("Municipal", "Sweeper", "Yellow", "Special", 6, "Diesel", "Manual", 3.0);
    }

    @Override
    public void sweepStreet() {
        System.out.println("Подметание улицы");
    }
}

// 4. Расширение Car с проверкой OCP
abstract class ExtendedCar extends Car {
    public ExtendedCar(String brand, String model, String color, String bodyType,
                       int wheels, String fuelType, String transmission, double engineVolume) {
        super(brand, model, color, bodyType, wheels, fuelType, transmission, engineVolume);
    }

    public void toggleFogLights() {
        System.out.println("Противотуманные фары включены/выключены");
    }

    public void transportCargo() {
        System.out.println("Перевозка груза");
    }
}

// 5. Проверка LSP (3 колеса)
class ThreeWheelCar extends Car {
    public ThreeWheelCar() {
        super("Reliant", "Robin", "Blue", "Three-Wheeler", 3, "Petrol", "Manual", 0.7);
    }
}

// 6. Проверка LSP (полёт)
class FlyingCar extends Car {
    public FlyingCar() {
        super("Aero", "X202", "White", "Flying", 4, "Electric", "Automatic", 0.0);
    }

    @Override
    public void drive() {
        System.out.println("Автомобиль летит");
    }
}

// 7-10. Интерфейсы и ISP
interface RefuelingStation {
    void refuel(String fuelType);
}

interface CleaningServices {
    void cleanWindshield();
    void cleanHeadlights();
    void cleanMirrors();
}

// Реализация интерфейсов в Car
class ModernCar extends Car implements RefuelingStation, CleaningServices {
    public ModernCar() {
        super("Tesla", "Model S", "Red", "Sedan", 4, "Electric", "Automatic", 0.0);
    }

    @Override
    public void refuel(String fuelType) {
        if (fuelType.equals("Electric")) {
            System.out.println("Зарядка батареи");
        } else {
            throw new IllegalArgumentException("Неверный тип топлива");
        }
    }

    @Override
    public void cleanWindshield() {
        System.out.println("Лобовое стекло протёрто");
    }

    @Override
    public void cleanHeadlights() {
        System.out.println("Фары протёрты");
    }

    @Override
    public void cleanMirrors() {
        System.out.println("Зеркала протёрты");
    }
}

// 11. Проверка DIP (бензин/дизель)
class PetrolCar extends Car implements RefuelingStation {
    public PetrolCar() {
        super("Ford", "Focus", "Grey", "Hatchback", 4, "Petrol", "Manual", 1.6);
    }

    @Override
    public void refuel(String fuelType) {
        if (fuelType.equals("Petrol")) {
            System.out.println("Заправка бензином");
        } else {
            throw new IllegalArgumentException("Неверный тип топлива");
        }
    }
}

class DieselCar extends Car implements RefuelingStation {
    public DieselCar() {
        super("Volvo", "XC90", "Black", "SUV", 4, "Diesel", "Automatic", 2.0);
    }

    @Override
    public void refuel(String fuelType) {
        if (fuelType.equals("Diesel")) {
            System.out.println("Заправка дизелем");
        } else {
            throw new IllegalArgumentException("Неверный тип топлива");
        }
    }
}

// Класс для мойки автомобиля (ISP)
interface CarWashServices {
    void basicWash();
    void premiumWash();
}

class CarWash implements CarWashServices {
    @Override
    public void basicWash() {
        System.out.println("Базовая мойка: кузов и стекла");
    }

    @Override
    public void premiumWash() {
        System.out.println("Премиум мойка: полная очистка + полировка");
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        // Проверка принципов
        Car sedan = new Sedan();
        sedan.drive(); // Обычное движение

        Car flyingCar = new FlyingCar();
        flyingCar.drive(); // Полёт (LSP соблюдён)

        RefuelingStation petrolCar = new PetrolCar();
        petrolCar.refuel("Petrol"); // Заправка бензином (DIP)

        CarWashServices wash = new CarWash();
        wash.premiumWash(); // Услуги мойки
    }
}