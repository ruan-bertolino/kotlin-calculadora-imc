# kotlin-calculadora-imc
Aluno; Ruan Pedro Bertolino Batista
Turma ; 3°semestre do curso técnico de infórmatica 2026
## Descrição do Projeto
O **kotlin-calculadora-imc** é uma aplicação mobile para Android desenvolvida inteiramente em **Kotlin** utilizando o **Jetpack Compose**. O objetivo principal da aplicação é calcular o Índice de Massa Corporal (IMC) do utilizador a partir dos dados inseridos de peso (em kg) e altura (em metros). O aplicativo classifica automaticamente a saúde do utilizador de acordo com a tabela padrão de IMC utilizando uma estrutura `when` e disponibiliza um comutador (*Switch*) interativo para arredondar o resultado final exibido no ecrã.

---

## Tecnologias Utilizadas
* **Linguagem**: Kotlin
* **Framework UI**: Jetpack Compose com Material Design 3 (Material3)
* **IDE**: Android Studio
* **Build System**: Gradle com Kotlin DSL

---

## Como Compilar e Executar o Projeto

Siga os passos abaixo para clonar, compilar e executar o projeto no seu computador:

1. **Clonar o Repositório**:
   Abra o terminal e execute o comando:
   ```bash
   git clone [https://github.com/ruan-bertolino/kotlin-calculadora-imc.git](https://github.com/ruan-bertolino/kotlin-calculadora-imc.git)
   Abrir no Android Studio:

Abra o Android Studio.

Clique em File > Open... e selecione a pasta do projeto clonado (kotlin-calculadora-imc).

Sincronizar o Gradle:

Aguarde a sincronização automática das dependências. Se necessário, clique em Sync Project with Gradle Files no canto superior direito.

Executar no Emulador ou Dispositivo Físico:

Selecione um dispositivo virtual (AVD) configurado (API 30+) ou conecte um telemóvel Android físico com a depuração USB ativada.

Clique no botão verde de execução (Run 'app') ou prima Shift + F10.

Descrição dos Arquivos e Composables
1. MainActivity.kt
Arquivo central do projeto que agrupa a atividade principal, a lógica de negócio isolada, as constantes de limites e todos os componentes de interface construídos em Jetpack Compose (sem utilização de nenhum ficheiro XML de layout).

ImcLimites (Object/Singleton): Centraliza os valores limite padrão de classificação do IMC (ABAIXO_PESO = 18.5, PESO_NORMAL_MAX = 24.9, SOBREPESO_MAX = 29.9), evitando o uso de números mágicos espalhados pelo código.

calcularIMC(peso: Double, altura: Double): Double (Função de Lógica): Função Kotlin pura separada da interface que calcula o valor numérico bruto do IMC utilizando a fórmula peso / (altura * altura).

getImcClassification(imc: Double): String (Função de Lógica): Avalia o resultado numérico do IMC através de uma estrutura when e devolve a respetiva string descritiva da faixa de saúde.

MainActivity: Classe que herda de ComponentActivity, responsável por inicializar o ciclo de vida da app e carregar o tema e os composables através do bloco setContent.

CalculadoraImcLayout() @Composable: Função principal de layout do ecrã. Gere os estados reativos locais (remember e mutableStateOf) para os inputs de peso, altura, estado do switch de arredondamento e resultados numéricos/textuais.

EditNumberField(...) @Composable: Componente visual reutilizável de entrada de texto que implementa o conceito de State Hoisting (recebendo value e onValueChange como parâmetros) e restringe o teclado do utilizador exclusivamente para números (KeyboardType.Number).
