# Programação de Dispositivos Móveis

Exemplos das aulas de 2026/2027, regime pós-laboral. Todos os projetos estão escritos em Kotlin e usam os padrões da linguagem trabalhados nas aulas. Cada pasta é um projeto Android que se abre à parte no Android Studio.

Horário: terça-feira, 18h–20h e 20h–22h. Os sumários estão em [SUMARIOS.md](SUMARIOS.md).

## Padrões de Kotlin

O código segue os padrões de Kotlin usados nas aulas, em vez de equivalentes de Java.

- Tipos anuláveis e chamadas seguras (`?.`, `?:`, `?.let`), para tratar valores que podem não existir.
- Funções de extensão sobre `String` e `Date`, para ler a data do servidor e formatá-la.
- `companion object` para membros da classe, como a fábrica de um artigo a partir de JSON.
- Enumerados com propriedades e `when`, para as operações da calculadora.
- `data class` para o estado do ecrã e para os modelos.
- `sealed class` para as rotas de navegação.
- Lambdas e tipos função nos eventos dos botões e nos pedidos assíncronos.
- Parâmetros com valor por omissão e cadeias de caracteres com modelo.

| Exemplo | O que mostra | Aulas |
|---|---|---|
| [HelloWorld](HelloWorld) | `Scaffold`, estado e evento de clique | 22/09 |
| [Calculator](Calculator) | Grelha, botão reutilizável e `CalculatorBrain` | 29/09 e 06/10 |
| [MyNews](MyNews) | Lista, HTTP, `ViewModel`, imagens, `WebView`, tema e barras | 13/10, 20/10 e 27/10, 18h–20h |
| [ShoppingList](ShoppingList) | Ecrã de autenticação com Firebase | 27/10, 20h–22h |

## HelloWorld

Primeiro ecrã com Jetpack Compose. `MainActivity` monta o tema e o `Scaffold`. `MainView` guarda o texto com `remember` e `mutableStateOf` e muda-o no clique do botão.

## Calculator

Calculadora no ponto em que as aulas separam a interface da lógica.

- `CalculatorView` desenha o visor e a grelha com `Column` e `Row`, e trata a introdução de dígitos e do ponto decimal.
- `CalcButton` é o botão reutilizável, com cor do tema diferente para as operações.
- `CalculatorBrain` e o enumerado `Operation` fazem as operações binárias e unárias e guardam o acumulador.

## MyNews

Aplicação de notícias que junta as aulas de listas, rede, navegação e tema. O código está organizado por pacotes (`ui/home`, `ui/components`, `models`).

- `ArticlesView` apresenta os artigos numa `LazyColumn`.
- `ArticlesViewModel` pede os destaques, lê o JSON e expõe artigos, carregamento e erro num `StateFlow`.
- `ArticleRowView` mostra a imagem remota, com uma imagem de recurso quando o endereço não existe, e formata a data de publicação.
- `ArticleDetailView` abre o endereço da notícia numa `WebView`. A rota recebe o título e o endereço.
- O tema Material 3 define paletas para o modo claro e para o modo escuro.
- `MyTopBar` volta atrás na pilha e partilha o artigo com um `Intent`.
- `MyBottomBar` muda de categoria (geral, negócios, desporto e saúde) e filtra as notícias.

## ShoppingList

Ecrã de início de sessão. `LoginView` lê o email e a palavra-passe. `LoginViewModel` guarda esse estado num `StateFlow`, valida os campos e autentica com Firebase Authentication.

## Licença

Estes exemplos são distribuídos sob a licença MIT. O texto completo está em [LICENSE](LICENSE).
