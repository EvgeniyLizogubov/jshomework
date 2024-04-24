package com.github.evgenylizogubov;

public class HomeWorkApp {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivan Ivanov", "Manager", "ivanov@mail.com", "81111111111",
                50000, 37);
        employees[1] = new Employee("Petr Petrov", "Engineer", "petrov@mail.com", "82222222222",
                60000, 29);
        employees[2] = new Employee("Alexey Alexeyev", "Director", "alexeyev@mail.com",
                "83333333333", 100500, 45);
        employees[3] = new Employee("Vasiliy Vasilyev", "Programmer", "vasilyev@mail.com",
                "84444444444", 90000, 33);
        employees[4] = new Employee("Alexsandra Alexsandrova", "Accountant", "alexsandrova@mail.com",
                "85555555555", 80000, 41);
        
        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                employee.printInfo();
            }
        }
    }
}
