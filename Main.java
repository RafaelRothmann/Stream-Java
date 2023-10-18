import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main{
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        ArrayList<Employee> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] fileds = line.split(",");
                list.add(new Employee(fileds[0], fileds[1], Double.parseDouble(fileds[2])));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("ERRO " + e.getMessage());
        }
        
        
        List<String> email = list.stream()
        .filter(x -> x.getSalary() > salary)
        .map(x -> x.getEmail())
        .sorted()
        .collect(Collectors.toList());

        System.out.println();
        System.out.printf("Email of people whose salary is more than %.2f\n", salary);
        email.forEach(System.out::println);
        System.out.println();

        Double sum = list.stream()
        .filter(x -> x.getName().startsWith("M"))
        .map(x -> x.getSalary())
        .reduce(0.0, (x,y) -> x + y);

        System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));
    }
}