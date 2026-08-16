package br.com.alexduzi.number_of_students_unable_to_eat_lunch;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        System.out.println(countStudents(new int[] { 1, 1, 0, 0 }, new int[] { 0, 1, 0, 1 }));
    }

    static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int student : students) {
            queue.add(student);
        }

        boolean eat;
        int count = students.length;

        // fazer o loop até comerem todos os lanches
        // ou até rodar a fila de todos os alunos
        for (int sandwich : sandwiches) {
            eat = false;
            while (!eat && count > 0) {
                int student = queue.poll();
                if (sandwich == student) {
                    eat = true;
                } else {
                    // aluno não quis comer o lanche, volta para a fila
                    queue.add(student);
                    count--;
                }
            }

            if (count == 0) {
                return queue.size();
            }
        }

        return 0;
    }
}
