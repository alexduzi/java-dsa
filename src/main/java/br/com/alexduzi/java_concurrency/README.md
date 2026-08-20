# Evolução da Concorrência no Java: Tasks, Threads e Virtual Threads

Este guia prático explica a diferença conceitual entre tarefas e linhas de execução no ecossistema Java, detalhando a evolução histórica do modelo de concorrência até a chegada das **Virtual Threads** no Java 21.

---

## 1. Task vs. Thread vs. Virtual Thread

Para entender a diferença de forma simples, pense na **Task** como o *projeto ou instrução de trabalho* e na **Thread** como o *operário* que executa esse trabalho.

### Task (O "O quê")
* **Definição:** Uma unidade lógica de trabalho. Contém puramente o código e os dados a serem processados.
* **Abstração Java:** Representada pelas interfaces `Runnable` (executa sem retorno) ou `Callable<V>` (executa e retorna um resultado).
* **Ciclo de Vida:** Não executa de forma autônoma; precisa ser obrigatoriamente atribuída a uma Thread.

### Platform Thread / Thread Tradicional (O "Como" - Baseado no OS)
* **Definição:** Uma linha de execução pesada, mapeada diretamente para uma thread do Kernel do Sistema Operacional (relação **1:1**).
* **Abstração Java:** Classe tradicional `java.lang.Thread`.
* **Custo:** Alto consumo de recursos. Cada instância aloca estaticamente cerca de **1 MB** de memória para sua stack, limitando a escalabilidade do sistema.

### Virtual Thread / Thread Virtual (O "Como" - Baseado na JVM)
* **Definição:** Uma thread extremamente leve introduzida no **Java 21 (Project Loom)**, gerenciada integralmente pelo runtime da JVM, sem vinculação direta 1:1 com o OS.
* **Abstração Java:** Criada via `Thread.ofVirtual()` ou através de executores dedicados.
* **Funcionamento:** Mapeamento **M:N** (milhares de threads virtuais compartilham poucas threads reais do SO). Quando uma Virtual Thread bloqueia em operações de I/O (banco de dados, rede), a JVM a desmonta da thread do SO, permitindo que outra Virtual Thread seja executada imediatamente.

### Tabela Comparativa de Recursos

| Característica | Task (`Runnable` / `Callable`) | Platform Thread (Tradicional) | Virtual Thread (Java 21+) |
| :--- | :--- | :--- | :--- |
| **Natureza** | Código / Conceito Lógico | Recurso do Sistema Operacional | Recurso Interno da JVM |
| **Pegada de Memória** | Praticamente zero | ~1 Megabyte | Poucos Bytes |
| **Custo de Criação** | Irrelevante | Altamente custoso / Lento | Quase nulo |
| **Limite de Escala** | Ilimitado | Poucas milhares (Limite do SO) | Milhões (Escala Massiva) |
| **Bloqueio de I/O** | Não aplicável | Bloqueia a thread física do SO | Libera a thread física do SO |

---

## 2. A Evolução da Concorrência no Java

A arquitetura de processamento paralelo do Java passou por quatro grandes eras ao longo de seus mais de 25 anos de existência.

```
+---------------------------+     +---------------------------+     +---------------------------+     +---------------------------+
|   Java 1.0 (1996)         |     |   Java 5 (2004)           |     |   Java 7/8 (2011-2014)    |     |   Java 21+ (2023)         |
|   Thread Manual           | --> |   Executor Framework      | --> |   ForkJoin / Streams      | --> |   Virtual Threads         |
|   Mapeamento 1:1 com OS   |     |   Pools de Threads        |     |   Assincronismo / Math    |     |   Project Loom (Escala)   |
+---------------------------+     +---------------------------+     +---------------------------+     +---------------------------+
```

