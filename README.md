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
- Lambdas e tipos função nos eventos dos botões, nos pedidos assíncronos e nos ouvintes do Firestore.
- Parâmetros com valor por omissão e cadeias de caracteres com modelo.

| Exemplo | O que mostra | Aulas |
|---|---|---|
| [HelloWorld](HelloWorld) | `Scaffold`, estado e evento de clique | 22/09 |
| [Calculator](Calculator) | Grelha, botão reutilizável e `CalculatorBrain` | 29/09 e 06/10 |
| [MyNews](MyNews) | Lista, HTTP, `ViewModel`, imagens, `WebView`, tema e barras | 13/10, 20/10 e 27/10, 18h–20h |
| [ShoppingList](ShoppingList) | Autenticação, Firestore, carrinhos e produtos | 27/10, 20h–22h e 10/11 |

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

Lista de compras com Firebase Authentication e Cloud Firestore. O código está organizado por pacotes (`ui/login`, `ui/home`, `ui/home/kart`, `ui/components`, `models`).

- `LoginView` lê o email e a palavra-passe. `LoginViewModel` valida os campos, autentica com Firebase Authentication e grava o utilizador na coleção `users`.
- `MainActivity` abre o ecrã inicial conforme exista sessão e declara as rotas numa `sealed class`.
- `HomeView` lista os carrinhos. `HomeViewModel` escuta a coleção `karts` e fica apenas com aqueles em que o identificador do utilizador está em `userIds`.
- `AddKartView` cria um carrinho com o nome indicado e o identificador do utilizador.
- `KartView` escuta o carrinho e a subcoleção `products`. `ProductViewRow` mostra o nome, a quantidade e a caixa de verificação.
- `AddProductView` acrescenta um produto com nome e quantidade. Marcar a caixa atualiza o campo `checked` no Firestore.
- `MyTopBar` volta atrás na pilha e, no ecrã inicial, termina a sessão.

## Licença

Estes exemplos são distribuídos sob a licença MIT. O texto completo está em [LICENSE](LICENSE).
