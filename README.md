# 🧩 Projeto SOLID na Prática — Do Caos à Ordem
### Sistema de Logística Internacional — TechStore

---

## 📌 Cenário

A **TechStore** é uma empresa de logística que processa fretes nacionais e internacionais por diferentes modais de transporte (**Aéreo, Marítimo, Terrestre**), com tributação variável e notificação ao cliente após cada operação.

O sistema passou por dois momentos distintos de desenvolvimento:

| Momento | Descrição |
|---------|-----------|
| **O Caos** | Implementação monolítica que viola propositalmente os 5 princípios SOLID |
| **A Ordem** | Refatoração completa com Arquitetura em Camadas, padrão Strategy e interfaces |

---

## 💥 O Caos — Antipattern

Uma única classe concentra todas as responsabilidades: entrada de dados, cálculo de frete, tributação, persistência e notificação.

### Violações SOLID identificadas

| Princípio | Como foi violado |
|-----------|-----------------|
| **SRP** | `processarFrete()` faz I/O, negócio, impostos, banco de dados e e-mail ao mesmo tempo |
| **OCP** | Adicionar novo modal de transporte exige modificar a classe com mais `if/else` |
| **LSP** | Sem hierarquia de tipos; nenhuma implementação respeita contrato comum |
| **ISP** | Uma única classe expõe todas as operações para todos os consumidores |
| **DIP** | Depende diretamente de implementações concretas, sem qualquer abstração |

---

## ✅ A Ordem — Solução Refatorada

### Arquitetura em Camadas

```
java/
├── domain/
│   ├── model/
│   │   └── Frete.java
│   └── strategy/
│       ├── FreteRepository.java       (interface)
│       ├── FreteStrategy.java         (interface)
│       ├── NotificacaoService.java    (interface)
│       └── TaxaStrategy.java          (interface)
├── infra/
│   ├── config/
│   │   └── Conexao.java
│   ├── notification/
│   │   └── EmailService.java
│   ├── persistence/
│   │   └── FreteRepository.java
│   ├── tax/
│   │   ├── InternacionalTaxStrategy.java
│   │   └── NacionalTaxStrategy.java
│   └── transport/
│       ├── FreteAereoStrategy.java
│       ├── FreteMaritimoStrategy.java
│       └── FreteTerrestreStrategy.java
├── service/
│   └── LogisticaService.java
└── LogisticaApplication.java
```

### Responsabilidade de cada camada

#### 🔷 Domain — núcleo do sistema, sem dependências externas

- **`Frete.java`** — entidade com os dados do frete (peso, custo, origem, destino)
- **`FreteStrategy`** — contrato para cálculo de frete por modal
- **`TaxaStrategy`** — contrato para aplicação de imposto (nacional ou internacional)
- **`NotificacaoService`** — contrato para envio de notificação ao cliente
- **`FreteRepository`** — contrato para persistência

#### 🔶 Infra — implementações técnicas que dependem de tecnologia

- **`EmailService`** — implementa `NotificacaoService`, envia e-mail ao cliente
- **`FreteRepository` (persistence)** — implementa a interface, persiste no banco
- **`Conexao`** — gerencia configuração da conexão com o banco de dados
- **`FreteAereoStrategy`, `FreteMaritimoStrategy`, `FreteTerrestreStrategy`** — implementam `FreteStrategy`
- **`InternacionalTaxStrategy`, `NacionalTaxStrategy`** — implementam `TaxaStrategy`

#### 🔹 Service — orquestra o fluxo de negócio sem conhecer detalhes técnicos

- **`LogisticaService`** — recebe interfaces por injeção e coordena o processamento do frete

#### 🔸 Raiz

- **`LogisticaApplication`** — ponto de entrada, menu interativo via `Scanner`

---

## 📐 Princípios SOLID na Solução

### SRP — Single Responsibility Principle
Cada classe tem exatamente **um motivo para mudar**. `LogisticaService` só orquestra; `EmailService` só notifica; `FreteAereoStrategy` só calcula frete aéreo.

### OCP — Open/Closed Principle
Para adicionar o modal Terrestre, basta criar `FreteTerrestreStrategy` implementando `FreteStrategy`. **Nenhuma classe existente é modificada.**

### LSP — Liskov Substitution Principle
Qualquer implementação de `FreteStrategy` pode **substituir outra sem quebrar o sistema**. `LogisticaService` não sabe e não precisa saber qual modal está em uso.

### ISP — Interface Segregation Principle
As interfaces são **pequenas e focadas**: `FreteStrategy` só define cálculo de frete; `TaxaStrategy` só define cálculo de imposto; `NotificacaoService` só define envio de notificação.

### DIP — Dependency Inversion Principle
`LogisticaService` depende **exclusivamente de interfaces** (`FreteStrategy`, `TaxaStrategy`, `NotificacaoService`, `FreteRepository`). As implementações concretas são injetadas em tempo de execução.

---

## 🔄 Padrão Strategy

O padrão Strategy é o **coração da solução**. Permite trocar o comportamento de cálculo de frete e tributação em tempo de execução, sem alterar a lógica de negócio.

```
FreteStrategy (interface)
    ├── FreteAereoStrategy      → taxa por peso + adicional fixo
    ├── FreteMaritimoStrategy   → taxa por peso (menor custo)
    └── FreteTerrestreStrategy  → taxa por peso + pedágios

TaxaStrategy (interface)
    ├── NacionalTaxStrategy     → 20% sobre o custo
    └── InternacionalTaxStrategy → 60% sobre o custo
```

O `LogisticaService` recebe **qualquer combinação de estratégias** e processa corretamente — sem `if/else` e sem conhecer as implementações.

---

## ▶️ Execução — LogisticaApplication

A classe `LogisticaApplication` simula os fluxos completos do sistema através de um **menu interativo via Scanner**:

```
=== SISTEMA DE LOGÍSTICA TECHSTORE ===
1 - Processar novo frete
2 - Consultar fretes registrados
3 - Sair
```

Ao processar um frete, o usuário informa o **peso**, o **modal de transporte** e o **tipo de tributação**. O sistema então:

1. Calcula o custo via Strategy selecionada
2. Aplica a taxa
3. Persiste via repositório
4. Notifica o cliente por e-mail

---

## 📦 Entregáveis

- **Versão Caos** — classe monolítica com todas as violações SOLID comentadas e identificadas
- **Versão Ordem** — projeto completo em camadas com padrão Strategy, interfaces e menu interativo