### 🗺️ Fase 1: As Fundações (Java 1.0 - 1996)
* **Mecanismo:** Criação direta de objetos `Thread` e acoplamento com `Runnable`.
* **Gargalo:** Não existia o conceito de reaproveitamento de recursos. Criar e destruir threads sob demanda degradava drasticamente a performance. O controle de concorrência dependia de travas manuais complexas (`synchronized`, `wait()`, `notify()`), gerando alta incidência de *deadlocks*.

### 🗺️ Fase 2: O Framework Executor (Java 5 - 2004)
* **Mecanismo:** Introdução do pacote `java.util.concurrent`, abstraindo o gerenciamento através de `ExecutorService` e pools fixos/dinâmicos (`ThreadPoolExecutor`).
* **Gargalo:** O pool resolveu a sobrecarga de criação de threads, mas manteve o modelo atrelado às threads pesadas do OS. Em cenários Web com arquitetura *Thread-per-Request*, a capacidade do servidor ficava limitada à quantidade máxima de threads físicas suportadas pela memória.

### 🗺️ Fase 3: ForkJoinPool & Parallel Streams (Java 7/8 - 2011/2014)
* **Mecanismo:** Lançamento do `ForkJoinPool` focado em paralelismo de divisão e conquista (Work-Stealing), servindo de fundação para **Parallel Streams** e as pipelines assíncronas do `CompletableFuture`.
* **Gargalo:** Modelo ideal para operações massivas focadas puramente em CPU. Contudo, a escrita de códigos assíncronos baseados em callbacks complexos gerava o padrão conhecido como *"Callback Hell"*, elevando a complexidade de manutenção e dificultando a depuração (debug).

### 🗺️ Fase 4: Virtual Threads & Project Loom (Java 21+ - 2023)
* **Mecanismo:** Separação definitiva entre a unidade de concorrência da aplicação e a thread do sistema operacional.
* **Solução:** O desenvolvedor ganha a capacidade de escrever código síncrono, linear e imperativo (fácil de ler e debugar), obtendo a performance e escalabilidade de sistemas reativos de alta escala. O uso de pools para economizar recursos torna-se obsoleto para tarefas de I/O.

---

## 3. Demonstração Prática de Códigos

### Abordagem Clássica (Java 5+)
Utiliza um pool limitado para evitar o esgotamento de memória do sistema operacional. As tarefas aguardam em uma fila caso o pool esteja cheio.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClassicConcurrency {
    public static void main(String[] args) {
        // Pool limitado a 10 threads platform do OS
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    // Se houver bloqueio de I/O aqui, a thread do OS fica inutilizada
                    System.out.println("Executando em thread clássica: " + Thread.currentThread());
                });
            }
        } // Auto-close do executor chama o shutdown automaticamente
    }
}
```

### Abordagem Moderna (Java 21+)
Cria uma thread virtual leve dedicada e exclusiva para cada tarefa individual. Sem o overhead de gerenciar pools fixos.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.time.Duration;

public class ModernConcurrency {
    public static void main(String[] args) {
        // Executor que aloca uma thread virtual inédita por task
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    // Este bloqueio suspende apenas a thread virtual, liberando o hardware real
                    Thread.sleep(Duration.ofMillis(500));
                    System.out.println("Executando em Virtual Thread: " + Thread.currentThread());
                    return "Sucesso";
                });
            }
        } // Aguarda a finalização de todas as threads virtuais com segurança
    }
}
```

---

## 4. Resumo de Diretrizes: Quando usar o quê?

1. **Use Virtual Threads (Java 21+)** se sua aplicação for altamente focada em operações de **I/O** (como APIs Web REST, microsserviços, chamadas HTTP, consultas a bancos de dados relacionais ou leitura de arquivos).
2. **Use ThreadPoolExecutor Tradicional (Java 5+)** se você precisar limitar estritamente o acesso simultâneo a um recurso externo escasso ou não escalável.
3. **Use ForkJoinPool / Parallel Streams (Java 8+)** se sua aplicação rodar rotinas intensivas de **CPU** (como processamento de imagens, criptografia, compressão de arquivos ou cálculos matemáticos complexos).