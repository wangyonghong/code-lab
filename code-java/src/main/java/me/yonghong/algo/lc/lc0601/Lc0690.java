package me.yonghong.algo.lc.lc0601;

import me.yonghong.algo.Solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

public class Lc0690 implements Solution {
    Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);

    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        if (employee.subordinates == null || employee.subordinates.isEmpty()) {
            return employee.importance;
        }
        return employee.importance
                + map.get(id).subordinates.stream()
                .mapToInt(this::dfs)
                .sum();
    }
}
