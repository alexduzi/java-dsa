package br.com.alexduzi.open_the_lock;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Main {
    // ref https://leetcode.com/problems/open-the-lock/description/
    
    // Você tem um cadeado à sua frente com 4 rodas circulares. 
    // Cada roda tem 10 ranhuras '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. 
    // As rodas podem girar livremente e se enrolar: por exemplo, podemos girar '9'para ser '0', ou '0'para ser '9'. 
    // Cada movimento consiste em girar uma roda uma ranhura.

    // A trava começa inicialmente em '0000', uma string que representa o estado das 4 rodas.

    // Você recebe uma lista de deadendsbecos sem saída, o que significa que, se a fechadura exibir 
    // algum desses códigos, as engrenagens da fechadura pararão de girar e você não conseguirá abri-la.

    // Dado um targetvalor a que representa as rodas necessárias para destravar a fechadura, 
    // retorne o número mínimo total de voltas necessárias para abrir a fechadura, ou -1 se for impossível.
    
    // Entrada: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
    // Saída: 6
    // Explicação: 
    // Uma sequência de movimentos válidos seria "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
    // Note que uma sequência como "0000" -> "0001" -> "0002" -> "0102" -> "0202" seria inválida.
    // porque as rodas da fechadura ficam presas depois que o visor se torna o beco sem saída "0102".
    public static void main(String[] args) {
        System.out.println(openLock(
            new String[] {
            "0201","0101","0102","1212","2002"
        }, 
        "0202"));
    }
    
    public static int openLock(String[] deadends, String target) {
        
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        begin.add("0000");
        end.add(target);
        
        int ans = 0;
        
        while (!begin.isEmpty() && !end.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for(String s : begin) {
                if(end.contains(s)) return ans;
                if(deads.contains(s)) continue;
                deads.add(s);
                StringBuilder sb = new StringBuilder(s);
                for(int i = 0; i < 4; i ++) {
                    char c = sb.charAt(i);
                    String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                    String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
                    if(!deads.contains(s1))
                        temp.add(s1);
                    if(!deads.contains(s2))
                        temp.add(s2);
                }
            }
            ans++;
            begin = end;
            end = temp;
        }
        

        return -1;
    }
}