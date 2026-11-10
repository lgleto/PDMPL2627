# Sumários — Programação de Dispositivos Móveis

Ano letivo 2026/2027. Período de 15 de setembro a 27 de outubro de 2026.

## Horário
**terça-feira**: 18h-20h
**terça-feira**: 20h-22h

---

## 15/09/2026 — terça-feira (18h-20h)

**Título:** Apresentação da unidade curricular

**Descrição:** Apresentação dos objetivos, dos conteúdos programáticos, da metodologia e da avaliação. Enquadramento do desenvolvimento de aplicações para plataformas móveis.

## 15/09/2026 — terça-feira (20h-22h)

**Título:** Fundamentos da linguagem Kotlin

**Descrição:** Sintaxe de Kotlin: variáveis imutáveis e mutáveis, tipos e inferência de tipos, funções e parâmetros com valor por omissão. Estruturas de controlo de fluxo. Nulidade e chamadas seguras. Funções de ordem superior e lambdas.

## 22/09/2026 — terça-feira (18h-20h)

**Título:** Estrutura de uma aplicação com Jetpack Compose

**Descrição:** Projeto Android com Jetpack Compose. `Activity`, `setContent` e tema da aplicação. Primeiro ecrã composto dentro de um `Scaffold`.

## 22/09/2026 — terça-feira (20h-22h)

**Título:** Funções composable, estado e eventos

**Descrição:** Funções composable, previsualização e disposição de texto e botões em `Column`. Estado com `remember` e `mutableStateOf`. Evento de clique que atualiza o texto apresentado.

## 29/09/2026 — terça-feira (18h-20h)

**Título:** Layout em grelha e componente de botão

**Descrição:** Calculadora em Jetpack Compose. Grelha com `Column` e `Row`, distribuição do espaço com pesos e espaçamento. Botão reutilizável com o tema Material para distinguir dígitos e operações.

## 29/09/2026 — terça-feira (20h-22h)

**Título:** Estado do visor e introdução de operações

**Descrição:** Estado do visor, do operando e da operação pendente. Introdução de dígitos e do separador decimal. Primeira operação binária, ainda tratada na interface, e reposição do modo de introdução após cada operador.

## 06/10/2026 — terça-feira (18h-20h)

**Título:** Separação entre interface e lógica de negócio

**Descrição:** Extração da lógica da calculadora para `CalculatorBrain`. Enumerado para representar as operações. A interface passa a delegar o cálculo e a limitar-se ao estado apresentado.

## 06/10/2026 — terça-feira (20h-22h)

**Título:** Operações binárias, unárias e reposição

**Descrição:** Operações binárias e unárias: soma, subtração, multiplicação, divisão, raiz quadrada, percentagem e troca de sinal. Igualdade, limpeza do acumulador e reposição do visor.

## 13/10/2026 — terça-feira (18h-20h)

**Título:** Listas lazy e modelo de artigo

**Descrição:** Modelo de dados de um artigo. Apresentação de uma coleção com `LazyColumn`, compondo cada elemento a partir do título, da descrição e da data.

## 13/10/2026 — terça-feira (20h-22h)

**Título:** Pedidos HTTP, ViewModel e estado da interface

**Descrição:** Pedidos HTTP e interpretação de respostas JSON. Arquitetura com `ViewModel`, `StateFlow` e atualização do ecrã a partir do estado. Exposição dos artigos, do estado de carregamento e dos erros.

## 20/10/2026 — terça-feira (18h-20h)

**Título:** Imagens remotas e formatação de datas

**Descrição:** Linha de artigo com imagem remota e imagem de recurso quando o endereço não existe. Formatação da data de publicação recebida do servidor.

## 20/10/2026 — terça-feira (20h-22h)

**Título:** WebView e navegação com argumentos

**Descrição:** Visualização da notícia numa `WebView`. Navegação entre a lista e o detalhe, com o título e o endereço passados como argumentos da rota.

## 27/10/2026 — terça-feira (18h-20h)

**Título:** Tema Material 3, barra superior e partilha

**Descrição:** Tema Material 3 com paletas para modo claro e modo escuro. Barra superior, regresso na pilha de ecrãs e `Intent` implícito para partilhar o título e o endereço da notícia.

## 27/10/2026 — terça-feira (20h-22h)

**Título:** Navegação inferior e autenticação

**Descrição:** Organização do código em pacotes por responsabilidade. Barra de navegação inferior e destinos que filtram as notícias por categoria. Novo exemplo com ecrã de autenticação: `ViewModel`, validação de email e palavra-passe, e início de sessão com Firebase Authentication.
